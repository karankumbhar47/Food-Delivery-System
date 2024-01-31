import unittest
import os

from flask import json

from openapi_server.models.delivery_accept_order_request import DeliveryAcceptOrderRequest  # noqa: E501
from openapi_server.models.delivery_drop_request import DeliveryDropRequest  # noqa: E501
from openapi_server.models.filter import Filter  # noqa: E501
from openapi_server.models.food_item import FoodItem  # noqa: E501
from openapi_server.models.food_item_full import FoodItemFull  # noqa: E501
from openapi_server.models.login_request import LoginRequest  # noqa: E501
from openapi_server.models.order import Order  # noqa: E501
from openapi_server.models.place_order_request import PlaceOrderRequest  # noqa: E501
from openapi_server.models.profile import Profile  # noqa: E501
from openapi_server.models.user_details import UserDetails  # noqa: E501
from openapi_server.models.vendor_add_product_images_request import VendorAddProductImagesRequest  # noqa: E501
from openapi_server.models.vendor_add_product_request import VendorAddProductRequest  # noqa: E501
from openapi_server.models.vendor_change_product_availabile_request import VendorChangeProductAvailabileRequest  # noqa: E501
from openapi_server.models.vendor_details import VendorDetails  # noqa: E501
from openapi_server.models.vendor_edit_product_request import VendorEditProductRequest  # noqa: E501
from openapi_server.models.vendor_get_requested_orders200_response_inner import VendorGetRequestedOrders200ResponseInner  # noqa: E501
from openapi_server.test import BaseTestCase


