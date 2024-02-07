# VendorAddProductImagesRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**item_id** | **str** |  | 
**image_urls** | **List[str]** |  | [optional] 

## Example

```python
from FoodDeliveryAPI.models.vendor_add_product_images_request import VendorAddProductImagesRequest

# TODO update the JSON string below
json = "{}"
# create an instance of VendorAddProductImagesRequest from a JSON string
vendor_add_product_images_request_instance = VendorAddProductImagesRequest.from_json(json)
# print the JSON string representation of the object
print VendorAddProductImagesRequest.to_json()

# convert the object into a dict
vendor_add_product_images_request_dict = vendor_add_product_images_request_instance.to_dict()
# create an instance of VendorAddProductImagesRequest from a dict
vendor_add_product_images_request_form_dict = vendor_add_product_images_request.from_dict(vendor_add_product_images_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


