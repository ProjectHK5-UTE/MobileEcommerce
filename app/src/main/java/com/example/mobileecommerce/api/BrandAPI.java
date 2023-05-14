package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.BrandsModel;
import com.example.mobileecommerce.model.dto.ResponseObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

    public interface BrandAPI {
    @GET("api/brand")
    Call<List<BrandsModel>> getBrands();

    @Multipart
    @POST("api/brand")
    Call<ResponseObject> insert(@Part("name") RequestBody name, @Part MultipartBody.Part images);

    @Multipart
    @PUT("api/brand")
        Call<ResponseObject> update(@Part("nameOld") RequestBody nameOld, @Part("nameNew") RequestBody nameNew,
                                    @Part MultipartBody.Part images);

}
