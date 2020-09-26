# OperationsApi

All URIs are relative to *https://api-invest.tinkoff.ru/openapi*

Method | HTTP request | Description
------------- | ------------- | -------------
[**operationsGet**](OperationsApi.md#operationsGet) | **GET** /operations | Получение списка операций


<a name="operationsGet"></a>
# **operationsGet**
> OperationsResponse operationsGet(from, to, figi, brokerAccountId)

Получение списка операций

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = OperationsApi()
val from : java.time.OffsetDateTime = 2019-08-19T18:38:33.131642+03:00 // java.time.OffsetDateTime | Начало временного промежутка
val to : java.time.OffsetDateTime = 2019-08-19T18:38:33.131642+03:00 // java.time.OffsetDateTime | Конец временного промежутка
val figi : kotlin.String = figi_example // kotlin.String | Figi инструмента для фильтрации
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : OperationsResponse = apiInstance.operationsGet(from, to, figi, brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OperationsApi#operationsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OperationsApi#operationsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from** | **java.time.OffsetDateTime**| Начало временного промежутка |
 **to** | **java.time.OffsetDateTime**| Конец временного промежутка |
 **figi** | **kotlin.String**| Figi инструмента для фильтрации | [optional]
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**OperationsResponse**](OperationsResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

