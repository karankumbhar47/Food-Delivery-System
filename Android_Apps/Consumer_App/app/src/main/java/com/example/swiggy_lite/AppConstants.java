package com.example.swiggy_lite;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    public static final String PREF_ORDERS_IDS      = "Order-Status"    ;
    public static final String STATUS_ONGOING       = "1"               ;
    public static final String STATUS_COMPLETED     = "0"               ;
    public static final String STATUS_CANCELLED     = "-1"              ;
    public static final String STATUS_OTP = "2"               ;

    public static final String PREF_ORDER_HISTORY   = "Pref-Order-History";

    public static final String PREF_IMAGE_STORAGE   = "Image-Storage"   ;

    public static final String KEY_ITEM_ID          = "Item-Id"         ;
    public static final String API_DEFAULT_URL      = "http://192.168.151.69:8080";
    //public static final String API_URL = "http://"+getLocalIpAddress() + ":8080";

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


    public static String getUrl() {
        String[] command = {"/bin/bash", "-c", "./test.sh"};

        try {
            // Create ProcessBuilder instance
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Log.d("myTag", "process build "+processBuilder.toString());
            // Start the process
            Process process = processBuilder.start();
            Log.d("myTag", "process start "+process.toString());

            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            Log.d("myTag", "line "+line);
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
                Log.d("myTag", "line "+output);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                Log.d("myTag", "exit code "+line+" "+output);
                // Script executed successfully
                System.out.println("Script output:");
                System.out.println(output.toString());
                return line;
            } else {
                Log.d("myTag", "exit code not zero "+line+" "+output);
                // Script execution failed
                System.err.println("Script execution failed with exit code: " + exitCode);
                return line;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.d("myTag", "exception "+e);
            return "Not found";
        }
/*
        try {
            String scriptPath = "./test.sh";
            Process process = Runtime.getRuntime().exec(scriptPath);
            process.waitFor();

            int exitValue = process.exitValue();
            if (exitValue == 0) {
                Log.d("myTag", "success ");
                System.out.println("Script executed successfully");
            } else {
                Log.d("myTag", " fail ");
                System.out.println("Script failed with exit value: " + exitValue);
            }
            return "success ";

        } catch (IOException | InterruptedException e) {
            Log.d("myTag", "exception "+e);
            return "fail ";
        }*/


    }

    public static final String url = getUrl();

}
