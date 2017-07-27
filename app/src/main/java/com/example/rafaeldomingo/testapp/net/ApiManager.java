package com.example.rafaeldomingo.testapp.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static final String BASE_URL = "https://agentemovil.pagatodo.com/AgenteMovil.svc/agMov/";

    private static PagatodoApi journeyApi;

    public static PagatodoApi getApiInstance() {
        if (journeyApi == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            journeyApi = retrofit.create(PagatodoApi.class);
        }

        return journeyApi;
    }
}
