# FoodItemFull


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**item_id** | **str** | ID of the item | [optional] 
**item_name** | **str** | Name of the item | [optional] 
**thumbnail_picture** | **str** | 64bit encoded thumbnail picture | [optional] 
**price** | **float** | Price of the item | [optional] 
**vendor_name** | **str** | Name of the vendor | [optional] 
**vendor_location** | **str** | Location of the vendor | [optional] 
**star_rating** | **float** | Star rating of the item | [optional] 
**is_available** | **bool** | Indicates if the item is available | [optional] 
**max_quantity** | **int** | Maximum quantity available | [optional] 
**image_urls** | **List[str]** | URLs of additional images | [optional] 
**tags** | **List[str]** | tags related to food item | [optional] 

## Example

```python
from FoodDeliveryAPI.models.food_item_full import FoodItemFull

# TODO update the JSON string below
json = "{}"
# create an instance of FoodItemFull from a JSON string
food_item_full_instance = FoodItemFull.from_json(json)
# print the JSON string representation of the object
print FoodItemFull.to_json()

# convert the object into a dict
food_item_full_dict = food_item_full_instance.to_dict()
# create an instance of FoodItemFull from a dict
food_item_full_form_dict = food_item_full.from_dict(food_item_full_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


