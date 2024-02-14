package com.example.delivery_app;

public interface RegistrationCallback {
    void onRegistrationSuccess(String sessionId);
    void onRegistrationError(int errorCode, String errorMessage);
}
