package com.example.rafaeldomingo.testapp;

import android.app.Application;

import com.facebook.FacebookSdk;


public class TestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

    }
}
