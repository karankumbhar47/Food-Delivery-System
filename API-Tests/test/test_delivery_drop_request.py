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

from FoodDeliveryAPI.models.delivery_drop_request import DeliveryDropRequest

class TestDeliveryDropRequest(unittest.TestCase):
    """DeliveryDropRequest unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def make_instance(self, include_optional) -> DeliveryDropRequest:
        """Test DeliveryDropRequest
            include_option is a boolean, when False only required
            params are included, when True both required and
            optional params are included """
        # uncomment below to create an instance of `DeliveryDropRequest`
        """
        model = DeliveryDropRequest()
        if include_optional:
            return DeliveryDropRequest(
                order_id = '',
                otp = 56
            )
        else:
            return DeliveryDropRequest(
        )
        """

    def testDeliveryDropRequest(self):
        """Test DeliveryDropRequest"""
        # inst_req_only = self.make_instance(include_optional=False)
        # inst_req_and_optional = self.make_instance(include_optional=True)

if __name__ == '__main__':
    unittest.main()