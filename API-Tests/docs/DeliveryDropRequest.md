# DeliveryDropRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**order_id** | **str** |  | [optional] 
**otp** | **int** |  | [optional] 

## Example

```python
from FoodDeliveryAPI.models.delivery_drop_request import DeliveryDropRequest

# TODO update the JSON string below
json = "{}"
# create an instance of DeliveryDropRequest from a JSON string
delivery_drop_request_instance = DeliveryDropRequest.from_json(json)
# print the JSON string representation of the object
print DeliveryDropRequest.to_json()

# convert the object into a dict
delivery_drop_request_dict = delivery_drop_request_instance.to_dict()
# create an instance of DeliveryDropRequest from a dict
delivery_drop_request_form_dict = delivery_drop_request.from_dict(delivery_drop_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


