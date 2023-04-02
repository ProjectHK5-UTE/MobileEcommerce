package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.dto.ResponseDTO;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SignUpAPI {
    @POST("api/user/SignUp")
    Call<ResponseDTO> SignUp(@Body UserModel userModel);

    @POST("api/user/SignUp/Verify")
    Call<ResponseDTO> Verify(@Part("otp") RequestBody otp, @Part("email") RequestBody email);
}
