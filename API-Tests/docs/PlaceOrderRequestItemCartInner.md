# PlaceOrderRequestItemCartInner


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**item_id** | **str** |  | 
**quantity** | **float** | Quantity of the specific item. | 

## Example

```python
from FoodDeliveryAPI.models.place_order_request_item_cart_inner import PlaceOrderRequestItemCartInner

# TODO update the JSON string below
json = "{}"
# create an instance of PlaceOrderRequestItemCartInner from a JSON string
place_order_request_item_cart_inner_instance = PlaceOrderRequestItemCartInner.from_json(json)
# print the JSON string representation of the object
print PlaceOrderRequestItemCartInner.to_json()

# convert the object into a dict
place_order_request_item_cart_inner_dict = place_order_request_item_cart_inner_instance.to_dict()
# create an instance of PlaceOrderRequestItemCartInner from a dict
place_order_request_item_cart_inner_form_dict = place_order_request_item_cart_inner.from_dict(place_order_request_item_cart_inner_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


