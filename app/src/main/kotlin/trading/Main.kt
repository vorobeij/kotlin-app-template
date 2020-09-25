package trading

import io.swagger.client.apis.MarketApi

fun main() {
    log(MarketApi().marketBondsGet().payload.instruments)
}