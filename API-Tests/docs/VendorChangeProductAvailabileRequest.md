# VendorChangeProductAvailabileRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**item_id** | **str** |  | 
**status** | **bool** | Weather the item is available or not | 

## Example

```python
from FoodDeliveryAPI.models.vendor_change_product_availabile_request import VendorChangeProductAvailabileRequest

# TODO update the JSON string below
json = "{}"
# create an instance of VendorChangeProductAvailabileRequest from a JSON string
vendor_change_product_availabile_request_instance = VendorChangeProductAvailabileRequest.from_json(json)
# print the JSON string representation of the object
print VendorChangeProductAvailabileRequest.to_json()

# convert the object into a dict
vendor_change_product_availabile_request_dict = vendor_change_product_availabile_request_instance.to_dict()
# create an instance of VendorChangeProductAvailabileRequest from a dict
vendor_change_product_availabile_request_form_dict = vendor_change_product_availabile_request.from_dict(vendor_change_product_availabile_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


