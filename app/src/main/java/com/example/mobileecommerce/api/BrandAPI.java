package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.BrandsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BrandAPI {
    @GET("api/brand")
    Call<List<BrandsModel>> getBrands();
}
