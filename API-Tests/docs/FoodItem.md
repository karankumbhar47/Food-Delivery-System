# FoodItem


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**item_id** | **str** |  | 
**name** | **str** |  | 
**thumbnail** | **str** |  | [optional] 
**vendor** | **str** |  | 
**price** | **float** |  | 
**rating** | **float** |  | [optional] 
**rated_by** | **float** |  | [optional] 
**tags** | **List[str]** | tags related to food item | [optional] 

## Example

```python
from FoodDeliveryAPI.models.food_item import FoodItem

# TODO update the JSON string below
json = "{}"
# create an instance of FoodItem from a JSON string
food_item_instance = FoodItem.from_json(json)
# print the JSON string representation of the object
print FoodItem.to_json()

# convert the object into a dict
food_item_dict = food_item_instance.to_dict()
# create an instance of FoodItem from a dict
food_item_form_dict = food_item.from_dict(food_item_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


