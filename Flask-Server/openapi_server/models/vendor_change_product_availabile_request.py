from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from openapi_server.models.base_model import Model
from openapi_server import util


class VendorChangeProductAvailabileRequest(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, item_id=None, status=None):  # noqa: E501
        """VendorChangeProductAvailabileRequest - a model defined in OpenAPI

        :param item_id: The item_id of this VendorChangeProductAvailabileRequest.  # noqa: E501
        :type item_id: str
        :param status: The status of this VendorChangeProductAvailabileRequest.  # noqa: E501
        :type status: bool
        """
        self.openapi_types = {
            'item_id': str,
            'status': bool
        }

        self.attribute_map = {
            'item_id': 'itemId',
            'status': 'status'
        }

        self._item_id = item_id
        self._status = status

    @classmethod
    def from_dict(cls, dikt) -> 'VendorChangeProductAvailabileRequest':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The vendorChangeProductAvailabile_request of this VendorChangeProductAvailabileRequest.  # noqa: E501
        :rtype: VendorChangeProductAvailabileRequest
        """
        return util.deserialize_model(dikt, cls)

    @property
    def item_id(self) -> str:
        """Gets the item_id of this VendorChangeProductAvailabileRequest.


        :return: The item_id of this VendorChangeProductAvailabileRequest.
        :rtype: str
        """
        return self._item_id

    @item_id.setter
    def item_id(self, item_id: str):
        """Sets the item_id of this VendorChangeProductAvailabileRequest.


        :param item_id: The item_id of this VendorChangeProductAvailabileRequest.
        :type item_id: str
        """
        if item_id is None:
            raise ValueError("Invalid value for `item_id`, must not be `None`")  # noqa: E501
        if item_id is not None and len(item_id) > 40:
            raise ValueError("Invalid value for `item_id`, length must be less than or equal to `40`")  # noqa: E501
        if item_id is not None and len(item_id) < 40:
            raise ValueError("Invalid value for `item_id`, length must be greater than or equal to `40`")  # noqa: E501

        self._item_id = item_id

    @property
    def status(self) -> bool:
        """Gets the status of this VendorChangeProductAvailabileRequest.

        Weather the item is available or not  # noqa: E501

        :return: The status of this VendorChangeProductAvailabileRequest.
        :rtype: bool
        """
        return self._status

    @status.setter
    def status(self, status: bool):
        """Sets the status of this VendorChangeProductAvailabileRequest.

        Weather the item is available or not  # noqa: E501

        :param status: The status of this VendorChangeProductAvailabileRequest.
        :type status: bool
        """
        if status is None:
            raise ValueError("Invalid value for `status`, must not be `None`")  # noqa: E501

        self._status = status
