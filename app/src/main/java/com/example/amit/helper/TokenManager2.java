package com.example.amit.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager2 {
    private SharedPreferences preferences;
    public TokenManager2 (Context context){
        preferences=context.getSharedPreferences
                ("token",Context.MODE_PRIVATE);
    }
    public void saveToken(String s){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("token",s);
        editor.apply();
    }
    public String getToken(){
        return preferences.getString("token",null);
    }
}
