# io.swagger.client - Kotlin client library for OpenAPI

## Requires

* Kotlin 1.1.2
* Gradle 3.3

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in Swagger definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *https://api-invest.tinkoff.ru/openapi/*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*MarketApi* | [**marketBondsGet**](docs/MarketApi.md#marketbondsget) | **GET** /market/bonds | Получение списка облигаций
*MarketApi* | [**marketCandlesGet**](docs/MarketApi.md#marketcandlesget) | **GET** /market/candles | Получение исторических свечей по FIGI
*MarketApi* | [**marketCurrenciesGet**](docs/MarketApi.md#marketcurrenciesget) | **GET** /market/currencies | Получение списка валютных пар
*MarketApi* | [**marketEtfsGet**](docs/MarketApi.md#marketetfsget) | **GET** /market/etfs | Получение списка ETF
*MarketApi* | [**marketOrderbookGet**](docs/MarketApi.md#marketorderbookget) | **GET** /market/orderbook | Получение стакана по FIGI
*MarketApi* | [**marketSearchByFigiGet**](docs/MarketApi.md#marketsearchbyfigiget) | **GET** /market/search/by-figi | Получение инструмента по FIGI
*MarketApi* | [**marketSearchByTickerGet**](docs/MarketApi.md#marketsearchbytickerget) | **GET** /market/search/by-ticker | Получение инструмента по тикеру
*MarketApi* | [**marketStocksGet**](docs/MarketApi.md#marketstocksget) | **GET** /market/stocks | Получение списка акций
*OperationsApi* | [**operationsGet**](docs/OperationsApi.md#operationsget) | **GET** /operations | Получение списка операций
*OrdersApi* | [**ordersCancelPost**](docs/OrdersApi.md#orderscancelpost) | **POST** /orders/cancel | Отмена заявки
*OrdersApi* | [**ordersGet**](docs/OrdersApi.md#ordersget) | **GET** /orders | Получение списка активных заявок
*OrdersApi* | [**ordersLimitOrderPost**](docs/OrdersApi.md#orderslimitorderpost) | **POST** /orders/limit-order | Создание лимитной заявки
*OrdersApi* | [**ordersMarketOrderPost**](docs/OrdersApi.md#ordersmarketorderpost) | **POST** /orders/market-order | Создание рыночной заявки
*PortfolioApi* | [**portfolioCurrenciesGet**](docs/PortfolioApi.md#portfoliocurrenciesget) | **GET** /portfolio/currencies | Получение валютных активов клиента
*PortfolioApi* | [**portfolioGet**](docs/PortfolioApi.md#portfolioget) | **GET** /portfolio | Получение портфеля клиента
*SandboxApi* | [**sandboxClearPost**](docs/SandboxApi.md#sandboxclearpost) | **POST** /sandbox/clear | Удаление всех позиций
*SandboxApi* | [**sandboxCurrenciesBalancePost**](docs/SandboxApi.md#sandboxcurrenciesbalancepost) | **POST** /sandbox/currencies/balance | Выставление баланса по валютным позициям
*SandboxApi* | [**sandboxPositionsBalancePost**](docs/SandboxApi.md#sandboxpositionsbalancepost) | **POST** /sandbox/positions/balance | Выставление баланса по инструментным позициям
*SandboxApi* | [**sandboxRegisterPost**](docs/SandboxApi.md#sandboxregisterpost) | **POST** /sandbox/register | Регистрация клиента в sandbox
*SandboxApi* | [**sandboxRemovePost**](docs/SandboxApi.md#sandboxremovepost) | **POST** /sandbox/remove | Удаление счета
*UserApi* | [**userAccountsGet**](docs/UserApi.md#useraccountsget) | **GET** /user/accounts | Получение брокерских счетов клиента

<a name="documentation-for-models"></a>
## Documentation for Models

 - [io.swagger.client.models.BrokerAccountType](docs/BrokerAccountType.md)
 - [io.swagger.client.models.Candle](docs/Candle.md)
 - [io.swagger.client.models.CandleResolution](docs/CandleResolution.md)
 - [io.swagger.client.models.Candles](docs/Candles.md)
 - [io.swagger.client.models.CandlesResponse](docs/CandlesResponse.md)
 - [io.swagger.client.models.Currencies](docs/Currencies.md)
 - [io.swagger.client.models.Currency](docs/Currency.md)
 - [io.swagger.client.models.CurrencyPosition](docs/CurrencyPosition.md)
 - [io.swagger.client.models.Empty](docs/Empty.md)
 - [io.swagger.client.models.Error](docs/Error.md)
 - [io.swagger.client.models.ErrorPayload](docs/ErrorPayload.md)
 - [io.swagger.client.models.InstrumentType](docs/InstrumentType.md)
 - [io.swagger.client.models.LimitOrderRequest](docs/LimitOrderRequest.md)
 - [io.swagger.client.models.LimitOrderResponse](docs/LimitOrderResponse.md)
 - [io.swagger.client.models.MarketInstrument](docs/MarketInstrument.md)
 - [io.swagger.client.models.MarketInstrumentList](docs/MarketInstrumentList.md)
 - [io.swagger.client.models.MarketInstrumentListResponse](docs/MarketInstrumentListResponse.md)
 - [io.swagger.client.models.MarketInstrumentResponse](docs/MarketInstrumentResponse.md)
 - [io.swagger.client.models.MarketOrderRequest](docs/MarketOrderRequest.md)
 - [io.swagger.client.models.MarketOrderResponse](docs/MarketOrderResponse.md)
 - [io.swagger.client.models.MoneyAmount](docs/MoneyAmount.md)
 - [io.swagger.client.models.Operation](docs/Operation.md)
 - [io.swagger.client.models.OperationStatus](docs/OperationStatus.md)
 - [io.swagger.client.models.OperationTrade](docs/OperationTrade.md)
 - [io.swagger.client.models.OperationType](docs/OperationType.md)
 - [io.swagger.client.models.OperationTypeWithCommission](docs/OperationTypeWithCommission.md)
 - [io.swagger.client.models.Operations](docs/Operations.md)
 - [io.swagger.client.models.OperationsResponse](docs/OperationsResponse.md)
 - [io.swagger.client.models.Order](docs/Order.md)
 - [io.swagger.client.models.OrderResponse](docs/OrderResponse.md)
 - [io.swagger.client.models.OrderStatus](docs/OrderStatus.md)
 - [io.swagger.client.models.OrderType](docs/OrderType.md)
 - [io.swagger.client.models.Orderbook](docs/Orderbook.md)
 - [io.swagger.client.models.OrderbookResponse](docs/OrderbookResponse.md)
 - [io.swagger.client.models.OrdersResponse](docs/OrdersResponse.md)
 - [io.swagger.client.models.PlacedLimitOrder](docs/PlacedLimitOrder.md)
 - [io.swagger.client.models.PlacedMarketOrder](docs/PlacedMarketOrder.md)
 - [io.swagger.client.models.Portfolio](docs/Portfolio.md)
 - [io.swagger.client.models.PortfolioCurrenciesResponse](docs/PortfolioCurrenciesResponse.md)
 - [io.swagger.client.models.PortfolioPosition](docs/PortfolioPosition.md)
 - [io.swagger.client.models.PortfolioResponse](docs/PortfolioResponse.md)
 - [io.swagger.client.models.SandboxAccount](docs/SandboxAccount.md)
 - [io.swagger.client.models.SandboxCurrency](docs/SandboxCurrency.md)
 - [io.swagger.client.models.SandboxRegisterRequest](docs/SandboxRegisterRequest.md)
 - [io.swagger.client.models.SandboxRegisterResponse](docs/SandboxRegisterResponse.md)
 - [io.swagger.client.models.SandboxSetCurrencyBalanceRequest](docs/SandboxSetCurrencyBalanceRequest.md)
 - [io.swagger.client.models.SandboxSetPositionBalanceRequest](docs/SandboxSetPositionBalanceRequest.md)
 - [io.swagger.client.models.SearchMarketInstrument](docs/SearchMarketInstrument.md)
 - [io.swagger.client.models.SearchMarketInstrumentResponse](docs/SearchMarketInstrumentResponse.md)
 - [io.swagger.client.models.TradeStatus](docs/TradeStatus.md)
 - [io.swagger.client.models.UserAccount](docs/UserAccount.md)
 - [io.swagger.client.models.UserAccounts](docs/UserAccounts.md)
 - [io.swagger.client.models.UserAccountsResponse](docs/UserAccountsResponse.md)

<a name="documentation-for-authorization"></a>
## Documentation for Authorization

<a name="sso_auth"></a>
### sso_auth


