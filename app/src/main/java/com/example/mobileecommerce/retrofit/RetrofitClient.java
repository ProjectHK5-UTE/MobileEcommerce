package com.example.mobileecommerce.retrofit;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mobileecommerce.service.JwtService;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    static Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    private static final String BASE_URL = "https://7f8f-2001-ee0-4f84-f010-7d00-e330-5ddc-c2fe.ngrok-free.app";
    static SharedPreferences pres;

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    String token = getJWT();
                    if (token != null && !token.isEmpty()) {
                        request = request.newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                    }

                    return chain.proceed(request);
                }
            })
            .build();

    private static String getJWT() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(pres);
        String token = sharedPreferencesManager.getJWT();
        return token;
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
//           HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofit60TimeOut() {
        if(retrofit == null) {
            OkHttpClient OClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
