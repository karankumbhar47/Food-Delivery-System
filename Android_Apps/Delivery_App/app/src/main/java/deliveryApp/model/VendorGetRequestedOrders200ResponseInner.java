/**
 * FDS
 * This API allows to communicate with FDS (Food Delivery System) server. 
 *
 * The version of the OpenAPI document: 0.0.3
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package deliveryApp.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.*;

@ApiModel(description = "")
public class VendorGetRequestedOrders200ResponseInner {
  
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
  private BigDecimal price = null;

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
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VendorGetRequestedOrders200ResponseInner vendorGetRequestedOrders200ResponseInner = (VendorGetRequestedOrders200ResponseInner) o;
    return (this.orderId == null ? vendorGetRequestedOrders200ResponseInner.orderId == null : this.orderId.equals(vendorGetRequestedOrders200ResponseInner.orderId)) &&
        (this.userId == null ? vendorGetRequestedOrders200ResponseInner.userId == null : this.userId.equals(vendorGetRequestedOrders200ResponseInner.userId)) &&
        (this.vendorID == null ? vendorGetRequestedOrders200ResponseInner.vendorID == null : this.vendorID.equals(vendorGetRequestedOrders200ResponseInner.vendorID)) &&
        (this.pickupLocation == null ? vendorGetRequestedOrders200ResponseInner.pickupLocation == null : this.pickupLocation.equals(vendorGetRequestedOrders200ResponseInner.pickupLocation)) &&
        (this.orderItems == null ? vendorGetRequestedOrders200ResponseInner.orderItems == null : this.orderItems.equals(vendorGetRequestedOrders200ResponseInner.orderItems)) &&
        (this.location == null ? vendorGetRequestedOrders200ResponseInner.location == null : this.location.equals(vendorGetRequestedOrders200ResponseInner.location)) &&
        (this.price == null ? vendorGetRequestedOrders200ResponseInner.price == null : this.price.equals(vendorGetRequestedOrders200ResponseInner.price));
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
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class VendorGetRequestedOrders200ResponseInner {\n");
    
    sb.append("  orderId: ").append(orderId).append("\n");
    sb.append("  userId: ").append(userId).append("\n");
    sb.append("  vendorID: ").append(vendorID).append("\n");
    sb.append("  pickupLocation: ").append(pickupLocation).append("\n");
    sb.append("  orderItems: ").append(orderItems).append("\n");
    sb.append("  location: ").append(location).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
