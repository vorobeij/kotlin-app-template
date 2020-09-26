# UserApi

All URIs are relative to *https://api-invest.tinkoff.ru/openapi*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userAccountsGet**](UserApi.md#userAccountsGet) | **GET** /user/accounts | Получение брокерских счетов клиента


<a name="userAccountsGet"></a>
# **userAccountsGet**
> UserAccountsResponse userAccountsGet()

Получение брокерских счетов клиента

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = UserApi()
try {
    val result : UserAccountsResponse = apiInstance.userAccountsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#userAccountsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#userAccountsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserAccountsResponse**](UserAccountsResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

