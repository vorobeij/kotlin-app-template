/**
* OpenAPI
* tinkoff.ru/invest OpenAPI.
*
* The version of the OpenAPI document: 1.0.0
* Contact: n.v.melnikov@tinkoff.ru
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools.client.models

import org.openapitools.client.models.BrokerAccountType

import com.squareup.moshi.Json
/**
 * 
 * @param brokerAccountType 
 */

data class SandboxRegisterRequest (
    @Json(name = "brokerAccountType")
    val brokerAccountType: BrokerAccountType? = null
)
