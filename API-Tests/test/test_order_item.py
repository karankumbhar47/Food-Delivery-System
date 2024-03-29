# coding: utf-8

"""
    FDS

    This API allows to communicate with FDS (Food Delivery System) server. 

    The version of the OpenAPI document: 0.0.3
    Generated by OpenAPI Generator (https://openapi-generator.tech)

    Do not edit the class manually.
"""  # noqa: E501


import unittest
import datetime

from FoodDeliveryAPI.models.order_item import OrderItem

class TestOrderItem(unittest.TestCase):
    """OrderItem unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def make_instance(self, include_optional) -> OrderItem:
        """Test OrderItem
            include_option is a boolean, when False only required
            params are included, when True both required and
            optional params are included """
        # uncomment below to create an instance of `OrderItem`
        """
        model = OrderItem()
        if include_optional:
            return OrderItem(
                item_id = '',
                item_name = '',
                quantity = 56,
                price = 1.337
            )
        else:
            return OrderItem(
        )
        """

    def testOrderItem(self):
        """Test OrderItem"""
        # inst_req_only = self.make_instance(include_optional=False)
        # inst_req_and_optional = self.make_instance(include_optional=True)

if __name__ == '__main__':
    unittest.main()
