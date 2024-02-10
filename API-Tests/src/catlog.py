import sys
import csv
import os
import argparse
sys.path.insert(0,"/home/karan/Templates/Food-Delivery-System/API-Tests/")
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException

from FoodDeliveryAPI.models.login_request import LoginRequest
from FoodDeliveryAPI.models.vendor_add_product_request import VendorAddProductRequest


vendor_data_path = "./API-Tests/dummy_data/vendor/vendor.csv"

def getImgId(path,api_instance,session_id):
    with open(path, 'rb') as file:
        image_data = file.read()
        try:
            api_response = api_instance.put_file(session_id, image_data)
            print(f"Added image {path}, image id is {api_response.strip()}\n")
            return api_response.strip()
        except ApiException as e:
            print("Exception when sending image: %s\n" % e)
            return None 

def create_vendor_product(row,api_instance,session_id):
    img_path = f"./API-Tests/dummy_data/catlog/images/{row['img_path']}.jpg" 
    img_id = getImgId(img_path,api_instance,session_id)
    return {
        "name": row['name'],
        "thumbnail": img_id,
        "price": float(row['price']),
        "maxQuantity": int(row['max_quantity']),
        "imageUrls": [],  
        "tags": row['tags'].split("|")
    }


def read_menu_items(file_path, api_instance,session_id):
    items = []
    with open(file_path, 'r', newline='') as file:
        reader = csv.DictReader(file)
        for row in reader:
            items.append(create_vendor_product(row,api_instance,session_id))
    return items


def add_product(v_username, v_password, catlog_data_path,api_instance):

    try:
        print(f"Trying to logging in for vendor {v_username}")
        api_response = api_instance.login_vendor(LoginRequest.from_dict({
            "username": v_username,
            "password": v_password
        }))

        session_id = str.strip(api_response)
        print(f"Session Id for vendor {v_username}: "+session_id)
        print(f"Registering products with session id {session_id} ")
        items = read_menu_items(catlog_data_path,api_instance,session_id)
        for item in items:
            try:
                print(item)
                api_response = api_instance.vendor_add_product(session_id, VendorAddProductRequest.from_dict(item))
            except ApiException as e:
                print("Exception when calling login_vendor: %s\n" % e)
    except ApiException as e:
        print(f"Exception when calling login_vendor {v_username}: %s\n" % e)

def addAllProduct(host):
    configuration = FoodDeliveryAPI.Configuration(host = host)
    
    with FoodDeliveryAPI.ApiClient(configuration) as api_client:
        api_instance = FoodDeliveryAPI.DefaultApi(api_client)
        try:
            with open(vendor_data_path, 'r') as file:
                reader = csv.DictReader(file)
                for row in reader:
                    username = row['username']
                    password = row['password']
                    path = os.path.join("./API-Tests/dummy_data/vendor/",f"{username}.csv")
                    add_product(username,password,path,api_instance)
        except Exception as e:
            print("Error occurs while opening vendor data file: %s\n" % e)

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
    addAllProduct(host)

if __name__ == "__main__":
    main()





    