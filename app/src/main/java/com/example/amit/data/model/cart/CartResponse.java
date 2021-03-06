package com.example.amit.data.model.cart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CartResponse{

	@SerializedName("products")
	private List<ProductsItem> products;

	public List<ProductsItem> getProducts(){
		return products;
	}
}