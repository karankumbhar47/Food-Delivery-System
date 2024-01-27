from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from openapi_server.models.base_model import Model
from openapi_server import util


class UserDetails(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, username=None, password=None, name=None, phone=None, email=None, gender=None, dob=None):  # noqa: E501
        """UserDetails - a model defined in OpenAPI

        :param username: The username of this UserDetails.  # noqa: E501
        :type username: str
        :param password: The password of this UserDetails.  # noqa: E501
        :type password: str
        :param name: The name of this UserDetails.  # noqa: E501
        :type name: str
        :param phone: The phone of this UserDetails.  # noqa: E501
        :type phone: float
        :param email: The email of this UserDetails.  # noqa: E501
        :type email: str
        :param gender: The gender of this UserDetails.  # noqa: E501
        :type gender: str
        :param dob: The dob of this UserDetails.  # noqa: E501
        :type dob: str
        """
        self.openapi_types = {
            'username': str,
            'password': str,
            'name': str,
            'phone': float,
            'email': str,
            'gender': str,
            'dob': str
        }

        self.attribute_map = {
            'username': 'username',
            'password': 'password',
            'name': 'name',
            'phone': 'phone',
            'email': 'email',
            'gender': 'gender',
            'dob': 'dob'
        }

        self._username = username
        self._password = password
        self._name = name
        self._phone = phone
        self._email = email
        self._gender = gender
        self._dob = dob

    @classmethod
    def from_dict(cls, dikt) -> 'UserDetails':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The userDetails of this UserDetails.  # noqa: E501
        :rtype: UserDetails
        """
        return util.deserialize_model(dikt, cls)

    @property
    def username(self) -> str:
        """Gets the username of this UserDetails.


        :return: The username of this UserDetails.
        :rtype: str
        """
        return self._username

    @username.setter
    def username(self, username: str):
        """Sets the username of this UserDetails.


        :param username: The username of this UserDetails.
        :type username: str
        """
        if username is None:
            raise ValueError("Invalid value for `username`, must not be `None`")  # noqa: E501

        self._username = username

    @property
    def password(self) -> str:
        """Gets the password of this UserDetails.


        :return: The password of this UserDetails.
        :rtype: str
        """
        return self._password

    @password.setter
    def password(self, password: str):
        """Sets the password of this UserDetails.


        :param password: The password of this UserDetails.
        :type password: str
        """
        if password is None:
            raise ValueError("Invalid value for `password`, must not be `None`")  # noqa: E501

        self._password = password

    @property
    def name(self) -> str:
        """Gets the name of this UserDetails.


        :return: The name of this UserDetails.
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name: str):
        """Sets the name of this UserDetails.


        :param name: The name of this UserDetails.
        :type name: str
        """

        self._name = name

    @property
    def phone(self) -> float:
        """Gets the phone of this UserDetails.


        :return: The phone of this UserDetails.
        :rtype: float
        """
        return self._phone

    @phone.setter
    def phone(self, phone: float):
        """Sets the phone of this UserDetails.


        :param phone: The phone of this UserDetails.
        :type phone: float
        """
        if phone is None:
            raise ValueError("Invalid value for `phone`, must not be `None`")  # noqa: E501

        self._phone = phone

    @property
    def email(self) -> str:
        """Gets the email of this UserDetails.


        :return: The email of this UserDetails.
        :rtype: str
        """
        return self._email

    @email.setter
    def email(self, email: str):
        """Sets the email of this UserDetails.


        :param email: The email of this UserDetails.
        :type email: str
        """

        self._email = email

    @property
    def gender(self) -> str:
        """Gets the gender of this UserDetails.


        :return: The gender of this UserDetails.
        :rtype: str
        """
        return self._gender

    @gender.setter
    def gender(self, gender: str):
        """Sets the gender of this UserDetails.


        :param gender: The gender of this UserDetails.
        :type gender: str
        """
        allowed_values = ["Male", "Female", "Others"]  # noqa: E501
        if gender not in allowed_values:
            raise ValueError(
                "Invalid value for `gender` ({0}), must be one of {1}"
                .format(gender, allowed_values)
            )

        self._gender = gender

    @property
    def dob(self) -> str:
        """Gets the dob of this UserDetails.


        :return: The dob of this UserDetails.
        :rtype: str
        """
        return self._dob

    @dob.setter
    def dob(self, dob: str):
        """Sets the dob of this UserDetails.


        :param dob: The dob of this UserDetails.
        :type dob: str
        """

        self._dob = dob