package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.ReviewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReviewAPI {
    @GET("api/review/{productId}/{username}")
    Call<List<ReviewModel>> getReviewsByProductId(@Path("productId") int productId, @Path("username") String username);
}
