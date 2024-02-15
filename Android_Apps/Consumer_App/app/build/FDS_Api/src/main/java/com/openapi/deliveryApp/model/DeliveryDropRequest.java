/**
 * FDS
 * This API allows to communicate with FDS (Food Delivery System) server. 
 *
 * The version of the OpenAPI document: 0.0.6
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.openapi.deliveryApp.model;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class DeliveryDropRequest {
  
  @SerializedName("orderId")
  private String orderId = null;
  @SerializedName("otp")
  private Integer otp = null;

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
  public Integer getOtp() {
    return otp;
  }
  public void setOtp(Integer otp) {
    this.otp = otp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeliveryDropRequest deliveryDropRequest = (DeliveryDropRequest) o;
    return (this.orderId == null ? deliveryDropRequest.orderId == null : this.orderId.equals(deliveryDropRequest.orderId)) &&
        (this.otp == null ? deliveryDropRequest.otp == null : this.otp.equals(deliveryDropRequest.otp));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.orderId == null ? 0: this.orderId.hashCode());
    result = 31 * result + (this.otp == null ? 0: this.otp.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeliveryDropRequest {\n");
    
    sb.append("  orderId: ").append(orderId).append("\n");
    sb.append("  otp: ").append(otp).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
