# VendorEditProductRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**item_id** | **str** |  | 
**name** | **str** |  | [optional] 
**thumbnail** | **str** |  | [optional] 
**price** | **float** |  | [optional] 
**max_quantity** | **float** |  | [optional] 

## Example

```python
from FoodDeliveryAPI.models.vendor_edit_product_request import VendorEditProductRequest

# TODO update the JSON string below
json = "{}"
# create an instance of VendorEditProductRequest from a JSON string
vendor_edit_product_request_instance = VendorEditProductRequest.from_json(json)
# print the JSON string representation of the object
print VendorEditProductRequest.to_json()

# convert the object into a dict
vendor_edit_product_request_dict = vendor_edit_product_request_instance.to_dict()
# create an instance of VendorEditProductRequest from a dict
vendor_edit_product_request_form_dict = vendor_edit_product_request.from_dict(vendor_edit_product_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


