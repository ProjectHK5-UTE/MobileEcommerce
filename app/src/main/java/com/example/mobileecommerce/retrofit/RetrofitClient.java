package com.example.mobileecommerce.retrofit;

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
    private static Retrofit retrofit = null;;
    static Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    private static final String BASE_URL = "http://192.168.11.104:8088/";

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request request = chain.request();

                    // Add JWT token to Authorization header
                    String token = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..Z4aLONtih8fDNN2kDvrgJw.hJabtNta8icYZgRpJu4HIP_nNFo0PgwDiJtlPkoxdtkmw70_W3WHB1KTneKv1kdc6VHSMuitjML7b-SVaHnaAQ.yOOz1jcmmTi5d8XSCPeBbg";
                    if (token != null && !token.isEmpty()) {
                        request = request.newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                    }

                    return chain.proceed(request);
                }
            })
            .build();


    public static Retrofit getRetrofit() {
        if(retrofit == null) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
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
