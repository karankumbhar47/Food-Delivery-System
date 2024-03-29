/**
 * FDS
 * This API allows to communicate with FDS (Food Delivery System) server. 
 *
 * The version of the OpenAPI document: 0.0.7
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.openapi.deliveryApp.model;

import com.openapi.deliveryApp.model.VendorGetRequestedOrders200ResponseInnerOrderItemsInner;
import java.util.*;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class GetOrders200Response {
  
  @SerializedName("orderId")
  private String orderId = null;
  @SerializedName("userId")
  private String userId = null;
  @SerializedName("vendorID")
  private String vendorID = null;
  @SerializedName("pickupLocation")
  private String pickupLocation = null;
  @SerializedName("orderItems")
  private List<VendorGetRequestedOrders200ResponseInnerOrderItemsInner> orderItems = null;
  @SerializedName("location")
  private String location = null;
  @SerializedName("price")
  private Float price = null;
  @SerializedName("status")
  private String status = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public String getOrderId() {
    return orderId;
  }
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getVendorID() {
    return vendorID;
  }
  public void setVendorID(String vendorID) {
    this.vendorID = vendorID;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getPickupLocation() {
    return pickupLocation;
  }
  public void setPickupLocation(String pickupLocation) {
    this.pickupLocation = pickupLocation;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<VendorGetRequestedOrders200ResponseInnerOrderItemsInner> getOrderItems() {
    return orderItems;
  }
  public void setOrderItems(List<VendorGetRequestedOrders200ResponseInnerOrderItemsInner> orderItems) {
    this.orderItems = orderItems;
  }

  /**
   * Delivery location.
   **/
  @ApiModelProperty(value = "Delivery location.")
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Float getPrice() {
    return price;
  }
  public void setPrice(Float price) {
    this.price = price;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetOrders200Response getOrders200Response = (GetOrders200Response) o;
    return (this.orderId == null ? getOrders200Response.orderId == null : this.orderId.equals(getOrders200Response.orderId)) &&
        (this.userId == null ? getOrders200Response.userId == null : this.userId.equals(getOrders200Response.userId)) &&
        (this.vendorID == null ? getOrders200Response.vendorID == null : this.vendorID.equals(getOrders200Response.vendorID)) &&
        (this.pickupLocation == null ? getOrders200Response.pickupLocation == null : this.pickupLocation.equals(getOrders200Response.pickupLocation)) &&
        (this.orderItems == null ? getOrders200Response.orderItems == null : this.orderItems.equals(getOrders200Response.orderItems)) &&
        (this.location == null ? getOrders200Response.location == null : this.location.equals(getOrders200Response.location)) &&
        (this.price == null ? getOrders200Response.price == null : this.price.equals(getOrders200Response.price)) &&
        (this.status == null ? getOrders200Response.status == null : this.status.equals(getOrders200Response.status));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.orderId == null ? 0: this.orderId.hashCode());
    result = 31 * result + (this.userId == null ? 0: this.userId.hashCode());
    result = 31 * result + (this.vendorID == null ? 0: this.vendorID.hashCode());
    result = 31 * result + (this.pickupLocation == null ? 0: this.pickupLocation.hashCode());
    result = 31 * result + (this.orderItems == null ? 0: this.orderItems.hashCode());
    result = 31 * result + (this.location == null ? 0: this.location.hashCode());
    result = 31 * result + (this.price == null ? 0: this.price.hashCode());
    result = 31 * result + (this.status == null ? 0: this.status.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetOrders200Response {\n");
    
    sb.append("  orderId: ").append(orderId).append("\n");
    sb.append("  userId: ").append(userId).append("\n");
    sb.append("  vendorID: ").append(vendorID).append("\n");
    sb.append("  pickupLocation: ").append(pickupLocation).append("\n");
    sb.append("  orderItems: ").append(orderItems).append("\n");
    sb.append("  location: ").append(location).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
