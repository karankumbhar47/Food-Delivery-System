import unittest

from flask import json

from openapi_server.models.delivery_accept_order_post_request import DeliveryAcceptOrderPostRequest  # noqa: E501
from openapi_server.models.delivery_drop_order_post_request import DeliveryDropOrderPostRequest  # noqa: E501
from openapi_server.models.food_item import FoodItem  # noqa: E501
from openapi_server.models.food_item_full import FoodItemFull  # noqa: E501
from openapi_server.models.login_post_request import LoginPostRequest  # noqa: E501
from openapi_server.models.order import Order  # noqa: E501
from openapi_server.models.order_place_post_request import OrderPlacePostRequest  # noqa: E501
from openapi_server.models.product_id_available_get200_response import ProductIdAvailableGet200Response  # noqa: E501
from openapi_server.models.profile import Profile  # noqa: E501
from openapi_server.models.query_get_request import QueryGetRequest  # noqa: E501
from openapi_server.models.user_details import UserDetails  # noqa: E501
from openapi_server.models.vendor_order_requested_get200_response_inner import VendorOrderRequestedGet200ResponseInner  # noqa: E501
from openapi_server.models.vendor_product_add_images_post_request import VendorProductAddImagesPostRequest  # noqa: E501
from openapi_server.models.vendor_product_add_post_request import VendorProductAddPostRequest  # noqa: E501
from openapi_server.models.vendor_product_change_available_post_request import VendorProductChangeAvailablePostRequest  # noqa: E501
from openapi_server.models.vendor_product_edit_post_request import VendorProductEditPostRequest  # noqa: E501
from openapi_server.test import BaseTestCase


class TestDefaultController(BaseTestCase):
    """DefaultController integration test stubs"""

    def test_delivery_accept_order_post(self):
        """Test case for delivery_accept_order_post

        Accept Order
        """
        delivery_accept_order_post_request = DeliveryAcceptOrderPostRequest()
        headers = { 
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/delivery/acceptOrder',
            method='POST',
            headers=headers,
            data=json.dumps(delivery_accept_order_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_delivery_drop_order_post(self):
        """Test case for delivery_drop_order_post

        Drop Order
        """
        delivery_drop_order_post_request = DeliveryDropOrderPostRequest()
        headers = { 
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/delivery/dropOrder',
            method='POST',
            headers=headers,
            data=json.dumps(delivery_drop_order_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_delivery_get_accepted_orders_get(self):
        """Test case for delivery_get_accepted_orders_get

        Get List of Accepted Orders
        """
        headers = { 
            'Accept': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/delivery/getAcceptedOrders',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_delivery_pickup_order_post(self):
        """Test case for delivery_pickup_order_post

        Pickup Order
        """
        query_string = [('orderId', 'order_id_example')]
        headers = { 
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/delivery/pickupOrder',
            method='POST',
            headers=headers,
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_delivery_view_waiting_orders_get(self):
        """Test case for delivery_view_waiting_orders_get

        View Waiting Orders
        """
        headers = { 
            'Accept': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/delivery/viewWaitingOrders',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_file_get(self):
        """Test case for file_get

        Get file by file ID
        """
        headers = { 
            'Accept': 'image/*',
            'file_id': 'file_id_example',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/file',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    @unittest.skip("image/* not supported by Connexion")
    def test_file_put(self):
        """Test case for file_put

        Upload a file
        """
        body = '/path/to/file'
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'image/*',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/file',
            method='PUT',
            headers=headers,
            data=json.dumps(body),
            content_type='image/*')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_login_post(self):
        """Test case for login_post

        Login to user account
        """
        login_post_request = LoginPostRequest()
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/login',
            method='POST',
            headers=headers,
            data=json.dumps(login_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_order_confirm_post(self):
        """Test case for order_confirm_post

        Confirm Order
        """
        body = 'body_example'
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/order/confirm',
            method='POST',
            headers=headers,
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_order_get(self):
        """Test case for order_get

        
        """
        body = 'body_example'
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/order',
            method='GET',
            headers=headers,
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_order_place_post(self):
        """Test case for order_place_post

        Place the order
        """
        order_place_post_request = OrderPlacePostRequest()
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/order/place',
            method='POST',
            headers=headers,
            data=json.dumps(order_place_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_product_id_available_get(self):
        """Test case for product_id_available_get

        Check Availability
        """
        query_string = [('count', 56)]
        headers = { 
            'Accept': 'application/json',
        }
        response = self.client.open(
            '/product/{id}/available'.format(id='id_example'),
            method='GET',
            headers=headers,
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_product_id_get(self):
        """Test case for product_id_get

        Get Product Details
        """
        headers = { 
            'Accept': 'application/json',
        }
        response = self.client.open(
            '/product/{id}'.format(id='id_example'),
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_profile_get(self):
        """Test case for profile_get

        
        """
        body = 'body_example'
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/profile',
            method='GET',
            headers=headers,
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_profile_update_post(self):
        """Test case for profile_update_post

        
        """
        profile = Profile()
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/profile/update',
            method='POST',
            headers=headers,
            data=json.dumps(profile),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_query_get(self):
        """Test case for query_get

        Search for items
        """
        query_get_request = QueryGetRequest()
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/query',
            method='GET',
            headers=headers,
            data=json.dumps(query_get_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_register_post(self):
        """Test case for register_post

        Register a new consumer?
        """
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

    def test_vendor_change_available_post(self):
        """Test case for vendor_change_available_post

        
        """
        vendor_product_change_available_post_request = VendorProductChangeAvailablePostRequest()
        headers = { 
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/vendor/changeAvailable',
            method='POST',
            headers=headers,
            data=json.dumps(vendor_product_change_available_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_vendor_order_accepted_get(self):
        """Test case for vendor_order_accepted_get

        
        """
        headers = { 
            'Accept': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/vendor/order/accepted',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_vendor_order_requested_get(self):
        """Test case for vendor_order_requested_get

        
        """
        headers = { 
            'Accept': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/vendor/order/requested',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_vendor_product_add_images_post(self):
        """Test case for vendor_product_add_images_post

        
        """
        vendor_product_add_images_post_request = VendorProductAddImagesPostRequest()
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/vendor/product/addImages',
            method='POST',
            headers=headers,
            data=json.dumps(vendor_product_add_images_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_vendor_product_add_post(self):
        """Test case for vendor_product_add_post

        
        """
        vendor_product_add_post_request = VendorProductAddPostRequest()
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/vendor/product/add',
            method='POST',
            headers=headers,
            data=json.dumps(vendor_product_add_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_vendor_product_change_available_post(self):
        """Test case for vendor_product_change_available_post

        
        """
        vendor_product_change_available_post_request = VendorProductChangeAvailablePostRequest()
        headers = { 
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/vendor/product/changeAvailable',
            method='POST',
            headers=headers,
            data=json.dumps(vendor_product_change_available_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_vendor_product_edit_post(self):
        """Test case for vendor_product_edit_post

        
        """
        vendor_product_edit_post_request = VendorProductEditPostRequest()
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'session_id': 'session_id_example',
        }
        response = self.client.open(
            '/vendor/product/edit',
            method='POST',
            headers=headers,
            data=json.dumps(vendor_product_edit_post_request),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    unittest.main()
