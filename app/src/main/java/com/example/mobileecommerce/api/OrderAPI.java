package com.example.mobileecommerce.api;

import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.dto.RequestOrderDTO;
import com.example.mobileecommerce.model.dto.OrderResponseDTO;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.model.dto.ResponseOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderAPI {
    @POST("api/order")
    Call<OrderResponseDTO> order(@Body RequestOrderDTO orderDTO);
    @GET("api/order/{username}")
    Call<List<ResponseOrderDTO>> getOrderByUsername(@Path("username") String username);
    @GET("api/order/getOrder/{status}")
    Call<List<ResponseOrderDTO>> getOrderByStatus(@Path("status") Status status);
    @GET("api/order/getOrderByID/{orderId}")
    Call<ResponseOrderDTO> getOrderByID(@Path("orderId") Integer orderId);
    @GET("api/order/update-status/{orderId}/{status}")
    Call<OrderResponseDTO> updateStatus(@Path("orderId") Integer orderId,
                                        @Path("status") Status status);
}
