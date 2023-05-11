package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.BrandsModel;
import com.example.mobileecommerce.model.ReviewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReviewAPI {
    @GET("api/review/{productId}/{username}")
        //@Headers("Authorization: Bearer eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..281RfEneJGjUyR5rws1rcA.NwmQ4VDtSkHY-mCBFNKSosdlUTAh448IzxL0jm_4k5t3M16ZbHNH2TUuay2hb2ZksNBcyEN8uwlvfGREU9iDWg.GXyg7Ha7t9TANu7wmM033g")
    Call<List<ReviewModel>> getReviewsByProductId(@Path("productId") int productId, @Path("username") String username);
}
