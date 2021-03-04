package com.example.amit.data.adapter.product;

import com.example.amit.data.model.product.ProductsItem;

public interface ProductClickListner {

    void showProductDetails(ProductsItem product);
    void addProductToCart(ProductsItem product);
}
