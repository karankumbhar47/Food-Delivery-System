# openapi-android-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-android-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "org.openapitools:openapi-android-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

- target/openapi-android-client-1.0.0.jar
- target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.openapi.deliveryApp.api.DefaultApi;

public class DefaultApiExample {

    public static void main(String[] args) {
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://13.233.99.87:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**checkProductAvailable**](docs/DefaultApi.md#checkProductAvailable) | **GET** /product/{id}/available | Check Availability
*DefaultApi* | [**confirmOrder**](docs/DefaultApi.md#confirmOrder) | **POST** /order/confirm | Confirm Order
*DefaultApi* | [**deliveryAcceptOrder**](docs/DefaultApi.md#deliveryAcceptOrder) | **POST** /delivery/acceptOrder | Accept Order
*DefaultApi* | [**deliveryDrop**](docs/DefaultApi.md#deliveryDrop) | **POST** /delivery/dropOrder | Drop Order
*DefaultApi* | [**deliveryPick**](docs/DefaultApi.md#deliveryPick) | **POST** /delivery/pickupOrder | Pickup Order
*DefaultApi* | [**deliveryViewAcceptedOrders**](docs/DefaultApi.md#deliveryViewAcceptedOrders) | **GET** /delivery/getAcceptedOrders | Get List of Accepted Orders
*DefaultApi* | [**deliveryViewWaitingOrders**](docs/DefaultApi.md#deliveryViewWaitingOrders) | **GET** /delivery/viewWaitingOrders | View Waiting Orders
*DefaultApi* | [**getFile**](docs/DefaultApi.md#getFile) | **GET** /file/{fileId} | Get file by file ID
*DefaultApi* | [**getOrders**](docs/DefaultApi.md#getOrders) | **GET** /order | 
*DefaultApi* | [**getProduct**](docs/DefaultApi.md#getProduct) | **GET** /product/{id} | Get Product Details
*DefaultApi* | [**getProfile**](docs/DefaultApi.md#getProfile) | **GET** /profile | 
*DefaultApi* | [**login**](docs/DefaultApi.md#login) | **POST** /login | Login to user account
*DefaultApi* | [**loginDeliveryPerson**](docs/DefaultApi.md#loginDeliveryPerson) | **POST** /deliveryPerson/login | Login to delivery person account
*DefaultApi* | [**loginVendor**](docs/DefaultApi.md#loginVendor) | **POST** /vendor/login | Login to vendor account
*DefaultApi* | [**placeOrder**](docs/DefaultApi.md#placeOrder) | **POST** /order/place | Place the order
*DefaultApi* | [**putFile**](docs/DefaultApi.md#putFile) | **PUT** /file | Upload a file
*DefaultApi* | [**query**](docs/DefaultApi.md#query) | **GET** /query | Search for items
*DefaultApi* | [**register**](docs/DefaultApi.md#register) | **POST** /register | Register a new consumer?
*DefaultApi* | [**registerDeliveryPerson**](docs/DefaultApi.md#registerDeliveryPerson) | **POST** /deliveryPerson/register | Register a new deliveryPerson
*DefaultApi* | [**registerVendor**](docs/DefaultApi.md#registerVendor) | **POST** /vendor/register | Register a new vendor
*DefaultApi* | [**updateProfile**](docs/DefaultApi.md#updateProfile) | **POST** /profile/update | 
*DefaultApi* | [**vendorAddProduct**](docs/DefaultApi.md#vendorAddProduct) | **POST** /vendor/product/add | 
*DefaultApi* | [**vendorChangeAvailabile**](docs/DefaultApi.md#vendorChangeAvailabile) | **POST** /vendor/changeAvailable | 
*DefaultApi* | [**vendorChangeProductAvailabile**](docs/DefaultApi.md#vendorChangeProductAvailabile) | **POST** /vendor/product/changeAvailable | 
*DefaultApi* | [**vendorEditProduct**](docs/DefaultApi.md#vendorEditProduct) | **POST** /vendor/product/edit | 
*DefaultApi* | [**vendorGetAcceptedOrders**](docs/DefaultApi.md#vendorGetAcceptedOrders) | **GET** /vendor/order/accepted | 
*DefaultApi* | [**vendorGetRequestedOrders**](docs/DefaultApi.md#vendorGetRequestedOrders) | **GET** /vendor/order/requested | 


## Documentation for Models

 - [DeliveryAcceptOrderRequest](docs/DeliveryAcceptOrderRequest.md)
 - [DeliveryDropRequest](docs/DeliveryDropRequest.md)
 - [DeliveryPersonDetails](docs/DeliveryPersonDetails.md)
 - [Filter](docs/Filter.md)
 - [FoodItem](docs/FoodItem.md)
 - [FoodItemFull](docs/FoodItemFull.md)
 - [GetOrders200Response](docs/GetOrders200Response.md)
 - [LoginRequest](docs/LoginRequest.md)
 - [Order](docs/Order.md)
 - [OrderItem](docs/OrderItem.md)
 - [PlaceOrderRequest](docs/PlaceOrderRequest.md)
 - [PlaceOrderRequestItemCartInner](docs/PlaceOrderRequestItemCartInner.md)
 - [Profile](docs/Profile.md)
 - [UserDetails](docs/UserDetails.md)
 - [VendorAddProductRequest](docs/VendorAddProductRequest.md)
 - [VendorChangeProductAvailabileRequest](docs/VendorChangeProductAvailabileRequest.md)
 - [VendorDetails](docs/VendorDetails.md)
 - [VendorEditProductRequest](docs/VendorEditProductRequest.md)
 - [VendorGetRequestedOrders200ResponseInner](docs/VendorGetRequestedOrders200ResponseInner.md)
 - [VendorGetRequestedOrders200ResponseInnerOrderItemsInner](docs/VendorGetRequestedOrders200ResponseInnerOrderItemsInner.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



