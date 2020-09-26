package trading

import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.SandboxOpenApi
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import ru.tinkoff.invest.openapi.models.market.Instrument
import ru.tinkoff.invest.openapi.models.portfolio.PortfolioCurrencies.PortfolioCurrency
import ru.tinkoff.invest.openapi.models.streaming.StreamingRequest
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

object App {

    @JvmStatic
    fun main(args: Array<String>) {
        val logger: Logger = initLogger()

        val parameters = TradingParameters(
            ssoToken = args[0],
            tickers = arrayOf("AAPL"),
            candleIntervals = arrayOf(CandleInterval.DAY),
            sandboxMode = true
        )

        val factory = OkHttpOpenApiFactory(parameters.ssoToken, logger)
        try {
            val api: OpenApi
            logger.info("Создаём подключение... ")
            if (parameters.sandboxMode) {
                api = factory.createSandboxOpenApiClient(Executors.newSingleThreadExecutor())
                // ОБЯЗАТЕЛЬНО нужно выполнить регистрацию в "песочнице"
                (api as SandboxOpenApi).sandboxContext.performRegistration(null).join()
            } else {
                api = factory.createOpenApiClient(Executors.newSingleThreadExecutor())
            }
            val listener = StreamingApiSubscriber(logger, Executors.newSingleThreadExecutor())
            api.streamingContext.eventPublisher.subscribe(listener)
            val currentOrders = api.ordersContext.getOrders(null).join()
            logger.info("Количество текущих заявок: " + currentOrders.size)
            val currentPositions = api.portfolioContext.getPortfolio(null).join()
            logger.info("Количество текущих позиций: " + currentPositions.positions.size)
            for (i in parameters.tickers.indices) {
                val ticker = parameters.tickers[i]
                val candleInterval = parameters.candleIntervals[i]
                logger.info("Ищём по тикеру $ticker... ")
                val instrumentsList = api.marketContext.searchMarketInstrumentsByTicker(ticker!!).join()
                val instrumentOpt = instrumentsList.instruments.stream().findFirst()
                val instrument: Instrument
                instrument = if (instrumentOpt.isEmpty) {
                    logger.severe("Не нашлось инструмента с нужным тикером.")
                    return
                } else {
                    instrumentOpt.get()
                }
                logger.info("Получаем валютные балансы... ")
                val portfolioCurrencies = api.portfolioContext.getPortfolioCurrencies(null).join()
                val portfolioCurrencyOpt = portfolioCurrencies.currencies.stream()
                    .filter { pc: PortfolioCurrency -> pc.currency == instrument.currency }
                    .findFirst()
                val portfolioCurrency: PortfolioCurrency
                if (portfolioCurrencyOpt.isEmpty) {
                    logger.severe("Не нашлось нужной валютной позиции.")
                    return
                } else {
                    portfolioCurrency = portfolioCurrencyOpt.get()
                    logger.info("Нужной валюты " + portfolioCurrency.currency + " на счету " + portfolioCurrency.balance.toPlainString())
                }
                api.streamingContext.sendRequest(StreamingRequest.subscribeCandle(instrument.figi, candleInterval!!))
            }
            initCleanupProcedure(api, logger)
            val result = CompletableFuture<Void>()
            result.join()
            api.close()
        } catch (ex: Exception) {
            logger.log(Level.SEVERE, "Что-то пошло не так.", ex)
        }
    }

    @Throws(IOException::class)
    private fun initLogger(): Logger {
        val logManager = LogManager.getLogManager()
        val classLoader = App::class.java.classLoader
        classLoader.getResourceAsStream("logging.properties").use { input ->
            if (input == null) {
                throw FileNotFoundException()
            }
            Files.createDirectories(Paths.get("./logs"))
            logManager.readConfiguration(input)
        }
        return Logger.getLogger(App::class.java.name)
    }

    private fun initCleanupProcedure(
        api: OpenApi,
        logger: Logger
    ) {
        Runtime.getRuntime().addShutdownHook(Thread {
            try {
                logger.info("Закрываем соединение... ")
                if (!api.hasClosed()) api.close()
            } catch (e: Exception) {
                logger.log(Level.SEVERE, "Что-то произошло при закрытии соединения!", e)
            }
        })
    }
}