package trading.di

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.koin.dsl.module
import ru.tinkoff.invest.openapi.OpenApi
import trading.api.MarketApi
import trading.api.MarketApiImpl
import trading.cache.MarketCache
import trading.cache.MarketCacheImpl
import trading.repository.MarketRepository
import trading.statisics.printer.ConsoleHistogramPrinter
import trading.statisics.printer.HistogramPrinter

fun marketModule(api: OpenApi) = module {
    single<OpenApi> { api }
    single<ObjectMapper> {
        ObjectMapper().apply {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            registerModule(JavaTimeModule())
            enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        }
    }
    factory<MarketApi> { MarketApiImpl(get()) }
    factory<MarketCache> { MarketCacheImpl("caches", get()) }
    factory<MarketRepository> { MarketRepository(get(), get()) }
    factory<HistogramPrinter> { ConsoleHistogramPrinter() }
}
