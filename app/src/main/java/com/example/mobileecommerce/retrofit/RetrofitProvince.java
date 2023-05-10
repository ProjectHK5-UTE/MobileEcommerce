package com.example.mobileecommerce.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvince {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://4a74-2001-ee0-4fc7-dce0-a89b-3429-6bdb-cfc4.ngrok-free.app";

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
