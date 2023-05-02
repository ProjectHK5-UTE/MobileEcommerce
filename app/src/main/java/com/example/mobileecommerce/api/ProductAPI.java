package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.BrandsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {
    @GET("api/product")
    Call<List<BrandsModel>> getProductByBrand();
}
