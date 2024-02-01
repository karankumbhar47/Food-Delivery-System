import time
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

from FoodDeliveryAPI.models.vendor_details import VendorDetails
from FoodDeliveryAPI.models.login_request import LoginRequest
from FoodDeliveryAPI.models.vendor_add_product_request import VendorAddProductRequest


configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# End to End test for vendor flow
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    id = 'id_example'
    count = 56
    session_id = ''

    try:
        # Check Availability
        print("Regestering Vendor...")
        api_response = api_instance.register_vendor(VendorDetails.from_dict(
            {"username": "vendor001",
             "password": "1234@Abcd",
             "name": "UltraTech Cafe",
             "phone": "9876214705",
             "email": "mymail@main.main",
             "location": "MessBlock"}))

        print("Session Id is:\n")
        session_id = str.strip(api_response)
        print(session_id, len(session_id))
    except ApiException as e:
        print("Exception when calling register_vendor: %s\n" % e)
        # Check the reason
        # if register failed, login
        try:
            print("Registeration Failed! Vendor already exists, Logging in...")
            api_response = api_instance.login_vendor(LoginRequest.from_dict({
                "username": "vendor001",
                "password": "1234@Abcd"
            }))

            print("Session Id is:\n")
            session_id = str.strip(api_response)
            print(session_id, len(session_id))
        except ApiException as e:
            print("Exception when calling login_vendor: %s\n" % e)

    items = [
        {"name": 'Pizza Margherita', "maxQunatity": 9, "price": 50},
        {"name": 'Chicken Caesar Salad', "maxQunatity": 7, "price": 30},
        {"name": 'Spaghetti Bolognese', "maxQunatity": 12, "price": 40},
        {"name": 'Grilled Salmon', "maxQunatity": 15, "price": 20},
        {"name": 'Cheeseburger', "maxQunatity": 6, "price": 50},
        {"name": 'Mushroom Risotto', "maxQunatity": 10, "price": 35},
        {"name": 'Chicken Tandoori', "maxQunatity": 13, "price": 30},
        {"name": 'Caesar Wrap', "maxQunatity": 7, "price": 40},
        {"name": 'Chocolate Fondue', "maxQunatity": 14, "price": 15},
        {"name": 'Shrimp Scampi', "maxQunatity": 17, "price": 25},
        {"name": 'Vegetable Stir Fry', "maxQunatity": 9, "price": 35},
        {"name": 'Caprese Salad', "maxQunatity": 8, "price": 30},
        {"name": 'BBQ Ribs', "maxQunatity": 19, "price": 20},
        {"name": 'Pad Thai', "maxQunatity": 11, "price": 40}
    ]

    print(f"Registering products with session id {session_id} (len: {len(session_id)})")
    for item in items:
        try:
            api_response = api_instance.vendor_add_product(session_id, VendorAddProductRequest.from_dict(item))
            print(f"Added product {item['name']}, product id is {api_response.strip()}")
        except ApiException as e:
            print("Exception when calling login_vendor: %s\n" % e)
