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


from typing import Any, ClassVar, Dict, List, Optional
from pydantic import BaseModel, StrictStr, field_validator
try:
    from typing import Self
except ImportError:
    from typing_extensions import Self

class Profile(BaseModel):
    """
    Profile
    """ # noqa: E501
    username: Optional[StrictStr] = None
    name: Optional[StrictStr] = None
    phone: Optional[StrictStr] = None
    email: Optional[StrictStr] = None
    gender: Optional[StrictStr] = None
    dob: Optional[StrictStr] = None
    __properties: ClassVar[List[str]] = ["username", "name", "phone", "email", "gender", "dob"]

    @field_validator('gender')
    def gender_validate_enum(cls, value):
        """Validates the enum"""
        if value is None:
            return value

        if value not in ('Male', 'Female', 'Others'):
            raise ValueError("must be one of enum values ('Male', 'Female', 'Others')")
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
        """Create an instance of Profile from a JSON string"""
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
        """Create an instance of Profile from a dict"""
        if obj is None:
            return None

        if not isinstance(obj, dict):
            return cls.model_validate(obj)

        _obj = cls.model_validate({
            "username": obj.get("username"),
            "name": obj.get("name"),
            "phone": obj.get("phone"),
            "email": obj.get("email"),
            "gender": obj.get("gender"),
            "dob": obj.get("dob")
        })
        return _obj


