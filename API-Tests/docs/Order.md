# Order


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**order_id** | **str** |  | [optional] 
**customer_name** | **str** |  | [optional] 
**delivery_address** | **str** |  | [optional] 
**pickup_location** | **str** |  | [optional] 
**order_details** | [**List[OrderItem]**](OrderItem.md) |  | [optional] 

## Example

```python
from FoodDeliveryAPI.models.order import Order

# TODO update the JSON string below
json = "{}"
# create an instance of Order from a JSON string
order_instance = Order.from_json(json)
# print the JSON string representation of the object
print Order.to_json()

# convert the object into a dict
order_dict = order_instance.to_dict()
# create an instance of Order from a dict
order_form_dict = order.from_dict(order_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


