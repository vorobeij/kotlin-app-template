/**
 * OpenAPI
 * tinkoff.ru/invest OpenAPI.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: n.v.melnikov@tinkoff.ru
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.models

import io.swagger.client.models.Candle
import io.swagger.client.models.CandleResolution

/**
 * 
 * @param figi 
 * @param interval 
 * @param candles 
 */
data class Candles (
    val figi: kotlin.String,
    val interval: CandleResolution,
    val candles: kotlin.Array<Candle>

) {
}