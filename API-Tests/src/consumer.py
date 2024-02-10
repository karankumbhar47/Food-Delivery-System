import csv
import argparse
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException

from FoodDeliveryAPI.models.user_details import UserDetails 
from FoodDeliveryAPI.models.login_request import LoginRequest


consumer_data_path = "./API-Tests/dummy_data/consumer/consumer.csv"

def registerUser(host):
    configuration = FoodDeliveryAPI.Configuration( host = host)
    
    # End to End test for vendor flow
    with FoodDeliveryAPI.ApiClient(configuration) as api_client:
        # Create an instance of the API class
        api_instance = FoodDeliveryAPI.DefaultApi(api_client)
 
        with open(consumer_data_path, 'r') as file:
                reader = csv.DictReader(file)
                for row in reader:
                    username = row['username']
                    password = row['password']
                    name = row['name']
                    phone = row['phone']
                    email = row['email']

                    # Registering a user
                    try:
                        print("Registering User ...")
                        api_response = api_instance.register(UserDetails.from_dict(
                            {"username": username,
                             "password": password,
                             "name": name,
                             "phone": phone,
                             "email": email}))

                        session_id = str.strip(api_response)
                        print(f"Session Id for user {username}: {session_id}")
                        print(f"length of session id is {len(session_id)}\n")
                    except ApiException as e:
                        print(f"Exception when calling register_user {username}: %s\n" % e)
                        # If registration failed, try logging in
                        try:
                            print("Registration Failed! User already exists, Logging in...")
                            api_response = api_instance.login(LoginRequest.from_dict({
                                "username": username,
                                "password": password 
                            }))

                            session_id = str.strip(api_response)
                            print(f"Session Id is: {session_id}")
                            print(f"length of session id is {len(session_id)}\n")
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
    registerUser(host)

if __name__ == "__main__":
    main()