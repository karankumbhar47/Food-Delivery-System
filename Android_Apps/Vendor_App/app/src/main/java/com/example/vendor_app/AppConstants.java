package com.example.vendor_app;

import java.util.ArrayList;

public class AppConstants {
    public static final String PREF_LOGIN       = "Login";
    public static final String KEY_LOGIN_FLAG   = "Login-Flag";
    public static final String KEY_SESSION_ID   = "Session-Id" ;

    public static final String PREF_CART_INFO   = "Cart-Info" ;
    public static final String KEY_CURRENT_CART = "Current-Cart" ;

    public static final String KEY_ITEM_ID      = "Item-Id" ;
//    public static final String API_URL          = "http://13.233.99.87:8080";
    public static final String API_URL          = "http://localhost:8080";

    public static final ArrayList<String> LIST_PREF = new ArrayList<>();
    static {
        LIST_PREF.add(PREF_LOGIN);
        LIST_PREF.add(PREF_CART_INFO);
    }
}
