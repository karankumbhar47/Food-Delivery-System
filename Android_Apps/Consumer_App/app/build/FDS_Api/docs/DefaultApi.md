# DefaultApi

All URIs are relative to *http://13.233.99.87:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**checkProductAvailable**](DefaultApi.md#checkProductAvailable) | **GET** /product/{id}/available | Check Availability
[**confirmOrder**](DefaultApi.md#confirmOrder) | **POST** /order/confirm | Confirm Order
[**deliveryAcceptOrder**](DefaultApi.md#deliveryAcceptOrder) | **POST** /delivery/acceptOrder | Accept Order
[**deliveryDrop**](DefaultApi.md#deliveryDrop) | **POST** /delivery/dropOrder | Drop Order
[**deliveryPick**](DefaultApi.md#deliveryPick) | **POST** /delivery/pickupOrder | Pickup Order
[**deliveryViewAcceptedOrders**](DefaultApi.md#deliveryViewAcceptedOrders) | **GET** /delivery/getAcceptedOrders | Get List of Accepted Orders
[**deliveryViewWaitingOrders**](DefaultApi.md#deliveryViewWaitingOrders) | **GET** /delivery/viewWaitingOrders | View Waiting Orders
[**getFile**](DefaultApi.md#getFile) | **GET** /file | Get file by file ID
[**getOrders**](DefaultApi.md#getOrders) | **GET** /order | 
[**getProduct**](DefaultApi.md#getProduct) | **GET** /product/{id} | Get Product Details
[**getProfile**](DefaultApi.md#getProfile) | **GET** /profile | 
[**login**](DefaultApi.md#login) | **POST** /login | Login to user account
[**loginDeliveryPerson**](DefaultApi.md#loginDeliveryPerson) | **POST** /deliveryPerson/login | Login to delivery person account
[**loginVendor**](DefaultApi.md#loginVendor) | **POST** /vendor/login | Login to vendor account
[**placeOrder**](DefaultApi.md#placeOrder) | **POST** /order/place | Place the order
[**putFile**](DefaultApi.md#putFile) | **PUT** /file | Upload a file
[**query**](DefaultApi.md#query) | **GET** /query | Search for items
[**register**](DefaultApi.md#register) | **POST** /register | Register a new consumer?
[**registerDeliveryPerson**](DefaultApi.md#registerDeliveryPerson) | **POST** /deliveryPerson/register | Register a new deliveryPerson
[**registerVendor**](DefaultApi.md#registerVendor) | **POST** /vendor/register | Register a new vendor
[**updateProfile**](DefaultApi.md#updateProfile) | **POST** /profile/update | 
[**vendorAddProduct**](DefaultApi.md#vendorAddProduct) | **POST** /vendor/product/add | 
[**vendorAddProductImages**](DefaultApi.md#vendorAddProductImages) | **POST** /vendor/product/addImages | 
[**vendorChangeAvailabile**](DefaultApi.md#vendorChangeAvailabile) | **POST** /vendor/changeAvailable | 
[**vendorChangeProductAvailabile**](DefaultApi.md#vendorChangeProductAvailabile) | **POST** /vendor/product/changeAvailable | 
[**vendorEditProduct**](DefaultApi.md#vendorEditProduct) | **POST** /vendor/product/edit | 
[**vendorGetAcceptedOrders**](DefaultApi.md#vendorGetAcceptedOrders) | **GET** /vendor/order/accepted | 
[**vendorGetRequestedOrders**](DefaultApi.md#vendorGetRequestedOrders) | **GET** /vendor/order/requested | 



## checkProductAvailable

> Boolean checkProductAvailable(id, count)

Check Availability

Check the availability of a specific item by providing its ID and quantity count

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String id = null; // String | ID of the item to check availability for
Integer count = null; // Integer | Quantity count to check availability for
try {
    Boolean result = apiInstance.checkProductAvailable(id, count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#checkProductAvailable");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of the item to check availability for | [default to null]
 **count** | **Integer**| Quantity count to check availability for | [default to null]

### Return type

**Boolean**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## confirmOrder

> Integer confirmOrder(sessionId, body)

Confirm Order

Confirm Order Delivery

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
String body = "body_example"; // String | 
try {
    Integer result = apiInstance.confirmOrder(sessionId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#confirmOrder");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **body** | **String**|  |

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## deliveryAcceptOrder

> deliveryAcceptOrder(sessionId, deliveryAcceptOrderRequest)

Accept Order

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | Session ID of the delivery person
DeliveryAcceptOrderRequest deliveryAcceptOrderRequest = new DeliveryAcceptOrderRequest(); // DeliveryAcceptOrderRequest | 
try {
    apiInstance.deliveryAcceptOrder(sessionId, deliveryAcceptOrderRequest);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deliveryAcceptOrder");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**| Session ID of the delivery person | [default to null]
 **deliveryAcceptOrderRequest** | [**DeliveryAcceptOrderRequest**](DeliveryAcceptOrderRequest.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## deliveryDrop

> deliveryDrop(sessionId, deliveryDropRequest)

Drop Order

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | Session ID of the delivery person
DeliveryDropRequest deliveryDropRequest = new DeliveryDropRequest(); // DeliveryDropRequest | 
try {
    apiInstance.deliveryDrop(sessionId, deliveryDropRequest);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deliveryDrop");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**| Session ID of the delivery person | [default to null]
 **deliveryDropRequest** | [**DeliveryDropRequest**](DeliveryDropRequest.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## deliveryPick

> deliveryPick(sessionId, orderId)

Pickup Order

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | Session ID of the delivery person
String orderId = null; // String | ID of the order to be picked up
try {
    apiInstance.deliveryPick(sessionId, orderId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deliveryPick");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**| Session ID of the delivery person | [default to null]
 **orderId** | **String**| ID of the order to be picked up | [default to null]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


## deliveryViewAcceptedOrders

> List&lt;Order&gt; deliveryViewAcceptedOrders(sessionId)

Get List of Accepted Orders

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | Session ID of the delivery person
try {
    List<Order> result = apiInstance.deliveryViewAcceptedOrders(sessionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deliveryViewAcceptedOrders");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**| Session ID of the delivery person | [default to null]

### Return type

[**List&lt;Order&gt;**](Order.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## deliveryViewWaitingOrders

> List&lt;Order&gt; deliveryViewWaitingOrders(sessionId)

View Waiting Orders

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | Session ID of the delivery person
try {
    List<Order> result = apiInstance.deliveryViewWaitingOrders(sessionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deliveryViewWaitingOrders");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**| Session ID of the delivery person | [default to null]

### Return type

[**List&lt;Order&gt;**](Order.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## getFile

> File getFile(fileId, sessionId)

Get file by file ID

Retrieve a file, typically an image, based on the provided file ID.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String fileId = null; // String | ID of the file to retrieve
String sessionId = null; // String | 
try {
    File result = apiInstance.getFile(fileId, sessionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getFile");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fileId** | **String**| ID of the file to retrieve | [default to null]
 **sessionId** | **String**|  | [optional] [default to null]

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: image/*


## getOrders

> VendorGetRequestedOrders200ResponseInner getOrders(sessionId, orderId)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
String orderId = null; // String | 
try {
    VendorGetRequestedOrders200ResponseInner result = apiInstance.getOrders(sessionId, orderId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getOrders");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **orderId** | **String**|  | [default to null]

### Return type

[**VendorGetRequestedOrders200ResponseInner**](VendorGetRequestedOrders200ResponseInner.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## getProduct

> FoodItemFull getProduct(id)

Get Product Details

Retrieve details for a specific item by providing its ID

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String id = null; // String | ID of the item to retrieve details for
try {
    FoodItemFull result = apiInstance.getProduct(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getProduct");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of the item to retrieve details for | [default to null]

### Return type

[**FoodItemFull**](FoodItemFull.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## getProfile

> Profile getProfile(sessionId)



Get information about the another user from username if the current user is allowed to do so. Anyone can see information about all the Vendors, Delivery Persons and the user himself.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
try {
    Profile result = apiInstance.getProfile(sessionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getProfile");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]

### Return type

[**Profile**](Profile.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## login

> String login(loginRequest)

Login to user account

Get username and password and authenticate the user. Returns sessionId for further requests

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
LoginRequest loginRequest = new LoginRequest(); // LoginRequest | 
try {
    String result = apiInstance.login(loginRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#login");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loginRequest** | [**LoginRequest**](LoginRequest.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## loginDeliveryPerson

> String loginDeliveryPerson(loginRequest)

Login to delivery person account

Get username and password and authenticate the delivery person. Returns sessionId for further requests

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
LoginRequest loginRequest = new LoginRequest(); // LoginRequest | 
try {
    String result = apiInstance.loginDeliveryPerson(loginRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#loginDeliveryPerson");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loginRequest** | [**LoginRequest**](LoginRequest.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## loginVendor

> String loginVendor(loginRequest)

Login to vendor account

Get username and password and authenticate the vendor. Returns sessionId for further requests

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
LoginRequest loginRequest = new LoginRequest(); // LoginRequest | 
try {
    String result = apiInstance.loginVendor(loginRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#loginVendor");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loginRequest** | [**LoginRequest**](LoginRequest.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## placeOrder

> String placeOrder(sessionId, placeOrderRequest)

Place the order

Place the order from the cart, with item id as key and quantity as value.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest(); // PlaceOrderRequest | 
try {
    String result = apiInstance.placeOrder(sessionId, placeOrderRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#placeOrder");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **placeOrderRequest** | [**PlaceOrderRequest**](PlaceOrderRequest.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## putFile

> String putFile(sessionId, body)

Upload a file

Upload an image to server for referencing elsewhere.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
File body = new File("/path/to/file"); // File | 
try {
    String result = apiInstance.putFile(sessionId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#putFile");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **body** | **File**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: image/*
- **Accept**: application/json


## query

> List&lt;FoodItem&gt; query(sessionId, query)

Search for items

Search for items based on search query and filters (Authentication is not necessary)

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
String query = null; // String | 
try {
    List<FoodItem> result = apiInstance.query(sessionId, query);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#query");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **query** | **String**|  | [optional] [default to null]

### Return type

[**List&lt;FoodItem&gt;**](FoodItem.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## register

> String register(userDetails)

Register a new consumer?

Create a new user account with unique username, strong password for authentication and other user info.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
UserDetails userDetails = new UserDetails(); // UserDetails | 
try {
    String result = apiInstance.register(userDetails);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#register");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userDetails** | [**UserDetails**](UserDetails.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## registerDeliveryPerson

> String registerDeliveryPerson(deliveryPersonDetails)

Register a new deliveryPerson

Create a new deliveryPerson account with unique username, strong password for authentication and other deliveryPerson info.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
DeliveryPersonDetails deliveryPersonDetails = new DeliveryPersonDetails(); // DeliveryPersonDetails | 
try {
    String result = apiInstance.registerDeliveryPerson(deliveryPersonDetails);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#registerDeliveryPerson");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **deliveryPersonDetails** | [**DeliveryPersonDetails**](DeliveryPersonDetails.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## registerVendor

> String registerVendor(vendorDetails)

Register a new vendor

Create a new vendor account with unique username, strong password for authentication and other vendor info.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
VendorDetails vendorDetails = new VendorDetails(); // VendorDetails | 
try {
    String result = apiInstance.registerVendor(vendorDetails);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#registerVendor");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **vendorDetails** | [**VendorDetails**](VendorDetails.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## updateProfile

> Profile updateProfile(sessionId, profile)



Update userinfo of the user.

### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
Profile profile = new Profile(); // Profile | 
try {
    Profile result = apiInstance.updateProfile(sessionId, profile);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updateProfile");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **profile** | [**Profile**](Profile.md)|  |

### Return type

[**Profile**](Profile.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## vendorAddProduct

> String vendorAddProduct(sessionId, vendorAddProductRequest)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
VendorAddProductRequest vendorAddProductRequest = new VendorAddProductRequest(); // VendorAddProductRequest | 
try {
    String result = apiInstance.vendorAddProduct(sessionId, vendorAddProductRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorAddProduct");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **vendorAddProductRequest** | [**VendorAddProductRequest**](VendorAddProductRequest.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## vendorAddProductImages

> List&lt;String&gt; vendorAddProductImages(sessionId, vendorAddProductImagesRequest)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
VendorAddProductImagesRequest vendorAddProductImagesRequest = new VendorAddProductImagesRequest(); // VendorAddProductImagesRequest | 
try {
    List<String> result = apiInstance.vendorAddProductImages(sessionId, vendorAddProductImagesRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorAddProductImages");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **vendorAddProductImagesRequest** | [**VendorAddProductImagesRequest**](VendorAddProductImagesRequest.md)|  |

### Return type

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## vendorChangeAvailabile

> vendorChangeAvailabile(sessionId, vendorChangeProductAvailabileRequest)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
VendorChangeProductAvailabileRequest vendorChangeProductAvailabileRequest = new VendorChangeProductAvailabileRequest(); // VendorChangeProductAvailabileRequest | 
try {
    apiInstance.vendorChangeAvailabile(sessionId, vendorChangeProductAvailabileRequest);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorChangeAvailabile");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **vendorChangeProductAvailabileRequest** | [**VendorChangeProductAvailabileRequest**](VendorChangeProductAvailabileRequest.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## vendorChangeProductAvailabile

> vendorChangeProductAvailabile(sessionId, vendorChangeProductAvailabileRequest)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
VendorChangeProductAvailabileRequest vendorChangeProductAvailabileRequest = new VendorChangeProductAvailabileRequest(); // VendorChangeProductAvailabileRequest | 
try {
    apiInstance.vendorChangeProductAvailabile(sessionId, vendorChangeProductAvailabileRequest);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorChangeProductAvailabile");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **vendorChangeProductAvailabileRequest** | [**VendorChangeProductAvailabileRequest**](VendorChangeProductAvailabileRequest.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## vendorEditProduct

> FoodItemFull vendorEditProduct(sessionId, vendorEditProductRequest)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
VendorEditProductRequest vendorEditProductRequest = new VendorEditProductRequest(); // VendorEditProductRequest | 
try {
    FoodItemFull result = apiInstance.vendorEditProduct(sessionId, vendorEditProductRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorEditProduct");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]
 **vendorEditProductRequest** | [**VendorEditProductRequest**](VendorEditProductRequest.md)|  |

### Return type

[**FoodItemFull**](FoodItemFull.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## vendorGetAcceptedOrders

> List&lt;VendorGetRequestedOrders200ResponseInner&gt; vendorGetAcceptedOrders(sessionId)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
try {
    List<VendorGetRequestedOrders200ResponseInner> result = apiInstance.vendorGetAcceptedOrders(sessionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorGetAcceptedOrders");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]

### Return type

[**List&lt;VendorGetRequestedOrders200ResponseInner&gt;**](VendorGetRequestedOrders200ResponseInner.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## vendorGetRequestedOrders

> List&lt;VendorGetRequestedOrders200ResponseInner&gt; vendorGetRequestedOrders(sessionId)



### Example

```java
// Import classes:
//import com.openapi.deliveryApp.api.DefaultApi;

DefaultApi apiInstance = new DefaultApi();
String sessionId = null; // String | 
try {
    List<VendorGetRequestedOrders200ResponseInner> result = apiInstance.vendorGetRequestedOrders(sessionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorGetRequestedOrders");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sessionId** | **String**|  | [default to null]

### Return type

[**List&lt;VendorGetRequestedOrders200ResponseInner&gt;**](VendorGetRequestedOrders200ResponseInner.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

