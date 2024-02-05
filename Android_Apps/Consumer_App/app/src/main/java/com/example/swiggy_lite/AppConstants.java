package com.example.swiggy_lite;

import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class AppConstants {
    public static final String PREF_LOGIN           = "Login"           ;
    public static final String KEY_LOGIN_FLAG       = "Login-Flag"      ;
    public static final String KEY_SESSION_ID       = "Session-Id"      ;
    public static final String KEY_USER_NAME        = "User_Name"       ;
    public static final String KEY_PASSWORD         = "Password"        ;

    public static final String PREF_CART_INFO       = "Cart-Info"       ;
    public static final String KEY_CURRENT_CART     = "Current-Cart"    ;
    public static final String KEY_CURRENT_ORDER    = "Current-Order"   ;
    public static final String KEY_IS_DATA_CHANGED  = "Is_Data_Changed" ;

    public static final String KEY_ITEM_ID          = "Item-Id"         ;
    public static final String API_DEFAULT_URL      = "http://192.168.151.69:8080";
    //public static final String API_URL = "http://"+getLocalIpAddress() + ":8080";

    private static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            Log.d("myTag", "interface count "+interfaces.toString());
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                Log.d("myTag", "name "+networkInterface.getName());
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    Log.d("myTag", "address "+address.getHostAddress());
                    if (!address.isLoopbackAddress() && !address.isLinkLocalAddress() && address.isSiteLocalAddress()) {
                        Log.d("myTag", "lookup "+address.isLoopbackAddress());
                        Log.d("myTag", "link local "+address.isLinkLocalAddress());
                        Log.d("myTag", "site local "+address.isSiteLocalAddress());
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "192.168.151.69";
    }



    public static final ArrayList<String> LIST_PREF = new ArrayList<>() ;
    static {
        LIST_PREF.add(PREF_LOGIN);
        LIST_PREF.add(PREF_CART_INFO);
    }

    public static final ArrayList<String> LIST_CATEGORY = new ArrayList<>();
    static {
        LIST_CATEGORY.add("biryani");
        LIST_CATEGORY.add("burger");
        LIST_CATEGORY.add("cakes");
        LIST_CATEGORY.add("chinese");
        LIST_CATEGORY.add("chole");
        LIST_CATEGORY.add("coffee");
        LIST_CATEGORY.add("dosa");
        LIST_CATEGORY.add("gulab");
        LIST_CATEGORY.add("ice");
        LIST_CATEGORY.add("idli");
        LIST_CATEGORY.add("kebab");
        LIST_CATEGORY.add("khichdi");
        LIST_CATEGORY.add("noodles");
        LIST_CATEGORY.add("north");
        LIST_CATEGORY.add("biryani");
        LIST_CATEGORY.add("burger");
        LIST_CATEGORY.add("cakes");
        LIST_CATEGORY.add("chinese");
        LIST_CATEGORY.add("chole");
        LIST_CATEGORY.add("coffee");
        LIST_CATEGORY.add("dosa");
        LIST_CATEGORY.add("gulab");
        LIST_CATEGORY.add("ice");
        LIST_CATEGORY.add("idli");
        LIST_CATEGORY.add("kebab");
    }

    public static final int SECOND_ACTIVITY_REQUEST_CODE = 669;

}
