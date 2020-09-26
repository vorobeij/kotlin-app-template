# SandboxApi

All URIs are relative to *https://api-invest.tinkoff.ru/openapi*

Method | HTTP request | Description
------------- | ------------- | -------------
[**sandboxClearPost**](SandboxApi.md#sandboxClearPost) | **POST** /sandbox/clear | Удаление всех позиций
[**sandboxCurrenciesBalancePost**](SandboxApi.md#sandboxCurrenciesBalancePost) | **POST** /sandbox/currencies/balance | Выставление баланса по валютным позициям
[**sandboxPositionsBalancePost**](SandboxApi.md#sandboxPositionsBalancePost) | **POST** /sandbox/positions/balance | Выставление баланса по инструментным позициям
[**sandboxRegisterPost**](SandboxApi.md#sandboxRegisterPost) | **POST** /sandbox/register | Регистрация клиента в sandbox
[**sandboxRemovePost**](SandboxApi.md#sandboxRemovePost) | **POST** /sandbox/remove | Удаление счета


<a name="sandboxClearPost"></a>
# **sandboxClearPost**
> Empty sandboxClearPost(brokerAccountId)

Удаление всех позиций

Удаление всех позиций клиента

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SandboxApi()
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : Empty = apiInstance.sandboxClearPost(brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SandboxApi#sandboxClearPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SandboxApi#sandboxClearPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**Empty**](Empty.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="sandboxCurrenciesBalancePost"></a>
# **sandboxCurrenciesBalancePost**
> Empty sandboxCurrenciesBalancePost(sandboxSetCurrencyBalanceRequest, brokerAccountId)

Выставление баланса по валютным позициям

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SandboxApi()
val sandboxSetCurrencyBalanceRequest : SandboxSetCurrencyBalanceRequest =  // SandboxSetCurrencyBalanceRequest | Запрос на выставление баланса по валютным позициям
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : Empty = apiInstance.sandboxCurrenciesBalancePost(sandboxSetCurrencyBalanceRequest, brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SandboxApi#sandboxCurrenciesBalancePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SandboxApi#sandboxCurrenciesBalancePost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sandboxSetCurrencyBalanceRequest** | [**SandboxSetCurrencyBalanceRequest**](SandboxSetCurrencyBalanceRequest.md)| Запрос на выставление баланса по валютным позициям |
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**Empty**](Empty.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="sandboxPositionsBalancePost"></a>
# **sandboxPositionsBalancePost**
> Empty sandboxPositionsBalancePost(sandboxSetPositionBalanceRequest, brokerAccountId)

Выставление баланса по инструментным позициям

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SandboxApi()
val sandboxSetPositionBalanceRequest : SandboxSetPositionBalanceRequest =  // SandboxSetPositionBalanceRequest | Запрос на выставление баланса по инструментным позициям
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : Empty = apiInstance.sandboxPositionsBalancePost(sandboxSetPositionBalanceRequest, brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SandboxApi#sandboxPositionsBalancePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SandboxApi#sandboxPositionsBalancePost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sandboxSetPositionBalanceRequest** | [**SandboxSetPositionBalanceRequest**](SandboxSetPositionBalanceRequest.md)| Запрос на выставление баланса по инструментным позициям |
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**Empty**](Empty.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="sandboxRegisterPost"></a>
# **sandboxRegisterPost**
> SandboxRegisterResponse sandboxRegisterPost(sandboxRegisterRequest)

Регистрация клиента в sandbox

Создание счета и валютных позиций для клиента

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SandboxApi()
val sandboxRegisterRequest : SandboxRegisterRequest =  // SandboxRegisterRequest | Запрос на создание счета и выставление баланса по валютным позициям
try {
    val result : SandboxRegisterResponse = apiInstance.sandboxRegisterPost(sandboxRegisterRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SandboxApi#sandboxRegisterPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SandboxApi#sandboxRegisterPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sandboxRegisterRequest** | [**SandboxRegisterRequest**](SandboxRegisterRequest.md)| Запрос на создание счета и выставление баланса по валютным позициям | [optional]

### Return type

[**SandboxRegisterResponse**](SandboxRegisterResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="sandboxRemovePost"></a>
# **sandboxRemovePost**
> Empty sandboxRemovePost(brokerAccountId)

Удаление счета

Удаление счета клиента

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SandboxApi()
val brokerAccountId : kotlin.String = brokerAccountId_example // kotlin.String | Номер счета (по умолчанию - Тинькофф)
try {
    val result : Empty = apiInstance.sandboxRemovePost(brokerAccountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SandboxApi#sandboxRemovePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SandboxApi#sandboxRemovePost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **brokerAccountId** | **kotlin.String**| Номер счета (по умолчанию - Тинькофф) | [optional]

### Return type

[**Empty**](Empty.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

