package trading.infrastructure

import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.SandboxOpenApi
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory
import trading.models.AuthParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.logging.Level
import java.util.logging.Logger

class ApiInteractor {

    fun runSafely(
        parameters: AuthParameters,
        block: (api: OpenApi) -> Unit
    ) {
        val factory = OkHttpOpenApiFactory(parameters.ssoToken, logger)

        var api: OpenApi? = null

        try {
            logger.info("Создаём подключение... ")
            if (parameters.sandboxMode) {
                api = factory.createSandboxOpenApiClient(Executors.newSingleThreadExecutor())
                // ОБЯЗАТЕЛЬНО нужно выполнить регистрацию в "песочнице"
                (api as SandboxOpenApi).sandboxContext.performRegistration(null).join()
            } else {
                api = factory.createOpenApiClient(Executors.newSingleThreadExecutor())
            }

            block(api)

            initCleanupProcedure(api, logger)
            val result = CompletableFuture<Void>()
            result.join()
            api.close()
        } catch (ex: Exception) {
            logger.log(Level.SEVERE, "Что-то пошло не так.", ex)
            api?.close()
            System.exit(-1)
        }
    }

    private fun initCleanupProcedure(
        api: OpenApi,
        logger: Logger
    ) {
        Runtime.getRuntime().addShutdownHook(
            Thread {
                try {
                    logger.info("Закрываем соединение... ")
                    if (!api.hasClosed()) api.close()
                } catch (e: Exception) {
                    logger.log(Level.SEVERE, "Что-то произошло при закрытии соединения!", e)
                }
            }
        )
    }
}
