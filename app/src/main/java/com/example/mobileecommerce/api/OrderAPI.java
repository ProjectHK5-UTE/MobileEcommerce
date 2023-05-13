package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.dto.OrderDTO;
import com.example.mobileecommerce.model.dto.OrderResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderAPI {
    @POST("api/order")
    Call<OrderResponseDTO> order(@Body OrderDTO orderDTO);
}
