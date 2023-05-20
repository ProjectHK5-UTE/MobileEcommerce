package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.dto.ResponseOrderDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OptionAPI {
    @GET("api/getProductId/{optionId}")
    Call<Integer> findProductIdByOptionId(@Path("optionId") Integer optionId);
}
