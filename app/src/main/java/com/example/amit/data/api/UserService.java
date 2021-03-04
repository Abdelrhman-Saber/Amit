package com.example.amit.data.api;

import com.example.amit.data.model.user.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST ("api/register")
    Call<UserResponse>userServiceRegister(@Body Map<String,String>user);
    @POST("api/login")
    Call<UserResponse>userServiceLogin(@Body Map<String,String>user);
}
