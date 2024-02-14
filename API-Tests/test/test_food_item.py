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

from FoodDeliveryAPI.models.food_item import FoodItem

class TestFoodItem(unittest.TestCase):
    """FoodItem unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def make_instance(self, include_optional) -> FoodItem:
        """Test FoodItem
            include_option is a boolean, when False only required
            params are included, when True both required and
            optional params are included """
        # uncomment below to create an instance of `FoodItem`
        """
        model = FoodItem()
        if include_optional:
            return FoodItem(
                item_id = '0123456789101112131415161718192021222324252627282930313233343536373839',
                name = '',
                thumbnail = '',
                vendor = '',
                price = 1.337,
                rating = 1.337,
                rated_by = 1.337,
                tags = [
                    ''
                    ]
            )
        else:
            return FoodItem(
                item_id = '0123456789101112131415161718192021222324252627282930313233343536373839',
                name = '',
                vendor = '',
                price = 1.337,
        )
        """

    def testFoodItem(self):
        """Test FoodItem"""
        # inst_req_only = self.make_instance(include_optional=False)
        # inst_req_and_optional = self.make_instance(include_optional=True)

if __name__ == '__main__':
    unittest.main()