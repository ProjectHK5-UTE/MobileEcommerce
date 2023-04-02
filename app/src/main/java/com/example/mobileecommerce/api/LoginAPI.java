package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.dto.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("api/user/login")
    Call<ResponseDTO> Login(@Body UserModel userModel);
}
