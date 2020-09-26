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


import com.squareup.moshi.Json

/**
* 
* Values: buy,sell
*/

enum class OperationType(val value: kotlin.String){


    @Json(name = "Buy")
    buy("Buy"),


    @Json(name = "Sell")
    sell("Sell");



	/**
	This override toString avoids using the enum var name and uses the actual api value instead.
	In cases the var name and value are different, the client would send incorrect enums to the server.
	**/
	override fun toString(): String {
        return value
    }

}
