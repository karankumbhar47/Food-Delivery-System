package com.example.swiggy_lite;

import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.model.FoodItem;
import com.openapi.deliveryApp.model.FoodItemFull;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DummyData {
    public static final ArrayList<String> categoryList =  new ArrayList<>(Arrays.asList(
            "category_biryani", "category_burger", "category_cakes", "category_chinese",
            "category_chole_bature", "category_coffee", "category_dosa", "category_gulab_jamun",
            "category_ice_creams", "category_idli", "category_kebabs", "category_khichdi",
            "category_noodles", "category_north_indian", "category_paratha", "category_pasta",
            "category_pastry", "category_pizza", "category_rolls", "category_vada"
    ));

    public static List<FoodItemFull> dummyFoodList = getDummyFoodItemList();
    public static List<FoodItem> dummyFood = getDummyFoodItem();

    public static List<FoodItemFull> getDummyFoodItemList() {
        return Arrays.asList(
               createDummyFoodItem("a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0", "Delicious Pizza", "pizza_thumbnail.jpg", 15.99f, "Pizzeria Uno", "New York", 4.5f, true, 5,
                       Arrays.asList("pizza_image_1.jpg", "pizza_image_2.jpg"), Arrays.asList("Italian", "Pizza")),
               createDummyFoodItem("BcDeFgHiJkLmNoPqRsTuVwXyZa0123456789bcde", "Burger Bonanza", "burger_thumbnail.jpg", 12.49f, "Burger Shack", "Los Angeles", 3.8f, true, 7,
                       Arrays.asList("burger_image_1.jpg", "burger_image_2.jpg"), Arrays.asList("Fast Food", "Burger")),
               createDummyFoodItem("C123456789012345678901234567890123456789", "Sushi Delight", "sushi_thumbnail.jpg", 24.99f, "Tokyo Sushi", "Tokyo", 4.7f, true, 4,
                       Arrays.asList("sushi_image_1.jpg", "sushi_image_2.jpg"), Arrays.asList("Japanese", "Sushi")),
               createDummyFoodItem("D23456789012345678901234567890123456789D", "Healthy Salad", "salad_thumbnail.jpg", 9.99f, "Green Eats", "San Francisco", 4.2f, true, 10,
                       Arrays.asList("salad_image_1.jpg", "salad_image_2.jpg"), Arrays.asList("Vegetarian", "Salad")),
               createDummyFoodItem("E3456789012345678901234567890123456789EE", "Mexican Fiesta", "mexican_thumbnail.jpg", 18.99f, "Hola Amigos", "Mexico City", 4.6f, true, 6,
                       Arrays.asList("mexican_image_1.jpg", "mexican_image_2.jpg"), Arrays.asList("Mexican", "Tacos")),
               createDummyFoodItem("F45678901234567890123456789012345678901F", "Classic Pasta", "pasta_thumbnail.jpg", 14.99f, "Nonna's Kitchen", "Rome", 4.4f, true, 8,
                       Arrays.asList("pasta_image_1.jpg", "pasta_image_2.jpg"), Arrays.asList("Italian", "Pasta")),
               createDummyFoodItem("G56789012345678901234567890123456789012G", "Seafood Extravaganza", "seafood_thumbnail.jpg", 29.99f, "Ocean Delights", "Sydney", 4.9f, true, 3,
                       Arrays.asList("seafood_image_1.jpg", "seafood_image_2.jpg"), Arrays.asList("Seafood", "Exotic")),
               createDummyFoodItem("H67890123456789012345678901234567890123H", "Vegetarian Delight", "vegetarian_thumbnail.jpg", 11.99f, "Green Bites", "Berlin", 4.1f, true, 12,
                       Arrays.asList("vegetarian_image_1.jpg", "vegetarian_image_2.jpg"), Arrays.asList("Vegetarian", "Healthy")),
               createDummyFoodItem("I78901234567890123456789012345678901234I", "Mediterranean Feast", "mediterranean_thumbnail.jpg", 21.99f, "Olive Garden", "Athens", 4.8f, true, 5,
                       Arrays.asList("mediterranean_image_1.jpg", "mediterranean_image_2.jpg"), Arrays.asList("Mediterranean", "Gourmet")),
               createDummyFoodItem("J89012345678901234567890123456789012345J", "Indian Spice Express", "indian_thumbnail.jpg", 16.99f, "Spice Route", "Mumbai", 4.3f, true, 9,
                       Arrays.asList("indian_image_1.jpg", "indian_image_2.jpg"), Arrays.asList("Indian", "Spicy")),
               createDummyFoodItem("K90123456789012345678901234567890123456K", "Dessert Paradise", "dessert_thumbnail.jpg", 8.99f, "Sweet Haven", "Paris", 4.0f, true, 15,
                       Arrays.asList("dessert_image_1.jpg", "dessert_image_2.jpg"), Arrays.asList("Dessert", "Sweet")),
               createDummyFoodItem("L01234567890123456789012345678901234567L", "Vegan Delight", "vegan_thumbnail.jpg", 13.99f, "Green Eats", "San Francisco", 4.6f, true, 8,
                       Arrays.asList("vegan_image_1.jpg", "vegan_image_2.jpg"), Arrays.asList("Vegan", "Healthy")),
               createDummyFoodItem("M12345678901234567890123456789012345678M", "BBQ Extravaganza", "bbq_thumbnail.jpg", 22.99f, "Smokehouse Grill", "Texas", 4.7f, true, 6,
                       Arrays.asList("bbq_image_1.jpg", "bbq_image_2.jpg"), Arrays.asList("Barbecue", "Grill")),
               createDummyFoodItem("N23456789012345678901234567890123456789N", "Asian Fusion", "asian_thumbnail.jpg", 17.99f, "Wok & Roll", "Tokyo", 4.3f, true, 10,
                       Arrays.asList("asian_image_1.jpg", "asian_image_2.jpg"), Arrays.asList("Asian", "Fusion")),
               createDummyFoodItem("O34567890123456789012345678901234567890O", "Mouthwatering Pies", "pies_thumbnail.jpg", 10.99f, "Pie Paradise", "London", 4.2f, true, 12,
                       Arrays.asList("pies_image_1.jpg", "pies_image_2.jpg"), Arrays.asList("Dessert", "Pies")),
               createDummyFoodItem("P45678901234567890123456789012345678901P", "Exotic Smoothies", "smoothies_thumbnail.jpg", 8.49f, "Tropical Blends", "Hawaii", 4.5f, true, 15,
                       Arrays.asList("smoothies_image_1.jpg", "smoothies_image_2.jpg"), Arrays.asList("Beverages", "Smoothies")),
               createDummyFoodItem("Q56789012345678901234567890123456789012Q", "Gourmet Burgers", "gourmet_burgers_thumbnail.jpg", 19.99f, "Epic Burger Co.", "Chicago", 4.8f, true, 7,
                       Arrays.asList("gourmet_burgers_image_1.jpg", "gourmet_burgers_image_2.jpg"), Arrays.asList("Gourmet", "Burgers")),
               createDummyFoodItem("R67890123456789012345678901234567890123R", "Delightful Donuts", "donuts_thumbnail.jpg", 7.99f, "Sweet Treats", "New York", 4.4f, true, 20,
                       Arrays.asList("donuts_image_1.jpg", "donuts_image_2.jpg"), Arrays.asList("Dessert", "Donuts")),
               createDummyFoodItem("S78901234567890123456789012345678901234S", "Fiesta Tamales", "tamales_thumbnail.jpg", 15.99f, "Mexican Fiesta", "Mexico City", 4.6f, true, 9,
                       Arrays.asList("tamales_image_1.jpg", "tamales_image_2.jpg"), Arrays.asList("Mexican", "Tamales")),
               createDummyFoodItem("T89012345678901234567890123456789012345T", "Classic Ice Cream", "ice_cream_thumbnail.jpg", 6.99f, "Scoops & Swirls", "Paris", 4.0f, true, 25,
                       Arrays.asList("ice_cream_image_1.jpg", "ice_cream_image_2.jpg"), Arrays.asList("Dessert", "Ice Cream"))
               );
    }


    public static List<FoodItem> getDummyFoodItem() {
        return Arrays.asList(
                createDummyFood("a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0", "Delicious Pizza", "pizza_thumbnail.jpg", 15.99f, "Pizzeria Uno", "New York", 4.5f, true, 5,
                        Arrays.asList("pizza_image_1.jpg", "pizza_image_2.jpg"), Arrays.asList("Italian", "Pizza")),
                createDummyFood("BcDeFgHiJkLmNoPqRsTuVwXyZa0123456789bcde", "Burger Bonanza", "burger_thumbnail.jpg", 12.49f, "Burger Shack", "Los Angeles", 3.8f, true, 7,
                        Arrays.asList("burger_image_1.jpg", "burger_image_2.jpg"), Arrays.asList("Fast Food", "Burger")),
                createDummyFood("C123456789012345678901234567890123456789", "Sushi Delight", "sushi_thumbnail.jpg", 24.99f, "Tokyo Sushi", "Tokyo", 4.7f, true, 4,
                        Arrays.asList("sushi_image_1.jpg", "sushi_image_2.jpg"), Arrays.asList("Japanese", "Sushi")),
                createDummyFood("D23456789012345678901234567890123456789D", "Healthy Salad", "salad_thumbnail.jpg", 9.99f, "Green Eats", "San Francisco", 4.2f, true, 10,
                        Arrays.asList("salad_image_1.jpg", "salad_image_2.jpg"), Arrays.asList("Vegetarian", "Salad")),
                createDummyFood("E3456789012345678901234567890123456789EE", "Mexican Fiesta", "mexican_thumbnail.jpg", 18.99f, "Hola Amigos", "Mexico City", 4.6f, true, 6,
                        Arrays.asList("mexican_image_1.jpg", "mexican_image_2.jpg"), Arrays.asList("Mexican", "Tacos")),
                createDummyFood("F45678901234567890123456789012345678901F", "Classic Pasta", "pasta_thumbnail.jpg", 14.99f, "Nonna's Kitchen", "Rome", 4.4f, true, 8,
                        Arrays.asList("pasta_image_1.jpg", "pasta_image_2.jpg"), Arrays.asList("Italian", "Pasta")),
                createDummyFood("G56789012345678901234567890123456789012G", "Seafood Extravaganza", "seafood_thumbnail.jpg", 29.99f, "Ocean Delights", "Sydney", 4.9f, true, 3,
                        Arrays.asList("seafood_image_1.jpg", "seafood_image_2.jpg"), Arrays.asList("Seafood", "Exotic")),
                createDummyFood("H67890123456789012345678901234567890123H", "Vegetarian Delight", "vegetarian_thumbnail.jpg", 11.99f, "Green Bites", "Berlin", 4.1f, true, 12,
                        Arrays.asList("vegetarian_image_1.jpg", "vegetarian_image_2.jpg"), Arrays.asList("Vegetarian", "Healthy")),
                createDummyFood("I78901234567890123456789012345678901234I", "Mediterranean Feast", "mediterranean_thumbnail.jpg", 21.99f, "Olive Garden", "Athens", 4.8f, true, 5,
                        Arrays.asList("mediterranean_image_1.jpg", "mediterranean_image_2.jpg"), Arrays.asList("Mediterranean", "Gourmet")),
                createDummyFood("J89012345678901234567890123456789012345J", "Indian Spice Express", "indian_thumbnail.jpg", 16.99f, "Spice Route", "Mumbai", 4.3f, true, 9,
                        Arrays.asList("indian_image_1.jpg", "indian_image_2.jpg"), Arrays.asList("Indian", "Spicy")),
                createDummyFood("K90123456789012345678901234567890123456K", "Dessert Paradise", "dessert_thumbnail.jpg", 8.99f, "Sweet Haven", "Paris", 4.0f, true, 15,
                        Arrays.asList("dessert_image_1.jpg", "dessert_image_2.jpg"), Arrays.asList("Dessert", "Sweet")),
                createDummyFood("L01234567890123456789012345678901234567L", "Vegan Delight", "vegan_thumbnail.jpg", 13.99f, "Green Eats", "San Francisco", 4.6f, true, 8,
                        Arrays.asList("vegan_image_1.jpg", "vegan_image_2.jpg"), Arrays.asList("Vegan", "Healthy")),
                createDummyFood("M12345678901234567890123456789012345678M", "BBQ Extravaganza", "bbq_thumbnail.jpg", 22.99f, "Smokehouse Grill", "Texas", 4.7f, true, 6,
                        Arrays.asList("bbq_image_1.jpg", "bbq_image_2.jpg"), Arrays.asList("Barbecue", "Grill")),
                createDummyFood("N23456789012345678901234567890123456789N", "Asian Fusion", "asian_thumbnail.jpg", 17.99f, "Wok & Roll", "Tokyo", 4.3f, true, 10,
                        Arrays.asList("asian_image_1.jpg", "asian_image_2.jpg"), Arrays.asList("Asian", "Fusion")),
                createDummyFood("O34567890123456789012345678901234567890O", "Mouthwatering Pies", "pies_thumbnail.jpg", 10.99f, "Pie Paradise", "London", 4.2f, true, 12,
                        Arrays.asList("pies_image_1.jpg", "pies_image_2.jpg"), Arrays.asList("Dessert", "Pies")),
                createDummyFood("P45678901234567890123456789012345678901P", "Exotic Smoothies", "smoothies_thumbnail.jpg", 8.49f, "Tropical Blends", "Hawaii", 4.5f, true, 15,
                        Arrays.asList("smoothies_image_1.jpg", "smoothies_image_2.jpg"), Arrays.asList("Beverages", "Smoothies")),
                createDummyFood("Q56789012345678901234567890123456789012Q", "Gourmet Burgers", "gourmet_burgers_thumbnail.jpg", 19.99f, "Epic Burger Co.", "Chicago", 4.8f, true, 7,
                        Arrays.asList("gourmet_burgers_image_1.jpg", "gourmet_burgers_image_2.jpg"), Arrays.asList("Gourmet", "Burgers")),
                createDummyFood("R67890123456789012345678901234567890123R", "Delightful Donuts", "donuts_thumbnail.jpg", 7.99f, "Sweet Treats", "New York", 4.4f, true, 20,
                        Arrays.asList("donuts_image_1.jpg", "donuts_image_2.jpg"), Arrays.asList("Dessert", "Donuts")),
                createDummyFood("S78901234567890123456789012345678901234S", "Fiesta Tamales", "tamales_thumbnail.jpg", 15.99f, "Mexican Fiesta", "Mexico City", 4.6f, true, 9,
                        Arrays.asList("tamales_image_1.jpg", "tamales_image_2.jpg"), Arrays.asList("Mexican", "Tamales")),
                createDummyFood("T89012345678901234567890123456789012345T", "Classic Ice Cream", "ice_cream_thumbnail.jpg", 6.99f, "Scoops & Swirls", "Paris", 4.0f, true, 25,
                        Arrays.asList("ice_cream_image_1.jpg", "ice_cream_image_2.jpg"), Arrays.asList("Dessert", "Ice Cream"))
        );
    }

    private static FoodItemFull createDummyFoodItem(String itemId, String itemName, String thumbnailPicture, Float price,
                                                    String vendorName, String vendorLocation, Float starRating, boolean isAvailable, int maxQuantity,
                                                    List<String> imageUrls, List<String> tags) {
        FoodItemFull foodItem = new FoodItemFull();
        foodItem.setItemId(itemId);
        foodItem.setItemName(itemName);
        foodItem.setThumbnailPicture(thumbnailPicture);
        foodItem.setPrice(price);
        foodItem.setVendorName(vendorName);
        foodItem.setVendorLocation(vendorLocation);
        foodItem.setStarRating(starRating);
        foodItem.setIsAvailable(isAvailable);
        foodItem.setMaxQuantity(maxQuantity);
        foodItem.setImageUrls(imageUrls);
        foodItem.setTags(tags);
        return foodItem;
    }


    private static FoodItem createDummyFood(String itemId, String itemName, String thumbnailPicture, Float price,
                                                String vendorName, String vendorLocation, Float starRating, boolean isAvailable, int maxQuantity,
                                                List<String> imageUrls, List<String> tags) {
        FoodItem foodItem = new FoodItem();
        foodItem.setItemId(itemId);
        foodItem.setThumbnail(thumbnailPicture);
        foodItem.setName(itemName);
        foodItem.setPrice(price);
        foodItem.setVendor(vendorName);
        foodItem.setRating(starRating);
        foodItem.setTags(tags);
        return foodItem;
    }

    public static List<OrderModel> dummyOrderList = getDummyOrderList();

    public static List<OrderModel> getDummyOrderList() {
        return Arrays.asList(
                new OrderModel(getRandomOrderItems(), "2024-02-01", "18:30", "Address 1", 5),
                new OrderModel(getRandomOrderItems(), "2024-02-02", "19:00", "Address 2", 6),
                new OrderModel(getRandomOrderItems(), "2024-02-03", "19:30", "Address 3", 7),
                new OrderModel(getRandomOrderItems(), "2024-02-04", "20:00", "Address 4", 8),
                new OrderModel(getRandomOrderItems(), "2024-02-05", "20:30", "Address 5", 9),
                new OrderModel(getRandomOrderItems(), "2024-02-06", "21:00", "Address 6", 10),
                new OrderModel(getRandomOrderItems(), "2024-02-07", "21:30", "Address 7", 11),
                new OrderModel(getRandomOrderItems(), "2024-02-08", "22:00", "Address 8", 12),
                new OrderModel(getRandomOrderItems(), "2024-02-09", "22:30", "Address 9", 13),
                new OrderModel(getRandomOrderItems(), "2024-02-10", "23:00", "Address 10", 14),
                new OrderModel(getRandomOrderItems(), "2024-02-11", "23:30", "Address 11", 15),
                new OrderModel(getRandomOrderItems(), "2024-02-12", "00:00", "Address 12", 16),
                new OrderModel(getRandomOrderItems(), "2024-02-13", "00:30", "Address 13", 17),
                new OrderModel(getRandomOrderItems(), "2024-02-14", "01:00", "Address 14", 18)
        );
    }

    private static List<OrderItemAdvanced> getRandomOrderItems() {
        List<FoodItemFull> dummyFoodList = getDummyFoodItemList();
        Random random = new Random();

        int numberOfItems = random.nextInt(dummyFoodList.size()) + 1;
        List<OrderItemAdvanced> orderItems = new ArrayList<>();

        for (int i = 0; i < numberOfItems; i++) {
            FoodItemFull randomFoodItem = dummyFoodList.get(i);
            int quantity = random.nextInt(5) + 1; // Random quantity between 1 and 5
            float totalPrice = randomFoodItem.getPrice()*quantity;

            OrderItemAdvanced orderItem = new OrderItemAdvanced();
            orderItem.setItemId(randomFoodItem.getItemId());
            orderItem.setItemName(randomFoodItem.getItemName());
            orderItem.setQuantity(quantity);
            orderItem.setPrice(totalPrice);
            orderItem.setMaxQuantity(randomFoodItem.getMaxQuantity());

            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
