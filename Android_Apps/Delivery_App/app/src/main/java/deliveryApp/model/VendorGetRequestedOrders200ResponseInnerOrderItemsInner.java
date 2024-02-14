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

import io.swagger.annotations.*;

@ApiModel(description = "")
public class VendorGetRequestedOrders200ResponseInnerOrderItemsInner {
  
  @SerializedName("itemId")
  private String itemId = null;
  @SerializedName("quantity")
  private BigDecimal quantity = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getItemId() {
    return itemId;
  }
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  /**
   * Quantity of the specific item.
   **/
  @ApiModelProperty(required = true, value = "Quantity of the specific item.")
  public BigDecimal getQuantity() {
    return quantity;
  }
  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VendorGetRequestedOrders200ResponseInnerOrderItemsInner vendorGetRequestedOrders200ResponseInnerOrderItemsInner = (VendorGetRequestedOrders200ResponseInnerOrderItemsInner) o;
    return (this.itemId == null ? vendorGetRequestedOrders200ResponseInnerOrderItemsInner.itemId == null : this.itemId.equals(vendorGetRequestedOrders200ResponseInnerOrderItemsInner.itemId)) &&
        (this.quantity == null ? vendorGetRequestedOrders200ResponseInnerOrderItemsInner.quantity == null : this.quantity.equals(vendorGetRequestedOrders200ResponseInnerOrderItemsInner.quantity));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.itemId == null ? 0: this.itemId.hashCode());
    result = 31 * result + (this.quantity == null ? 0: this.quantity.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class VendorGetRequestedOrders200ResponseInnerOrderItemsInner {\n");
    
    sb.append("  itemId: ").append(itemId).append("\n");
    sb.append("  quantity: ").append(quantity).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
