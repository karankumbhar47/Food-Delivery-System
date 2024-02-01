# VendorAddProductRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**name** | **str** |  | 
**thumbnail** | **str** |  | [optional] 
**price** | **float** |  | 
**max_quantity** | **float** |  | [optional] 
**image_urls** | **List[str]** |  | [optional] 
**tags** | **List[str]** | tags related to food item | [optional] 

## Example

```python
from FoodDeliveryAPI.models.vendor_add_product_request import VendorAddProductRequest

# TODO update the JSON string below
json = "{}"
# create an instance of VendorAddProductRequest from a JSON string
vendor_add_product_request_instance = VendorAddProductRequest.from_json(json)
# print the JSON string representation of the object
print VendorAddProductRequest.to_json()

# convert the object into a dict
vendor_add_product_request_dict = vendor_add_product_request_instance.to_dict()
# create an instance of VendorAddProductRequest from a dict
vendor_add_product_request_form_dict = vendor_add_product_request.from_dict(vendor_add_product_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


