package com.example.mobileecommerce.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitForLogin {
    private static Retrofit retrofit = null;
    static Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    private static final String BASE_URL = "https://784e-2001-ee0-4fc7-dce0-61a2-fcaf-6829-fbd5.ngrok-free.app";


    public static Retrofit getRetrofitForLogin() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
