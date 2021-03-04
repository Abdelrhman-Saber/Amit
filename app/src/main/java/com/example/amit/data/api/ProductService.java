package com.example.amit.data.api;

import com.example.amit.data.model.product.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET ("api/products")
    Call<ProductResponse>getProducts();
}
