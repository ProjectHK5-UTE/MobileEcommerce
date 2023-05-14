package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.dto.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignUpAPI {
    @POST("api/user/SignUp")
    Call<ResponseDTO> SignUp(@Body UserModel userModel);

    @FormUrlEncoded
    @POST("api/user/SignUp/Verify")
    Call<ResponseDTO> Verify(@Field("otp") Integer otp, @Field("email") String email);

    @GET("/api/user/SignUp/check-signup")
    Call<ResponseDTO> checkSignUp(@Query("username") String username, @Query("email") String email);
}
