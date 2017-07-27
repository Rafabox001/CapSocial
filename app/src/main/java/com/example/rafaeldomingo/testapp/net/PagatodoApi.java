package com.example.rafaeldomingo.testapp.net;


import com.example.rafaeldomingo.testapp.net.request.LoginRequest;
import com.example.rafaeldomingo.testapp.net.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PagatodoApi {

    @Headers({
            "SO:Android",
            "Version:2.5.2",
            "Content-Type:application/json",
            "X-Requested-With:XMLHttpRequest"
    })
    @POST("login")
    Call<LoginResponse> login (@Body LoginRequest loginRequest);
}
