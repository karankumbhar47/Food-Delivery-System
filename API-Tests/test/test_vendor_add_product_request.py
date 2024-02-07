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

from FoodDeliveryAPI.models.vendor_add_product_request import VendorAddProductRequest

class TestVendorAddProductRequest(unittest.TestCase):
    """VendorAddProductRequest unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def make_instance(self, include_optional) -> VendorAddProductRequest:
        """Test VendorAddProductRequest
            include_option is a boolean, when False only required
            params are included, when True both required and
            optional params are included """
        # uncomment below to create an instance of `VendorAddProductRequest`
        """
        model = VendorAddProductRequest()
        if include_optional:
            return VendorAddProductRequest(
                name = '',
                thumbnail = '',
                price = 1.337,
                max_quantity = 1.337,
                image_urls = [
                    '0123456789101112131415161718192021222324252627282930313233343536373839'
                    ],
                tags = [
                    ''
                    ]
            )
        else:
            return VendorAddProductRequest(
                name = '',
                price = 1.337,
        )
        """

    def testVendorAddProductRequest(self):
        """Test VendorAddProductRequest"""
        # inst_req_only = self.make_instance(include_optional=False)
        # inst_req_and_optional = self.make_instance(include_optional=True)

if __name__ == '__main__':
    unittest.main()
