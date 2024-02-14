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

from FoodDeliveryAPI.models.vendor_get_requested_orders200_response_inner_order_items_inner import VendorGetRequestedOrders200ResponseInnerOrderItemsInner

class TestVendorGetRequestedOrders200ResponseInnerOrderItemsInner(unittest.TestCase):
    """VendorGetRequestedOrders200ResponseInnerOrderItemsInner unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def make_instance(self, include_optional) -> VendorGetRequestedOrders200ResponseInnerOrderItemsInner:
        """Test VendorGetRequestedOrders200ResponseInnerOrderItemsInner
            include_option is a boolean, when False only required
            params are included, when True both required and
            optional params are included """
        # uncomment below to create an instance of `VendorGetRequestedOrders200ResponseInnerOrderItemsInner`
        """
        model = VendorGetRequestedOrders200ResponseInnerOrderItemsInner()
        if include_optional:
            return VendorGetRequestedOrders200ResponseInnerOrderItemsInner(
                item_id = '0123456789101112131415161718192021222324252627282930313233343536373839',
                quantity = 1.337
            )
        else:
            return VendorGetRequestedOrders200ResponseInnerOrderItemsInner(
                item_id = '0123456789101112131415161718192021222324252627282930313233343536373839',
                quantity = 1.337,
        )
        """

    def testVendorGetRequestedOrders200ResponseInnerOrderItemsInner(self):
        """Test VendorGetRequestedOrders200ResponseInnerOrderItemsInner"""
        # inst_req_only = self.make_instance(include_optional=False)
        # inst_req_and_optional = self.make_instance(include_optional=True)

if __name__ == '__main__':
    unittest.main()