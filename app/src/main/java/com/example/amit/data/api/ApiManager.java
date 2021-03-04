package com.example.amit.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
     private static Retrofit retrofit;
     private static final String BASE_URL = "http://retail.amit-learning.com/";
     private static Retrofit getOpject(){

         if (retrofit==null){
             retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                     addConverterFactory(GsonConverterFactory.create()).build();
         }
         return retrofit;
    }
    public static UserService getUserService(){
         return getOpject().create(UserService.class);
    }
    public static ProductService productService(){

         return getOpject().create(ProductService.class);
    }
    public static CategoryService categoryService(){
         return getOpject().create(CategoryService.class);
    }
}
