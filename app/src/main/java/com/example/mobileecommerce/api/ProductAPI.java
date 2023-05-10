package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.HomeViewModelClass;
import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.BrandsModel;
import com.example.mobileecommerce.model.ProductGridModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductAPI {
    @GET("api/product/lasted-product")
    Call<List<HomeViewModelClass>> LastedProduct();

    @GET("api/product/popular-product")
    Call<List<HomeViewModelClass>> PopularProduct();

    @GET("api/product/{brandId}")
    Call<List<ProductGridModel>> getProductByBrand(@Path("brandId") Integer brandId);
}
