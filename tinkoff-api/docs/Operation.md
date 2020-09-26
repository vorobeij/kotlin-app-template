
# Operation

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** |  | 
**status** | [**OperationStatus**](OperationStatus.md) |  | 
**currency** | [**Currency**](Currency.md) |  | 
**payment** | **kotlin.Double** |  | 
**isMarginCall** | **kotlin.Boolean** |  | 
**date** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) | ISO8601 | 
**trades** | [**kotlin.Array&lt;OperationTrade&gt;**](OperationTrade.md) |  |  [optional]
**commission** | [**MoneyAmount**](MoneyAmount.md) |  |  [optional]
**price** | **kotlin.Double** |  |  [optional]
**quantity** | **kotlin.Int** | Число инструментов в выставленной заявке |  [optional]
**quantityExecuted** | **kotlin.Int** | Число инструментов, исполненных в заявке |  [optional]
**figi** | **kotlin.String** |  |  [optional]
**instrumentType** | [**InstrumentType**](InstrumentType.md) |  |  [optional]
**operationType** | [**OperationTypeWithCommission**](OperationTypeWithCommission.md) |  |  [optional]



