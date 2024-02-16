/**
 * FDS
 * This API allows to communicate with FDS (Food Delivery System) server. 
 *
 * The version of the OpenAPI document: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.openapi.deliveryApp.model;

import com.openapi.deliveryApp.model.Filter;
import java.util.*;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class QueryRequest {
  
  @SerializedName("query")
  private String query = null;
  @SerializedName("filters")
  private List<Filter> filters = null;

  /**
   * Can be empty
   **/
  @ApiModelProperty(value = "Can be empty")
  public String getQuery() {
    return query;
  }
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<Filter> getFilters() {
    return filters;
  }
  public void setFilters(List<Filter> filters) {
    this.filters = filters;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryRequest queryRequest = (QueryRequest) o;
    return (this.query == null ? queryRequest.query == null : this.query.equals(queryRequest.query)) &&
        (this.filters == null ? queryRequest.filters == null : this.filters.equals(queryRequest.filters));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.query == null ? 0: this.query.hashCode());
    result = 31 * result + (this.filters == null ? 0: this.filters.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryRequest {\n");
    
    sb.append("  query: ").append(query).append("\n");
    sb.append("  filters: ").append(filters).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}