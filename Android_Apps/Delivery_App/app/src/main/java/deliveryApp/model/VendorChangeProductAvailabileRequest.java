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

import io.swagger.annotations.*;

@ApiModel(description = "")
public class VendorChangeProductAvailabileRequest {
  
  @SerializedName("itemId")
  private String itemId = null;
  @SerializedName("status")
  private Boolean status = null;

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
   * Weather the item is available or not
   **/
  @ApiModelProperty(required = true, value = "Weather the item is available or not")
  public Boolean getStatus() {
    return status;
  }
  public void setStatus(Boolean status) {
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
    VendorChangeProductAvailabileRequest vendorChangeProductAvailabileRequest = (VendorChangeProductAvailabileRequest) o;
    return (this.itemId == null ? vendorChangeProductAvailabileRequest.itemId == null : this.itemId.equals(vendorChangeProductAvailabileRequest.itemId)) &&
        (this.status == null ? vendorChangeProductAvailabileRequest.status == null : this.status.equals(vendorChangeProductAvailabileRequest.status));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.itemId == null ? 0: this.itemId.hashCode());
    result = 31 * result + (this.status == null ? 0: this.status.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class VendorChangeProductAvailabileRequest {\n");
    
    sb.append("  itemId: ").append(itemId).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
