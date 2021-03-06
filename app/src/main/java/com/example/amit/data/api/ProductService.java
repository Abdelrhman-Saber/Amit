package com.example.amit.data.api;

import com.example.amit.data.adapter.product.AddCartResponse;
import com.example.amit.data.model.product.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {

    @GET ("api/products")
    Call<ProductResponse>getProducts();

    @PUT ("api/user/products/{id}")
    Call<AddCartResponse>addProductToCart(
            @Path("id")int id ,
            @Header("Authorization") String token,
            @Query("amount") int amount
    );


}
