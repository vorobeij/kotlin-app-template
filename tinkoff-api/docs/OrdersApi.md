# OrdersApi

All URIs are relative to *https://api-invest.tinkoff.ru/openapi*

Method | HTTP request | Description
------------- | ------------- | -------------
[**ordersCancelPost**](OrdersApi.md#ordersCancelPost) | **POST** /orders/cancel | Отмена заявки
[**ordersGet**](OrdersApi.md#ordersGet) | **GET** /orders | Получение списка активных заявок
[**ordersLimitOrderPost**](OrdersApi.md#ordersLimitOrderPost) | **POST** /orders/limit-order | Создание лимитной заявки
[**ordersMarketOrderPost**](OrdersApi.md#ordersMarketOrderPost) | **POST** /orders/market-order | Создание рыночной заявки


<a name="ordersCancelPost"></a>
# **ordersCancelPost**
> Empty ordersCancelPost(orderId, brokerAccountId)

Отмена заявки

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = OrdersApi()
val orderId : kotlin.String = orderId_example // kotlin.String | ID заявки
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : Empty = apiInstance.ordersCancelPost(orderId, brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OrdersApi#ordersCancelPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OrdersApi#ordersCancelPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **kotlin.String**| ID заявки |
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**Empty**](Empty.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ordersGet"></a>
# **ordersGet**
> OrdersResponse ordersGet(brokerAccountId)

Получение списка активных заявок

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = OrdersApi()
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : OrdersResponse = apiInstance.ordersGet(brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OrdersApi#ordersGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OrdersApi#ordersGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**OrdersResponse**](OrdersResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ordersLimitOrderPost"></a>
# **ordersLimitOrderPost**
> LimitOrderResponse ordersLimitOrderPost(figi, limitOrderRequest, brokerAccountId)

Создание лимитной заявки

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = OrdersApi()
val figi : kotlin.String = figi_example // kotlin.String | FIGI инструмента
val limitOrderRequest : LimitOrderRequest =  // LimitOrderRequest | 
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : LimitOrderResponse = apiInstance.ordersLimitOrderPost(figi, limitOrderRequest, brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OrdersApi#ordersLimitOrderPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OrdersApi#ordersLimitOrderPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **figi** | **kotlin.String**| FIGI инструмента |
 **limitOrderRequest** | [**LimitOrderRequest**](LimitOrderRequest.md)|  |
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**LimitOrderResponse**](LimitOrderResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ordersMarketOrderPost"></a>
# **ordersMarketOrderPost**
> MarketOrderResponse ordersMarketOrderPost(figi, marketOrderRequest, brokerAccountId)

Создание рыночной заявки

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = OrdersApi()
val figi : kotlin.String = figi_example // kotlin.String | FIGI инструмента
val marketOrderRequest : MarketOrderRequest =  // MarketOrderRequest | 
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Уникальный идентификатор счета (по умолчанию - Тинькофф)
try {
    val result : MarketOrderResponse = apiInstance.ordersMarketOrderPost(figi, marketOrderRequest, brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OrdersApi#ordersMarketOrderPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OrdersApi#ordersMarketOrderPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **figi** | **kotlin.String**| FIGI инструмента |
 **marketOrderRequest** | [**MarketOrderRequest**](MarketOrderRequest.md)|  |
 **brokerAccountId** | **kotlin.String**| Уникальный идентификатор счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**MarketOrderResponse**](MarketOrderResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

