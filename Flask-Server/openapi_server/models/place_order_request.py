from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from openapi_server.models.base_model import Model
from openapi_server.models.place_order_request_item_cart_inner import PlaceOrderRequestItemCartInner
from openapi_server import util

from openapi_server.models.place_order_request_item_cart_inner import PlaceOrderRequestItemCartInner  # noqa: E501

class PlaceOrderRequest(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, item_cart=None, location=None):  # noqa: E501
        """PlaceOrderRequest - a model defined in OpenAPI

        :param item_cart: The item_cart of this PlaceOrderRequest.  # noqa: E501
        :type item_cart: List[PlaceOrderRequestItemCartInner]
        :param location: The location of this PlaceOrderRequest.  # noqa: E501
        :type location: str
        """
        self.openapi_types = {
            'item_cart': List[PlaceOrderRequestItemCartInner],
            'location': str
        }

        self.attribute_map = {
            'item_cart': 'itemCart',
            'location': 'location'
        }

        self._item_cart = item_cart
        self._location = location

    @classmethod
    def from_dict(cls, dikt) -> 'PlaceOrderRequest':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The placeOrder_request of this PlaceOrderRequest.  # noqa: E501
        :rtype: PlaceOrderRequest
        """
        return util.deserialize_model(dikt, cls)

    @property
    def item_cart(self) -> List[PlaceOrderRequestItemCartInner]:
        """Gets the item_cart of this PlaceOrderRequest.


        :return: The item_cart of this PlaceOrderRequest.
        :rtype: List[PlaceOrderRequestItemCartInner]
        """
        return self._item_cart

    @item_cart.setter
    def item_cart(self, item_cart: List[PlaceOrderRequestItemCartInner]):
        """Sets the item_cart of this PlaceOrderRequest.


        :param item_cart: The item_cart of this PlaceOrderRequest.
        :type item_cart: List[PlaceOrderRequestItemCartInner]
        """
        if item_cart is None:
            raise ValueError("Invalid value for `item_cart`, must not be `None`")  # noqa: E501

        self._item_cart = item_cart

    @property
    def location(self) -> str:
        """Gets the location of this PlaceOrderRequest.

        Delivery location.  # noqa: E501

        :return: The location of this PlaceOrderRequest.
        :rtype: str
        """
        return self._location

    @location.setter
    def location(self, location: str):
        """Sets the location of this PlaceOrderRequest.

        Delivery location.  # noqa: E501

        :param location: The location of this PlaceOrderRequest.
        :type location: str
        """
        if location is None:
            raise ValueError("Invalid value for `location`, must not be `None`")  # noqa: E501

        self._location = location
