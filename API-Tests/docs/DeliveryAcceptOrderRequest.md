# DeliveryAcceptOrderRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**order_id** | **str** |  | [optional] 

## Example

```python
from FoodDeliveryAPI.models.delivery_accept_order_request import DeliveryAcceptOrderRequest

# TODO update the JSON string below
json = "{}"
# create an instance of DeliveryAcceptOrderRequest from a JSON string
delivery_accept_order_request_instance = DeliveryAcceptOrderRequest.from_json(json)
# print the JSON string representation of the object
print DeliveryAcceptOrderRequest.to_json()

# convert the object into a dict
delivery_accept_order_request_dict = delivery_accept_order_request_instance.to_dict()
# create an instance of DeliveryAcceptOrderRequest from a dict
delivery_accept_order_request_form_dict = delivery_accept_order_request.from_dict(delivery_accept_order_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


