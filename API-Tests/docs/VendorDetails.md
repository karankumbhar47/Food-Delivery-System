# VendorDetails


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**username** | **str** |  | 
**password** | **str** |  | 
**name** | **str** |  | [optional] 
**phone** | **str** |  | 
**email** | **str** |  | [optional] 
**location** | **str** |  | 

## Example

```python
from FoodDeliveryAPI.models.vendor_details import VendorDetails

# TODO update the JSON string below
json = "{}"
# create an instance of VendorDetails from a JSON string
vendor_details_instance = VendorDetails.from_json(json)
# print the JSON string representation of the object
print VendorDetails.to_json()

# convert the object into a dict
vendor_details_dict = vendor_details_instance.to_dict()
# create an instance of VendorDetails from a dict
vendor_details_form_dict = vendor_details.from_dict(vendor_details_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


