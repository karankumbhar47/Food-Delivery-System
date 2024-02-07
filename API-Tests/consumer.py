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