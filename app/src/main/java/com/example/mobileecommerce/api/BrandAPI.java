package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.HomeViewModelClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BrandAPI {
    @GET("api/brand")
    Call<HomeViewModelClass> getBrand();
}
