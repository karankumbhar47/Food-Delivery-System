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

import com.openapi.deliveryApp.model.PlaceOrderRequestItemCartInner;
import java.util.*;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class PlaceOrderRequest {
  
  @SerializedName("itemCart")
  private List<PlaceOrderRequestItemCartInner> itemCart = null;
  @SerializedName("location")
  private String location = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public List<PlaceOrderRequestItemCartInner> getItemCart() {
    return itemCart;
  }
  public void setItemCart(List<PlaceOrderRequestItemCartInner> itemCart) {
    this.itemCart = itemCart;
  }

  /**
   * Delivery location.
   **/
  @ApiModelProperty(required = true, value = "Delivery location.")
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlaceOrderRequest placeOrderRequest = (PlaceOrderRequest) o;
    return (this.itemCart == null ? placeOrderRequest.itemCart == null : this.itemCart.equals(placeOrderRequest.itemCart)) &&
        (this.location == null ? placeOrderRequest.location == null : this.location.equals(placeOrderRequest.location));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.itemCart == null ? 0: this.itemCart.hashCode());
    result = 31 * result + (this.location == null ? 0: this.location.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlaceOrderRequest {\n");
    
    sb.append("  itemCart: ").append(itemCart).append("\n");
    sb.append("  location: ").append(location).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
