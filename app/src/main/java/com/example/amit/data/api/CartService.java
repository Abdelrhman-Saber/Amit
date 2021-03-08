package com.example.amit.data.api;

import com.example.amit.data.model.cart.CartResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CartService {
    @GET("api/user/products/")
    Call<CartResponse>getCarts(

            @Header("Authorization") String token
    );
}
