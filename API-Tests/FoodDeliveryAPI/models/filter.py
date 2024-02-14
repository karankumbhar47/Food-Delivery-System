# coding: utf-8

"""
    FDS

    This API allows to communicate with FDS (Food Delivery System) server. 

    The version of the OpenAPI document: 0.0.3
    Generated by OpenAPI Generator (https://openapi-generator.tech)

    Do not edit the class manually.
"""  # noqa: E501


from __future__ import annotations
import pprint
import re  # noqa: F401
import json


from typing import Any, ClassVar, Dict, List
from pydantic import BaseModel, StrictStr, field_validator
from pydantic import Field
try:
    from typing import Self
except ImportError:
    from typing_extensions import Self

class Filter(BaseModel):
    """
    Filter
    """ # noqa: E501
    var_property: StrictStr = Field(description="What property to apply filter on.", alias="property")
    constrain_type: StrictStr = Field(alias="constrainType")
    value: StrictStr
    __properties: ClassVar[List[str]] = ["property", "constrainType", "value"]

    @field_validator('var_property')
    def var_property_validate_enum(cls, value):
        """Validates the enum"""
        if value not in ('price', 'veg', 'rating', 'vendor', 'category'):
            raise ValueError("must be one of enum values ('price', 'veg', 'rating', 'vendor', 'category')")
        return value

    @field_validator('constrain_type')
    def constrain_type_validate_enum(cls, value):
        """Validates the enum"""
        if value not in ('greaterThan', 'lessThan', 'equals', 'notEquals'):
            raise ValueError("must be one of enum values ('greaterThan', 'lessThan', 'equals', 'notEquals')")
        return value

    model_config = {
        "populate_by_name": True,
        "validate_assignment": True,
        "protected_namespaces": (),
    }


    def to_str(self) -> str:
        """Returns the string representation of the model using alias"""
        return pprint.pformat(self.model_dump(by_alias=True))

    def to_json(self) -> str:
        """Returns the JSON representation of the model using alias"""
        # TODO: pydantic v2: use .model_dump_json(by_alias=True, exclude_unset=True) instead
        return json.dumps(self.to_dict())

    @classmethod
    def from_json(cls, json_str: str) -> Self:
        """Create an instance of Filter from a JSON string"""
        return cls.from_dict(json.loads(json_str))

    def to_dict(self) -> Dict[str, Any]:
        """Return the dictionary representation of the model using alias.

        This has the following differences from calling pydantic's
        `self.model_dump(by_alias=True)`:

        * `None` is only added to the output dict for nullable fields that
          were set at model initialization. Other fields with value `None`
          are ignored.
        """
        _dict = self.model_dump(
            by_alias=True,
            exclude={
            },
            exclude_none=True,
        )
        return _dict

    @classmethod
    def from_dict(cls, obj: Dict) -> Self:
        """Create an instance of Filter from a dict"""
        if obj is None:
            return None

        if not isinstance(obj, dict):
            return cls.model_validate(obj)

        _obj = cls.model_validate({
            "property": obj.get("property"),
            "constrainType": obj.get("constrainType"),
            "value": obj.get("value")
        })
        return _obj

