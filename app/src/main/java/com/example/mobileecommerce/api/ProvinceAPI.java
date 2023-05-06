package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.Province;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProvinceAPI {
    @GET("api/")
    Call<List<Province>> getProvince();

    @GET("api/p/{id}?depth=2")
    Call<JsonObject> getDistricts(@Path("id") Integer id);

    @GET("api/d/{id}?depth=2")
    Call<JsonObject> getSubDistricts(@Path("id") Integer id);
}
