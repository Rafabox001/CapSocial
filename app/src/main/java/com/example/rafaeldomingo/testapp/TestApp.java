package com.example.rafaeldomingo.testapp;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by JAGARCIA on 27/07/2017.
 */

public class TestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

    }
}
