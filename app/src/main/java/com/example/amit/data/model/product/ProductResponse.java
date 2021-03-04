package com.example.amit.data.model.product;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductResponse{

	@SerializedName("products")
	private List<ProductsItem> products;

	public List<ProductsItem> getProducts(){
		return products;
	}
}