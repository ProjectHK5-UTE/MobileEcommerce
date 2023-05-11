package com.example.mobileecommerce.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatisticAPI {
    @GET("api/admin/dashboard/get-statistics")
    Call<HashMap<String, Float>> getStatistics();
}
