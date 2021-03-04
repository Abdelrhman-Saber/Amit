package com.example.amit.data.model.user;

import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("token")
	private String token;

	public String getToken(){
		return token;
	}
}