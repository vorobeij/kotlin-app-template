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

import io.swagger.client.models.OrderResponse
import io.swagger.client.models.TradeStatus

/**
 * 
 * @param figi 
 * @param depth 
 * @param bids 
 * @param asks 
 * @param tradeStatus 
 * @param minPriceIncrement Шаг цены
 * @param faceValue Номинал для облигаций
 * @param lastPrice 
 * @param closePrice 
 * @param limitUp Верхняя граница цены
 * @param limitDown Нижняя граница цены
 */
data class Orderbook (
    val figi: kotlin.String,
    val depth: kotlin.Int,
    val bids: kotlin.Array<OrderResponse>,
    val asks: kotlin.Array<OrderResponse>,
    val tradeStatus: TradeStatus,
    /* Шаг цены */
    val minPriceIncrement: kotlin.Double
,
    /* Номинал для облигаций */
    val faceValue: kotlin.Double? = null,
    val lastPrice: kotlin.Double? = null,
    val closePrice: kotlin.Double? = null,
    /* Верхняя граница цены */
    val limitUp: kotlin.Double? = null,
    /* Нижняя граница цены */
    val limitDown: kotlin.Double? = null
) {
}