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

import io.swagger.client.models.OperationType

/**
 * 
 * @param lots 
 * @param operation 
 */
data class MarketOrderRequest (
    val lots: kotlin.Int,
    val operation: OperationType

) {
}