class TestDefaultController(BaseTestCase):
    """DefaultController integration test stubs"""

    # def test_check_product_available(self):
    #     """Test case for check_product_available

    #     Check Availability
    #     """
    #     query_string = [('count', 56)]
    #     headers = { 
    #         'Accept': 'application/json',
    #     }
    #     response = self.client.open(
    #         '/product/{id}/available'.format(id='id_example'),
    #         method='GET',
    #         headers=headers,
    #         query_string=query_string)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_confirm_order(self):
    #     """Test case for confirm_order

    #     Confirm Order
    #     """
    #     body = 'body_example'
    #     headers = { 
    #         'Accept': 'application/json',
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/order/confirm',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(body),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_delivery_accept_order(self):
    #     """Test case for delivery_accept_order

    #     Accept Order
    #     """
    #     delivery_accept_order_request = DeliveryAcceptOrderRequest()
    #     headers = { 
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/delivery/acceptOrder',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(delivery_accept_order_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_delivery_drop(self):
    #     """Test case for delivery_drop

    #     Drop Order
    #     """
    #     delivery_drop_request = DeliveryDropRequest()
    #     headers = { 
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/delivery/dropOrder',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(delivery_drop_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_delivery_pick(self):
    #     """Test case for delivery_pick

    #     Pickup Order
    #     """
    #     query_string = [('orderId', 'order_id_example')]
    #     headers = { 
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/delivery/pickupOrder',
    #         method='POST',
    #         headers=headers,
    #         query_string=query_string)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_delivery_view_accepted_orders(self):
    #     """Test case for delivery_view_accepted_orders

    #     Get List of Accepted Orders
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/delivery/getAcceptedOrders',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_delivery_view_waiting_orders(self):
    #     """Test case for delivery_view_waiting_orders

    #     View Waiting Orders
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/delivery/viewWaitingOrders',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_get_file(self):
    #     """Test case for get_file

    #     Get file by file ID
    #     """
    #     headers = { 
    #         'Accept': 'image/*',
    #         'file_id': 'file_id_example',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/file',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_get_orders(self):
    #     """Test case for get_orders

        
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #         'session_id': 'session_id_example',
    #         'order_id': 'order_id_example',
    #     }
    #     response = self.client.open(
    #         '/order',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_get_product(self):
    #     """Test case for get_product

    #     Get Product Details
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #     }
    #     response = self.client.open(
    #         '/product/{id}'.format(id='id_example'),
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_get_profile(self):
    #     """Test case for get_profile

        
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/profile',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    def test_login(self):
        """Test case for login

        Login to user account
        """

        testDir = os.getenv("FDS_TEST_SERVER_DIR")
        if testDir is None:
            testDir = "/tmp/tests/"
        dbPath = os.path.join(testDir, "login.db")
        os.environ["FOOD_DELIVERY_DB"] = dbPath

        login_request = LoginRequest(username="user1", password="1234@Abd")
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/login',
            method='POST',
            headers=headers,
            data=json.dumps(login_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_login_vendor(self):
        """Test case for login_vendor

        Login to vendor account
        """

        testDir = os.getenv("FDS_TEST_SERVER_DIR")
        if testDir is None:
            testDir = "/tmp/tests/"
        dbPath = os.path.join(testDir, "login_vendor.db")
        os.environ["FOOD_DELIVERY_DB"] = dbPath

        login_request = LoginRequest("vendor001", "1234@Abcd")
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/vendor/login',
            method='POST',
            headers=headers,
            data=json.dumps(login_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    # def test_place_order(self):
    #     """Test case for place_order

    #     Place the order
    #     """
    #     place_order_request = PlaceOrderRequest()
    #     headers = { 
    #         'Accept': 'application/json',
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/order/place',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(place_order_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # @unittest.skip("image/* not supported by Connexion")
    # def test_put_file(self):
    #     """Test case for put_file

    #     Upload a file
    #     """
    #     body = '/path/to/file'
    #     headers = { 
    #         'Accept': 'application/json',
    #         'Content-Type': 'image/*',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/file',
    #         method='PUT',
    #         headers=headers,
    #         data=json.dumps(body),
    #         content_type='image/*')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_query(self):
    #     """Test case for query

    #     Search for items
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #         'session_id': 'session_id_example',
    #         'query': 'query_example',
    #         'filters': [Filter()],
    #     }
    #     response = self.client.open(
    #         '/query',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    def test_register(self):
        testDir = os.getenv("FDS_TEST_SERVER_DIR")
        if testDir is None:
            testDir = "/tmp/tests/"
        dbPath = os.path.join(testDir, "register.db")
        os.environ["FOOD_DELIVERY_DB"] = dbPath

        try:
            os.remove(dbPath)
        except OSError:
            pass
        finally:
            print("Cleaned existing db file")

        print(os.getenv("FOOD_DELIVERY_DB"))

        user_details = UserDetails("user1", "1234@Abd", "Test D User", "9876543310", "", "Male")
        headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/register',
            method='POST',
            headers=headers,
            data=json.dumps(user_details),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_register_vendor(self):
        """Test case for register_vendor

        Register a new vendor
        """
        testDir = os.getenv("FDS_TEST_SERVER_DIR")
        if testDir is None:
            testDir = "/tmp/tests/"
        dbPath = os.path.join(testDir, "register_vendor.db")
        os.environ["FOOD_DELIVERY_DB"] = dbPath

        try:
            os.remove(dbPath)
        except OSError:
            pass
        finally:
            print("Cleaned existing db file")

        print(os.getenv("FOOD_DELIVERY_DB"))

        vendor_details = VendorDetails("vendor001", "1234@Abcd", "UltraTech Cafe", "9876214705",
                                       "vendor001@example.com", location="MessBlock")
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/vendor/register',
            method='POST',
            headers=headers,
            data=json.dumps(vendor_details),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    # def test_update_profile(self):
    #     """Test case for update_profile

        
    #     """
    #     profile = Profile()
    #     headers = { 
    #         'Accept': 'application/json',
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/profile/update',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(profile),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_vendor_add_product(self):
    #     """Test case for vendor_add_product

        
    #     """
    #     vendor_add_product_request = VendorAddProductRequest()
    #     headers = { 
    #         'Accept': 'application/json',
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/vendor/product/add',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(vendor_add_product_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_vendor_add_product_images(self):
    #     """Test case for vendor_add_product_images

        
    #     """
    #     vendor_add_product_images_request = VendorAddProductImagesRequest()
    #     headers = { 
    #         'Accept': 'application/json',
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/vendor/product/addImages',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(vendor_add_product_images_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_vendor_change_availabile(self):
    #     """Test case for vendor_change_availabile

        
    #     """
    #     vendor_change_product_availabile_request = VendorChangeProductAvailabileRequest()
    #     headers = { 
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/vendor/changeAvailable',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(vendor_change_product_availabile_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_vendor_change_product_availabile(self):
    #     """Test case for vendor_change_product_availabile

        
    #     """
    #     vendor_change_product_availabile_request = VendorChangeProductAvailabileRequest()
    #     headers = { 
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/vendor/product/changeAvailable',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(vendor_change_product_availabile_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_vendor_edit_product(self):
    #     """Test case for vendor_edit_product

        
    #     """
    #     vendor_edit_product_request = VendorEditProductRequest()
    #     headers = { 
    #         'Accept': 'application/json',
    #         'Content-Type': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/vendor/product/edit',
    #         method='POST',
    #         headers=headers,
    #         data=json.dumps(vendor_edit_product_request),
    #         content_type='application/json')
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_vendor_get_accepted_orders(self):
    #     """Test case for vendor_get_accepted_orders

        
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/vendor/order/accepted',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))

    # def test_vendor_get_requested_orders(self):
    #     """Test case for vendor_get_requested_orders

        
    #     """
    #     headers = { 
    #         'Accept': 'application/json',
    #         'session_id': 'session_id_example',
    #     }
    #     response = self.client.open(
    #         '/vendor/order/requested',
    #         method='GET',
    #         headers=headers)
    #     self.assert200(response,
    #                    'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    unittest.main()
