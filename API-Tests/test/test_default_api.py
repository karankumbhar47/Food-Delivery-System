# coding: utf-8

"""
    FDS

    This API allows to communicate with FDS (Food Delivery System) server. 

    The version of the OpenAPI document: 0.0.3
    Generated by OpenAPI Generator (https://openapi-generator.tech)

    Do not edit the class manually.
"""  # noqa: E501


import unittest

from FoodDeliveryAPI.api.default_api import DefaultApi


class TestDefaultApi(unittest.TestCase):
    """DefaultApi unit test stubs"""

    def setUp(self) -> None:
        self.api = DefaultApi()

    def tearDown(self) -> None:
        pass

    def test_check_product_available(self) -> None:
        """Test case for check_product_available

        Check Availability
        """
        pass

    def test_confirm_order(self) -> None:
        """Test case for confirm_order

        Confirm Order
        """
        pass

    def test_delivery_accept_order(self) -> None:
        """Test case for delivery_accept_order

        Accept Order
        """
        pass

    def test_delivery_drop(self) -> None:
        """Test case for delivery_drop

        Drop Order
        """
        pass

    def test_delivery_pick(self) -> None:
        """Test case for delivery_pick

        Pickup Order
        """
        pass

    def test_delivery_view_accepted_orders(self) -> None:
        """Test case for delivery_view_accepted_orders

        Get List of Accepted Orders
        """
        pass

    def test_delivery_view_waiting_orders(self) -> None:
        """Test case for delivery_view_waiting_orders

        View Waiting Orders
        """
        pass

    def test_get_file(self) -> None:
        """Test case for get_file

        Get file by file ID
        """
        pass

    def test_get_orders(self) -> None:
        """Test case for get_orders

        """
        pass

    def test_get_product(self) -> None:
        """Test case for get_product

        Get Product Details
        """
        pass

    def test_get_profile(self) -> None:
        """Test case for get_profile

        """
        pass

    def test_login(self) -> None:
        """Test case for login

        Login to user account
        """
        pass

    def test_login_vendor(self) -> None:
        """Test case for login_vendor

        Login to vendor account
        """
        pass

    def test_place_order(self) -> None:
        """Test case for place_order

        Place the order
        """
        pass

    def test_put_file(self) -> None:
        """Test case for put_file

        Upload a file
        """
        pass

    def test_query(self) -> None:
        """Test case for query

        Search for items
        """
        pass

    def test_register(self) -> None:
        """Test case for register

        Register a new consumer?
        """
        pass

    def test_register_vendor(self) -> None:
        """Test case for register_vendor

        Register a new vendor
        """
        pass

    def test_update_profile(self) -> None:
        """Test case for update_profile

        """
        pass

    def test_vendor_add_product(self) -> None:
        """Test case for vendor_add_product

        """
        pass

    def test_vendor_add_product_images(self) -> None:
        """Test case for vendor_add_product_images

        """
        pass

    def test_vendor_change_availabile(self) -> None:
        """Test case for vendor_change_availabile

        """
        pass

    def test_vendor_change_product_availabile(self) -> None:
        """Test case for vendor_change_product_availabile

        """
        pass

    def test_vendor_edit_product(self) -> None:
        """Test case for vendor_edit_product

        """
        pass

    def test_vendor_get_accepted_orders(self) -> None:
        """Test case for vendor_get_accepted_orders

        """
        pass

    def test_vendor_get_requested_orders(self) -> None:
        """Test case for vendor_get_requested_orders

        """
        pass


if __name__ == '__main__':
    unittest.main()
