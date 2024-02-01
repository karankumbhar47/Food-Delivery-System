# FoodDeliveryAPI.DefaultApi

All URIs are relative to *http://13.233.99.87:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**check_product_available**](DefaultApi.md#check_product_available) | **GET** /product/{id}/available | Check Availability
[**confirm_order**](DefaultApi.md#confirm_order) | **POST** /order/confirm | Confirm Order
[**delivery_accept_order**](DefaultApi.md#delivery_accept_order) | **POST** /delivery/acceptOrder | Accept Order
[**delivery_drop**](DefaultApi.md#delivery_drop) | **POST** /delivery/dropOrder | Drop Order
[**delivery_pick**](DefaultApi.md#delivery_pick) | **POST** /delivery/pickupOrder | Pickup Order
[**delivery_view_accepted_orders**](DefaultApi.md#delivery_view_accepted_orders) | **GET** /delivery/getAcceptedOrders | Get List of Accepted Orders
[**delivery_view_waiting_orders**](DefaultApi.md#delivery_view_waiting_orders) | **GET** /delivery/viewWaitingOrders | View Waiting Orders
[**get_file**](DefaultApi.md#get_file) | **GET** /file | Get file by file ID
[**get_orders**](DefaultApi.md#get_orders) | **GET** /order | 
[**get_product**](DefaultApi.md#get_product) | **GET** /product/{id} | Get Product Details
[**get_profile**](DefaultApi.md#get_profile) | **GET** /profile | 
[**login**](DefaultApi.md#login) | **POST** /login | Login to user account
[**login_vendor**](DefaultApi.md#login_vendor) | **POST** /vendor/login | Login to vendor account
[**place_order**](DefaultApi.md#place_order) | **POST** /order/place | Place the order
[**put_file**](DefaultApi.md#put_file) | **PUT** /file | Upload a file
[**query**](DefaultApi.md#query) | **GET** /query | Search for items
[**register**](DefaultApi.md#register) | **POST** /register | Register a new consumer?
[**register_vendor**](DefaultApi.md#register_vendor) | **POST** /vendor/register | Register a new vendor
[**update_profile**](DefaultApi.md#update_profile) | **POST** /profile/update | 
[**vendor_add_product**](DefaultApi.md#vendor_add_product) | **POST** /vendor/product/add | 
[**vendor_add_product_images**](DefaultApi.md#vendor_add_product_images) | **POST** /vendor/product/addImages | 
[**vendor_change_availabile**](DefaultApi.md#vendor_change_availabile) | **POST** /vendor/changeAvailable | 
[**vendor_change_product_availabile**](DefaultApi.md#vendor_change_product_availabile) | **POST** /vendor/product/changeAvailable | 
[**vendor_edit_product**](DefaultApi.md#vendor_edit_product) | **POST** /vendor/product/edit | 
[**vendor_get_accepted_orders**](DefaultApi.md#vendor_get_accepted_orders) | **GET** /vendor/order/accepted | 
[**vendor_get_requested_orders**](DefaultApi.md#vendor_get_requested_orders) | **GET** /vendor/order/requested | 


# **check_product_available**
> bool check_product_available(id, count)

Check Availability

Check the availability of a specific item by providing its ID and quantity count

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    id = 'id_example' # str | ID of the item to check availability for
    count = 56 # int | Quantity count to check availability for

    try:
        # Check Availability
        api_response = api_instance.check_product_available(id, count)
        print("The response of DefaultApi->check_product_available:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->check_product_available: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of the item to check availability for | 
 **count** | **int**| Quantity count to check availability for | 

### Return type

**bool**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **confirm_order**
> float confirm_order(session_id, body)

Confirm Order

Confirm Order Delivery

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    body = 'body_example' # str | 

    try:
        # Confirm Order
        api_response = api_instance.confirm_order(session_id, body)
        print("The response of DefaultApi->confirm_order:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->confirm_order: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **body** | **str**|  | 

### Return type

**float**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delivery_accept_order**
> delivery_accept_order(session_id, delivery_accept_order_request=delivery_accept_order_request)

Accept Order

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.delivery_accept_order_request import DeliveryAcceptOrderRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | Session ID of the delivery person
    delivery_accept_order_request = FoodDeliveryAPI.DeliveryAcceptOrderRequest() # DeliveryAcceptOrderRequest |  (optional)

    try:
        # Accept Order
        api_instance.delivery_accept_order(session_id, delivery_accept_order_request=delivery_accept_order_request)
    except Exception as e:
        print("Exception when calling DefaultApi->delivery_accept_order: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**| Session ID of the delivery person | 
 **delivery_accept_order_request** | [**DeliveryAcceptOrderRequest**](DeliveryAcceptOrderRequest.md)|  | [optional] 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delivery_drop**
> delivery_drop(session_id, delivery_drop_request=delivery_drop_request)

Drop Order

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.delivery_drop_request import DeliveryDropRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | Session ID of the delivery person
    delivery_drop_request = FoodDeliveryAPI.DeliveryDropRequest() # DeliveryDropRequest |  (optional)

    try:
        # Drop Order
        api_instance.delivery_drop(session_id, delivery_drop_request=delivery_drop_request)
    except Exception as e:
        print("Exception when calling DefaultApi->delivery_drop: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**| Session ID of the delivery person | 
 **delivery_drop_request** | [**DeliveryDropRequest**](DeliveryDropRequest.md)|  | [optional] 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delivery_pick**
> delivery_pick(session_id, order_id)

Pickup Order

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | Session ID of the delivery person
    order_id = 'order_id_example' # str | ID of the order to be picked up

    try:
        # Pickup Order
        api_instance.delivery_pick(session_id, order_id)
    except Exception as e:
        print("Exception when calling DefaultApi->delivery_pick: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**| Session ID of the delivery person | 
 **order_id** | **str**| ID of the order to be picked up | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delivery_view_accepted_orders**
> List[Order] delivery_view_accepted_orders(session_id)

Get List of Accepted Orders

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.order import Order
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | Session ID of the delivery person

    try:
        # Get List of Accepted Orders
        api_response = api_instance.delivery_view_accepted_orders(session_id)
        print("The response of DefaultApi->delivery_view_accepted_orders:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->delivery_view_accepted_orders: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**| Session ID of the delivery person | 

### Return type

[**List[Order]**](Order.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delivery_view_waiting_orders**
> List[Order] delivery_view_waiting_orders(session_id)

View Waiting Orders

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.order import Order
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | Session ID of the delivery person

    try:
        # View Waiting Orders
        api_response = api_instance.delivery_view_waiting_orders(session_id)
        print("The response of DefaultApi->delivery_view_waiting_orders:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->delivery_view_waiting_orders: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**| Session ID of the delivery person | 

### Return type

[**List[Order]**](Order.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_file**
> str get_file(file_id, session_id=session_id)

Get file by file ID

Retrieve a file, typically an image, based on the provided file ID.

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    file_id = 'file_id_example' # str | ID of the file to retrieve
    session_id = 'session_id_example' # str |  (optional)

    try:
        # Get file by file ID
        api_response = api_instance.get_file(file_id, session_id=session_id)
        print("The response of DefaultApi->get_file:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->get_file: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file_id** | **str**| ID of the file to retrieve | 
 **session_id** | **str**|  | [optional] 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: image/*

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |
**404** | File Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_orders**
> VendorGetRequestedOrders200ResponseInner get_orders(session_id, order_id)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_get_requested_orders200_response_inner import VendorGetRequestedOrders200ResponseInner
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    order_id = 'order_id_example' # str | 

    try:
        api_response = api_instance.get_orders(session_id, order_id)
        print("The response of DefaultApi->get_orders:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->get_orders: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **order_id** | **str**|  | 

### Return type

[**VendorGetRequestedOrders200ResponseInner**](VendorGetRequestedOrders200ResponseInner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_product**
> FoodItemFull get_product(id)

Get Product Details

Retrieve details for a specific item by providing its ID

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.food_item_full import FoodItemFull
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    id = 'id_example' # str | ID of the item to retrieve details for

    try:
        # Get Product Details
        api_response = api_instance.get_product(id)
        print("The response of DefaultApi->get_product:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->get_product: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| ID of the item to retrieve details for | 

### Return type

[**FoodItemFull**](FoodItemFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**404** | File Not Found |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_profile**
> Profile get_profile(session_id)



Get information about the another user from username if the current user is allowed to do so. Anyone can see information about all the Vendors, Delivery Persons and the user himself.

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.profile import Profile
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 

    try:
        api_response = api_instance.get_profile(session_id)
        print("The response of DefaultApi->get_profile:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->get_profile: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 

### Return type

[**Profile**](Profile.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **login**
> str login(login_request)

Login to user account

Get username and password and authenticate the user. Returns sessionId for further requests

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.login_request import LoginRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    login_request = FoodDeliveryAPI.LoginRequest() # LoginRequest | 

    try:
        # Login to user account
        api_response = api_instance.login(login_request)
        print("The response of DefaultApi->login:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->login: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **login_request** | [**LoginRequest**](LoginRequest.md)|  | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **login_vendor**
> str login_vendor(login_request)

Login to vendor account

Get username and password and authenticate the vendor. Returns sessionId for further requests

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.login_request import LoginRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    login_request = FoodDeliveryAPI.LoginRequest() # LoginRequest | 

    try:
        # Login to vendor account
        api_response = api_instance.login_vendor(login_request)
        print("The response of DefaultApi->login_vendor:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->login_vendor: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **login_request** | [**LoginRequest**](LoginRequest.md)|  | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **place_order**
> str place_order(session_id, place_order_request)

Place the order

Place the order from the cart, with item id as key and quantity as value.

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.place_order_request import PlaceOrderRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    place_order_request = FoodDeliveryAPI.PlaceOrderRequest() # PlaceOrderRequest | 

    try:
        # Place the order
        api_response = api_instance.place_order(session_id, place_order_request)
        print("The response of DefaultApi->place_order:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->place_order: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **place_order_request** | [**PlaceOrderRequest**](PlaceOrderRequest.md)|  | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **put_file**
> str put_file(session_id, body)

Upload a file

Upload an image to server for referencing elsewhere.

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    body = None # bytearray | 

    try:
        # Upload a file
        api_response = api_instance.put_file(session_id, body)
        print("The response of DefaultApi->put_file:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->put_file: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **body** | **bytearray**|  | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: image/*
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized (if user not valid to upload file) |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **query**
> List[FoodItem] query(session_id, query=query)

Search for items

Search for items based on search query and filters (Authentication is not necessary)

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.food_item import FoodItem
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    query = 'query_example' # str |  (optional)

    try:
        # Search for items
        api_response = api_instance.query(session_id, query=query)
        print("The response of DefaultApi->query:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->query: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **query** | **str**|  | [optional] 

### Return type

[**List[FoodItem]**](FoodItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **register**
> str register(user_details)

Register a new consumer?

Create a new user account with unique username, strong password for authentication and other user info.

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.user_details import UserDetails
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    user_details = FoodDeliveryAPI.UserDetails() # UserDetails | 

    try:
        # Register a new consumer?
        api_response = api_instance.register(user_details)
        print("The response of DefaultApi->register:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->register: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_details** | [**UserDetails**](UserDetails.md)|  | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**409** | Conflict |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **register_vendor**
> str register_vendor(vendor_details)

Register a new vendor

Create a new vendor account with unique username, strong password for authentication and other vendor info.

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_details import VendorDetails
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    vendor_details = FoodDeliveryAPI.VendorDetails() # VendorDetails | 

    try:
        # Register a new vendor
        api_response = api_instance.register_vendor(vendor_details)
        print("The response of DefaultApi->register_vendor:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->register_vendor: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **vendor_details** | [**VendorDetails**](VendorDetails.md)|  | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**409** | Conflict |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **update_profile**
> Profile update_profile(session_id, profile)



Update userinfo of the user.

### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.profile import Profile
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    profile = FoodDeliveryAPI.Profile() # Profile | 

    try:
        api_response = api_instance.update_profile(session_id, profile)
        print("The response of DefaultApi->update_profile:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->update_profile: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **profile** | [**Profile**](Profile.md)|  | 

### Return type

[**Profile**](Profile.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **vendor_add_product**
> str vendor_add_product(session_id, vendor_add_product_request)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_add_product_request import VendorAddProductRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    vendor_add_product_request = FoodDeliveryAPI.VendorAddProductRequest() # VendorAddProductRequest | 

    try:
        api_response = api_instance.vendor_add_product(session_id, vendor_add_product_request)
        print("The response of DefaultApi->vendor_add_product:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->vendor_add_product: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **vendor_add_product_request** | [**VendorAddProductRequest**](VendorAddProductRequest.md)|  | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **vendor_add_product_images**
> List[str] vendor_add_product_images(session_id, vendor_add_product_images_request)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_add_product_images_request import VendorAddProductImagesRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    vendor_add_product_images_request = FoodDeliveryAPI.VendorAddProductImagesRequest() # VendorAddProductImagesRequest | 

    try:
        api_response = api_instance.vendor_add_product_images(session_id, vendor_add_product_images_request)
        print("The response of DefaultApi->vendor_add_product_images:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->vendor_add_product_images: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **vendor_add_product_images_request** | [**VendorAddProductImagesRequest**](VendorAddProductImagesRequest.md)|  | 

### Return type

**List[str]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **vendor_change_availabile**
> vendor_change_availabile(session_id, vendor_change_product_availabile_request)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_change_product_availabile_request import VendorChangeProductAvailabileRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    vendor_change_product_availabile_request = FoodDeliveryAPI.VendorChangeProductAvailabileRequest() # VendorChangeProductAvailabileRequest | 

    try:
        api_instance.vendor_change_availabile(session_id, vendor_change_product_availabile_request)
    except Exception as e:
        print("Exception when calling DefaultApi->vendor_change_availabile: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **vendor_change_product_availabile_request** | [**VendorChangeProductAvailabileRequest**](VendorChangeProductAvailabileRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **vendor_change_product_availabile**
> vendor_change_product_availabile(session_id, vendor_change_product_availabile_request)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_change_product_availabile_request import VendorChangeProductAvailabileRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    vendor_change_product_availabile_request = FoodDeliveryAPI.VendorChangeProductAvailabileRequest() # VendorChangeProductAvailabileRequest | 

    try:
        api_instance.vendor_change_product_availabile(session_id, vendor_change_product_availabile_request)
    except Exception as e:
        print("Exception when calling DefaultApi->vendor_change_product_availabile: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **vendor_change_product_availabile_request** | [**VendorChangeProductAvailabileRequest**](VendorChangeProductAvailabileRequest.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **vendor_edit_product**
> FoodItemFull vendor_edit_product(session_id, vendor_edit_product_request)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.food_item_full import FoodItemFull
from FoodDeliveryAPI.models.vendor_edit_product_request import VendorEditProductRequest
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 
    vendor_edit_product_request = FoodDeliveryAPI.VendorEditProductRequest() # VendorEditProductRequest | 

    try:
        api_response = api_instance.vendor_edit_product(session_id, vendor_edit_product_request)
        print("The response of DefaultApi->vendor_edit_product:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->vendor_edit_product: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 
 **vendor_edit_product_request** | [**VendorEditProductRequest**](VendorEditProductRequest.md)|  | 

### Return type

[**FoodItemFull**](FoodItemFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **vendor_get_accepted_orders**
> List[VendorGetRequestedOrders200ResponseInner] vendor_get_accepted_orders(session_id)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_get_requested_orders200_response_inner import VendorGetRequestedOrders200ResponseInner
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 

    try:
        api_response = api_instance.vendor_get_accepted_orders(session_id)
        print("The response of DefaultApi->vendor_get_accepted_orders:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->vendor_get_accepted_orders: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 

### Return type

[**List[VendorGetRequestedOrders200ResponseInner]**](VendorGetRequestedOrders200ResponseInner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **vendor_get_requested_orders**
> List[VendorGetRequestedOrders200ResponseInner] vendor_get_requested_orders(session_id)



### Example


```python
import time
import os
import FoodDeliveryAPI
from FoodDeliveryAPI.models.vendor_get_requested_orders200_response_inner import VendorGetRequestedOrders200ResponseInner
from FoodDeliveryAPI.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to http://13.233.99.87:8080
# See configuration.py for a list of all supported configuration parameters.
configuration = FoodDeliveryAPI.Configuration(
    host = "http://13.233.99.87:8080"
)


# Enter a context with an instance of the API client
with FoodDeliveryAPI.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = FoodDeliveryAPI.DefaultApi(api_client)
    session_id = 'session_id_example' # str | 

    try:
        api_response = api_instance.vendor_get_requested_orders(session_id)
        print("The response of DefaultApi->vendor_get_requested_orders:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling DefaultApi->vendor_get_requested_orders: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **session_id** | **str**|  | 

### Return type

[**List[VendorGetRequestedOrders200ResponseInner]**](VendorGetRequestedOrders200ResponseInner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

