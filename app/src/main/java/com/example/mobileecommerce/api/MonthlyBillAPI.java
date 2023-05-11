package com.example.mobileecommerce.api;

import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MonthlyBillAPI {
    @GET("api/admin/dashboard/get-bill-in-month")
    Call<TreeMap<String,Float>> getBillInMonth();
}
