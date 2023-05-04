package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.BrandsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BrandAPI {
    @GET("api/brand")
    //@Headers("Authorization: Bearer eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..281RfEneJGjUyR5rws1rcA.NwmQ4VDtSkHY-mCBFNKSosdlUTAh448IzxL0jm_4k5t3M16ZbHNH2TUuay2hb2ZksNBcyEN8uwlvfGREU9iDWg.GXyg7Ha7t9TANu7wmM033g")
    Call<List<BrandsModel>> getBrands();
}
