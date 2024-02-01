# PlaceOrderRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**item_cart** | [**List[PlaceOrderRequestItemCartInner]**](PlaceOrderRequestItemCartInner.md) |  | 
**location** | **str** | Delivery location. | 

## Example

```python
from FoodDeliveryAPI.models.place_order_request import PlaceOrderRequest

# TODO update the JSON string below
json = "{}"
# create an instance of PlaceOrderRequest from a JSON string
place_order_request_instance = PlaceOrderRequest.from_json(json)
# print the JSON string representation of the object
print PlaceOrderRequest.to_json()

# convert the object into a dict
place_order_request_dict = place_order_request_instance.to_dict()
# create an instance of PlaceOrderRequest from a dict
place_order_request_form_dict = place_order_request.from_dict(place_order_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


