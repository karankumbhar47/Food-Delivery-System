import sys
import csv
import time
import argparse
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

from FoodDeliveryAPI.models.vendor_details import VendorDetails
from FoodDeliveryAPI.models.user_details import UserDetails 
from FoodDeliveryAPI.models.login_request import LoginRequest
from FoodDeliveryAPI.models.vendor_add_product_request import VendorAddProductRequest


def read_menu_items(file_path):
    items = []
    with open(file_path, 'r', newline='') as file:
        reader = csv.DictReader(file)
        for row in reader:
            items.append({
                "name": row['name'],
                "maxQuantity": int(row['maxQuantity']),
                "price": float(row['price'])
            })
    return items


def addVendorProduct(host):
    configuration = FoodDeliveryAPI.Configuration(
        host = host
    )
    
    # End to End test for vendor flow
    with FoodDeliveryAPI.ApiClient(configuration) as api_client:
        # Create an instance of the API class
        api_instance = FoodDeliveryAPI.DefaultApi(api_client)
        id = 'id_example'
        menu_file_path = "./API-Tests/dummy_data/food_item.csv"
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

        items = read_menu_items(menu_file_path)

        print(f"Registering products with session id {session_id} (len: {len(session_id)})")
        for item in items:
            try:
                api_response = api_instance.vendor_add_product(session_id, VendorAddProductRequest.from_dict(item))
                print(f"Added product {item['name']}, product id is {api_response.strip()}")
            except ApiException as e:
                print("Exception when calling login_vendor: %s\n" % e)

def registerUser(host):
    configuration = FoodDeliveryAPI.Configuration(
        host = host
    )
    
    # End to End test for vendor flow
    with FoodDeliveryAPI.ApiClient(configuration) as api_client:
        # Create an instance of the API class
        api_instance = FoodDeliveryAPI.DefaultApi(api_client)
        id = 'id_example'
        count = 56
        session_id = ''
 
        # Registering a user
    try:
        print("Registering User...")
        api_response = api_instance.register(UserDetails.from_dict(
            {"username": "user001",
             "password": "1234@Abcd",
             "name": "John Doe",
             "phone": "9876543210",
             "email": "john.doe@example.com"}))

        print("Session Id is:\n")
        session_id = str.strip(api_response)
        print(session_id, len(session_id))
    except ApiException as e:
        print("Exception when calling register_user: %s\n" % e)
        # If registration failed, try logging in
        try:
            print("Registration Failed! User already exists, Logging in...")
            api_response = api_instance.login(LoginRequest.from_dict({
                "username": "user001",
                "password": "1234@Abcd"
            }))

            print("Session Id is:\n")
            session_id = str.strip(api_response)
            print(session_id, len(session_id))
        except ApiException as e:
            print("Exception when calling login_user: %s\n" % e)

def main():
    parser = argparse.ArgumentParser(description="Add the data to server with server specifications")
    
    parser.add_argument('-a', '--address', default="13.233.99.87", help='Specify the IP address')
    parser.add_argument('-p', '--port', default="8080", help='Specify the port')
    parser.add_argument('-H', '--host', default="http://13.233.99.87:8080", help='Specify the host URL')

    args = parser.parse_args()

    ip_address = args.address
    port = args.port
    host = args.host

    if ip_address is not None and port is not None:
        host = f"http://{ip_address}:{port}"
    elif  ip_address is not None:  
        host = f"http://{ip_address}:8080"
    elif port is not None:
        host = f"http://13.233.99.87:{port}"
    else:
        print("Error: If host URL is not specified, both IP address and port are required.")
        parser.print_help()
        return

    print(f"Using host URL: {host}")

    # host = "http://13.233.99.87:8080"
    addVendorProduct(host)
    registerUser(host)

if __name__ == "__main__":
    main()