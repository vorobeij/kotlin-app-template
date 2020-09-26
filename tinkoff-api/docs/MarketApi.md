# MarketApi

All URIs are relative to *https://api-invest.tinkoff.ru/openapi*

Method | HTTP request | Description
------------- | ------------- | -------------
[**marketBondsGet**](MarketApi.md#marketBondsGet) | **GET** /market/bonds | Получение списка облигаций
[**marketCandlesGet**](MarketApi.md#marketCandlesGet) | **GET** /market/candles | Получение исторических свечей по FIGI
[**marketCurrenciesGet**](MarketApi.md#marketCurrenciesGet) | **GET** /market/currencies | Получение списка валютных пар
[**marketEtfsGet**](MarketApi.md#marketEtfsGet) | **GET** /market/etfs | Получение списка ETF
[**marketOrderbookGet**](MarketApi.md#marketOrderbookGet) | **GET** /market/orderbook | Получение стакана по FIGI
[**marketSearchByFigiGet**](MarketApi.md#marketSearchByFigiGet) | **GET** /market/search/by-figi | Получение инструмента по FIGI
[**marketSearchByTickerGet**](MarketApi.md#marketSearchByTickerGet) | **GET** /market/search/by-ticker | Получение инструмента по тикеру
[**marketStocksGet**](MarketApi.md#marketStocksGet) | **GET** /market/stocks | Получение списка акций


<a name="marketBondsGet"></a>
# **marketBondsGet**
> MarketInstrumentListResponse marketBondsGet()

Получение списка облигаций

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
try {
    val result : MarketInstrumentListResponse = apiInstance.marketBondsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketBondsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketBondsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**MarketInstrumentListResponse**](MarketInstrumentListResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="marketCandlesGet"></a>
# **marketCandlesGet**
> CandlesResponse marketCandlesGet(figi, from, to, interval)

Получение исторических свечей по FIGI

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
val figi : kotlin.String = figi_example // kotlin.String | FIGI
val from : java.time.OffsetDateTime = 2019-08-19T18:38:33.131642+03:00 // java.time.OffsetDateTime | Начало временного промежутка
val to : java.time.OffsetDateTime = 2019-08-19T18:38:33.131642+03:00 // java.time.OffsetDateTime | Конец временного промежутка
val interval : CandleResolution =  // CandleResolution | Интервал свечи
try {
    val result : CandlesResponse = apiInstance.marketCandlesGet(figi, from, to, interval)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketCandlesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketCandlesGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **figi** | **kotlin.String**| FIGI |
 **from** | **java.time.OffsetDateTime**| Начало временного промежутка |
 **to** | **java.time.OffsetDateTime**| Конец временного промежутка |
 **interval** | [**CandleResolution**](.md)| Интервал свечи | [enum: 1min, 2min, 3min, 5min, 10min, 15min, 30min, hour, day, week, month]

### Return type

[**CandlesResponse**](CandlesResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="marketCurrenciesGet"></a>
# **marketCurrenciesGet**
> MarketInstrumentListResponse marketCurrenciesGet()

Получение списка валютных пар

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
try {
    val result : MarketInstrumentListResponse = apiInstance.marketCurrenciesGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketCurrenciesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketCurrenciesGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**MarketInstrumentListResponse**](MarketInstrumentListResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="marketEtfsGet"></a>
# **marketEtfsGet**
> MarketInstrumentListResponse marketEtfsGet()

Получение списка ETF

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
try {
    val result : MarketInstrumentListResponse = apiInstance.marketEtfsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketEtfsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketEtfsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**MarketInstrumentListResponse**](MarketInstrumentListResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="marketOrderbookGet"></a>
# **marketOrderbookGet**
> OrderbookResponse marketOrderbookGet(figi, depth)

Получение стакана по FIGI

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
val figi : kotlin.String = figi_example // kotlin.String | FIGI
val depth : kotlin.Int = 56 // kotlin.Int | Глубина стакана [1..20]
try {
    val result : OrderbookResponse = apiInstance.marketOrderbookGet(figi, depth)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketOrderbookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketOrderbookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **figi** | **kotlin.String**| FIGI |
 **depth** | **kotlin.Int**| Глубина стакана [1..20] |

### Return type

[**OrderbookResponse**](OrderbookResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="marketSearchByFigiGet"></a>
# **marketSearchByFigiGet**
> SearchMarketInstrumentResponse marketSearchByFigiGet(figi)

Получение инструмента по FIGI

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
val figi : kotlin.String = figi_example // kotlin.String | FIGI
try {
    val result : SearchMarketInstrumentResponse = apiInstance.marketSearchByFigiGet(figi)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketSearchByFigiGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketSearchByFigiGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **figi** | **kotlin.String**| FIGI |

### Return type

[**SearchMarketInstrumentResponse**](SearchMarketInstrumentResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="marketSearchByTickerGet"></a>
# **marketSearchByTickerGet**
> MarketInstrumentListResponse marketSearchByTickerGet(ticker)

Получение инструмента по тикеру

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
val ticker : kotlin.String = ticker_example // kotlin.String | Тикер инструмента
try {
    val result : MarketInstrumentListResponse = apiInstance.marketSearchByTickerGet(ticker)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketSearchByTickerGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketSearchByTickerGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ticker** | **kotlin.String**| Тикер инструмента |

### Return type

[**MarketInstrumentListResponse**](MarketInstrumentListResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="marketStocksGet"></a>
# **marketStocksGet**
> MarketInstrumentListResponse marketStocksGet()

Получение списка акций

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketApi()
try {
    val result : MarketInstrumentListResponse = apiInstance.marketStocksGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketApi#marketStocksGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketApi#marketStocksGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**MarketInstrumentListResponse**](MarketInstrumentListResponse.md)

### Authorization


Configure sso_auth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

