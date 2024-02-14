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
public class FoodItemFull {
  
  @SerializedName("itemId")
  private String itemId = null;
  @SerializedName("itemName")
  private String itemName = null;
  @SerializedName("thumbnailPicture")
  private String thumbnailPicture = null;
  @SerializedName("price")
  private BigDecimal price = null;
  @SerializedName("vendorName")
  private String vendorName = null;
  @SerializedName("vendorLocation")
  private String vendorLocation = null;
  @SerializedName("starRating")
  private BigDecimal starRating = null;
  @SerializedName("isAvailable")
  private Boolean isAvailable = null;
  @SerializedName("maxQuantity")
  private Integer maxQuantity = null;
  @SerializedName("imageUrls")
  private List<String> imageUrls = null;
  @SerializedName("tags")
  private List<String> tags = null;

  /**
   * ID of the item
   **/
  @ApiModelProperty(value = "ID of the item")
  public String getItemId() {
    return itemId;
  }
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  /**
   * Name of the item
   **/
  @ApiModelProperty(value = "Name of the item")
  public String getItemName() {
    return itemName;
  }
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  /**
   * 64bit encoded thumbnail picture
   **/
  @ApiModelProperty(value = "64bit encoded thumbnail picture")
  public String getThumbnailPicture() {
    return thumbnailPicture;
  }
  public void setThumbnailPicture(String thumbnailPicture) {
    this.thumbnailPicture = thumbnailPicture;
  }

  /**
   * Price of the item
   **/
  @ApiModelProperty(value = "Price of the item")
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * Name of the vendor
   **/
  @ApiModelProperty(value = "Name of the vendor")
  public String getVendorName() {
    return vendorName;
  }
  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  /**
   * Location of the vendor
   **/
  @ApiModelProperty(value = "Location of the vendor")
  public String getVendorLocation() {
    return vendorLocation;
  }
  public void setVendorLocation(String vendorLocation) {
    this.vendorLocation = vendorLocation;
  }

  /**
   * Star rating of the item
   **/
  @ApiModelProperty(value = "Star rating of the item")
  public BigDecimal getStarRating() {
    return starRating;
  }
  public void setStarRating(BigDecimal starRating) {
    this.starRating = starRating;
  }

  /**
   * Indicates if the item is available
   **/
  @ApiModelProperty(value = "Indicates if the item is available")
  public Boolean getIsAvailable() {
    return isAvailable;
  }
  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  /**
   * Maximum quantity available
   **/
  @ApiModelProperty(value = "Maximum quantity available")
  public Integer getMaxQuantity() {
    return maxQuantity;
  }
  public void setMaxQuantity(Integer maxQuantity) {
    this.maxQuantity = maxQuantity;
  }

  /**
   * URLs of additional images
   **/
  @ApiModelProperty(value = "URLs of additional images")
  public List<String> getImageUrls() {
    return imageUrls;
  }
  public void setImageUrls(List<String> imageUrls) {
    this.imageUrls = imageUrls;
  }

  /**
   * tags related to food item
   **/
  @ApiModelProperty(value = "tags related to food item")
  public List<String> getTags() {
    return tags;
  }
  public void setTags(List<String> tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodItemFull foodItemFull = (FoodItemFull) o;
    return (this.itemId == null ? foodItemFull.itemId == null : this.itemId.equals(foodItemFull.itemId)) &&
        (this.itemName == null ? foodItemFull.itemName == null : this.itemName.equals(foodItemFull.itemName)) &&
        (this.thumbnailPicture == null ? foodItemFull.thumbnailPicture == null : this.thumbnailPicture.equals(foodItemFull.thumbnailPicture)) &&
        (this.price == null ? foodItemFull.price == null : this.price.equals(foodItemFull.price)) &&
        (this.vendorName == null ? foodItemFull.vendorName == null : this.vendorName.equals(foodItemFull.vendorName)) &&
        (this.vendorLocation == null ? foodItemFull.vendorLocation == null : this.vendorLocation.equals(foodItemFull.vendorLocation)) &&
        (this.starRating == null ? foodItemFull.starRating == null : this.starRating.equals(foodItemFull.starRating)) &&
        (this.isAvailable == null ? foodItemFull.isAvailable == null : this.isAvailable.equals(foodItemFull.isAvailable)) &&
        (this.maxQuantity == null ? foodItemFull.maxQuantity == null : this.maxQuantity.equals(foodItemFull.maxQuantity)) &&
        (this.imageUrls == null ? foodItemFull.imageUrls == null : this.imageUrls.equals(foodItemFull.imageUrls)) &&
        (this.tags == null ? foodItemFull.tags == null : this.tags.equals(foodItemFull.tags));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.itemId == null ? 0: this.itemId.hashCode());
    result = 31 * result + (this.itemName == null ? 0: this.itemName.hashCode());
    result = 31 * result + (this.thumbnailPicture == null ? 0: this.thumbnailPicture.hashCode());
    result = 31 * result + (this.price == null ? 0: this.price.hashCode());
    result = 31 * result + (this.vendorName == null ? 0: this.vendorName.hashCode());
    result = 31 * result + (this.vendorLocation == null ? 0: this.vendorLocation.hashCode());
    result = 31 * result + (this.starRating == null ? 0: this.starRating.hashCode());
    result = 31 * result + (this.isAvailable == null ? 0: this.isAvailable.hashCode());
    result = 31 * result + (this.maxQuantity == null ? 0: this.maxQuantity.hashCode());
    result = 31 * result + (this.imageUrls == null ? 0: this.imageUrls.hashCode());
    result = 31 * result + (this.tags == null ? 0: this.tags.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FoodItemFull {\n");
    
    sb.append("  itemId: ").append(itemId).append("\n");
    sb.append("  itemName: ").append(itemName).append("\n");
    sb.append("  thumbnailPicture: ").append(thumbnailPicture).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("  vendorName: ").append(vendorName).append("\n");
    sb.append("  vendorLocation: ").append(vendorLocation).append("\n");
    sb.append("  starRating: ").append(starRating).append("\n");
    sb.append("  isAvailable: ").append(isAvailable).append("\n");
    sb.append("  maxQuantity: ").append(maxQuantity).append("\n");
    sb.append("  imageUrls: ").append(imageUrls).append("\n");
    sb.append("  tags: ").append(tags).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
