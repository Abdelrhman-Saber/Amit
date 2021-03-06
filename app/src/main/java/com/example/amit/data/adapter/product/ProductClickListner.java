package com.example.amit.data.adapter.product;

import com.example.amit.data.model.product.Product;

public interface ProductClickListner {

    void showProductDetails(Product product);
    void addProductToCart(Product product);
}
