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

import io.swagger.client.models.MoneyAmount
import io.swagger.client.models.OperationType
import io.swagger.client.models.OrderStatus

/**
 * 
 * @param orderId 
 * @param operation 
 * @param status 
 * @param rejectReason 
 * @param message Сообщение об ошибке
 * @param requestedLots 
 * @param executedLots 
 * @param commission 
 */
data class PlacedMarketOrder (
    val orderId: kotlin.String,
    val operation: OperationType,
    val status: OrderStatus,
    val requestedLots: kotlin.Int,
    val executedLots: kotlin.Int
,
    val rejectReason: kotlin.String? = null,
    /* Сообщение об ошибке */
    val message: kotlin.String? = null,
    val commission: MoneyAmount? = null
) {
}