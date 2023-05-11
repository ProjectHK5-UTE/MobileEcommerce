package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.HomeViewModelClass;
import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.BrandsModel;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.dto.ResponseObject;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductAPI {
    @GET("api/product/lasted-product")
    Call<List<HomeViewModelClass>> LastedProduct();

    @GET("api/product/popular-product")
    Call<List<HomeViewModelClass>> PopularProduct();

    @GET("api/product/{brandId}")
    Call<List<ProductGridModel>> getProductByBrand(@Path("brandId") Integer brandId);
    @GET("api/product")
    Call<List<ProductGridModel>> getAllProduct();

    @GET("api/product/filter")
    Call<List<ProductGridModel>> filterProduct(@Query("start-price") double startPrice,
                                               @Query("end-price") double endPrice,
                                               @Query("start-battery") int startBattery,
                                               @Query("end-battery") int endBattery,
                                               @Query("start-screen") double startScreen,
                                               @Query("end-screen") double endScreen);

    @GET("api/product/search")
    Call<ResponseObject> searchProduct(@Query("keyword") String keyword);

    @GET("api/product/get")
    Call<ProductGridModel> getProduct(@Query("id") Integer id);
}
