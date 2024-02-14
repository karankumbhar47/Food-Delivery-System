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

package deliveryApp.invoker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.openapi.deliveryApp.model.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class JsonUtil {
  public static GsonBuilder gsonBuilder;

  static {
    gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
      public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Date(json.getAsJsonPrimitive().getAsLong());
      }
    });
  }

  public static Gson getGson() {
    return gsonBuilder.create();
  }

  public static String serialize(Object obj){
    return getGson().toJson(obj);
  }

  public static <T> T deserializeToList(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
  }

  public static <T> T deserializeToObject(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
  }

  public static Type getListTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("DeliveryAcceptOrderRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<DeliveryAcceptOrderRequest>>(){}.getType();
    }
    
    if ("DeliveryDropRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<DeliveryDropRequest>>(){}.getType();
    }
    
    if ("Filter".equalsIgnoreCase(className)) {
      return new TypeToken<List<Filter>>(){}.getType();
    }
    
    if ("FoodItem".equalsIgnoreCase(className)) {
      return new TypeToken<List<FoodItem>>(){}.getType();
    }
    
    if ("FoodItemFull".equalsIgnoreCase(className)) {
      return new TypeToken<List<FoodItemFull>>(){}.getType();
    }
    
    if ("LoginRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<LoginRequest>>(){}.getType();
    }
    
    if ("Order".equalsIgnoreCase(className)) {
      return new TypeToken<List<Order>>(){}.getType();
    }
    
    if ("OrderItem".equalsIgnoreCase(className)) {
      return new TypeToken<List<OrderItem>>(){}.getType();
    }
    
    if ("PlaceOrderRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<PlaceOrderRequest>>(){}.getType();
    }
    
    if ("PlaceOrderRequestItemCartInner".equalsIgnoreCase(className)) {
      return new TypeToken<List<PlaceOrderRequestItemCartInner>>(){}.getType();
    }
    
    if ("Profile".equalsIgnoreCase(className)) {
      return new TypeToken<List<Profile>>(){}.getType();
    }
    
    if ("UserDetails".equalsIgnoreCase(className)) {
      return new TypeToken<List<UserDetails>>(){}.getType();
    }
    
    if ("VendorAddProductImagesRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<VendorAddProductImagesRequest>>(){}.getType();
    }
    
    if ("VendorAddProductRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<VendorAddProductRequest>>(){}.getType();
    }
    
    if ("VendorChangeProductAvailabileRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<VendorChangeProductAvailabileRequest>>(){}.getType();
    }
    
    if ("VendorDetails".equalsIgnoreCase(className)) {
      return new TypeToken<List<VendorDetails>>(){}.getType();
    }
    
    if ("VendorEditProductRequest".equalsIgnoreCase(className)) {
      return new TypeToken<List<VendorEditProductRequest>>(){}.getType();
    }
    
    if ("VendorGetRequestedOrders200ResponseInner".equalsIgnoreCase(className)) {
      return new TypeToken<List<VendorGetRequestedOrders200ResponseInner>>(){}.getType();
    }
    
    if ("VendorGetRequestedOrders200ResponseInnerOrderItemsInner".equalsIgnoreCase(className)) {
      return new TypeToken<List<VendorGetRequestedOrders200ResponseInnerOrderItemsInner>>(){}.getType();
    }
    
    return new TypeToken<List<Object>>(){}.getType();
  }

  public static Type getTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("DeliveryAcceptOrderRequest".equalsIgnoreCase(className)) {
      return new TypeToken<DeliveryAcceptOrderRequest>(){}.getType();
    }
    
    if ("DeliveryDropRequest".equalsIgnoreCase(className)) {
      return new TypeToken<DeliveryDropRequest>(){}.getType();
    }
    
    if ("Filter".equalsIgnoreCase(className)) {
      return new TypeToken<Filter>(){}.getType();
    }
    
    if ("FoodItem".equalsIgnoreCase(className)) {
      return new TypeToken<FoodItem>(){}.getType();
    }
    
    if ("FoodItemFull".equalsIgnoreCase(className)) {
      return new TypeToken<FoodItemFull>(){}.getType();
    }
    
    if ("LoginRequest".equalsIgnoreCase(className)) {
      return new TypeToken<LoginRequest>(){}.getType();
    }
    
    if ("Order".equalsIgnoreCase(className)) {
      return new TypeToken<Order>(){}.getType();
    }
    
    if ("OrderItem".equalsIgnoreCase(className)) {
      return new TypeToken<OrderItem>(){}.getType();
    }
    
    if ("PlaceOrderRequest".equalsIgnoreCase(className)) {
      return new TypeToken<PlaceOrderRequest>(){}.getType();
    }
    
    if ("PlaceOrderRequestItemCartInner".equalsIgnoreCase(className)) {
      return new TypeToken<PlaceOrderRequestItemCartInner>(){}.getType();
    }
    
    if ("Profile".equalsIgnoreCase(className)) {
      return new TypeToken<Profile>(){}.getType();
    }
    
    if ("UserDetails".equalsIgnoreCase(className)) {
      return new TypeToken<UserDetails>(){}.getType();
    }
    
    if ("VendorAddProductImagesRequest".equalsIgnoreCase(className)) {
      return new TypeToken<VendorAddProductImagesRequest>(){}.getType();
    }
    
    if ("VendorAddProductRequest".equalsIgnoreCase(className)) {
      return new TypeToken<VendorAddProductRequest>(){}.getType();
    }
    
    if ("VendorChangeProductAvailabileRequest".equalsIgnoreCase(className)) {
      return new TypeToken<VendorChangeProductAvailabileRequest>(){}.getType();
    }
    
    if ("VendorDetails".equalsIgnoreCase(className)) {
      return new TypeToken<VendorDetails>(){}.getType();
    }
    
    if ("VendorEditProductRequest".equalsIgnoreCase(className)) {
      return new TypeToken<VendorEditProductRequest>(){}.getType();
    }
    
    if ("VendorGetRequestedOrders200ResponseInner".equalsIgnoreCase(className)) {
      return new TypeToken<VendorGetRequestedOrders200ResponseInner>(){}.getType();
    }
    
    if ("VendorGetRequestedOrders200ResponseInnerOrderItemsInner".equalsIgnoreCase(className)) {
      return new TypeToken<VendorGetRequestedOrders200ResponseInnerOrderItemsInner>(){}.getType();
    }
    
    return new TypeToken<Object>(){}.getType();
  }

};
