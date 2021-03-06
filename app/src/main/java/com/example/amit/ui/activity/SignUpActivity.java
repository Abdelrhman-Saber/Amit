package com.example.amit.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amit.R;
import com.example.amit.data.api.ApiManager;
import com.example.amit.data.model.user.UserResponse;
import com.example.amit.helper.TokenManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText name_editText,email_editText,password_editText;
    Button btn;
    LinearLayout layout;
    ProgressBar progressBar;
    TextView signUp_have_already_acc;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();

            }
        });
        signUp_have_already_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initView(){
        name_editText= findViewById(R.id.signUp_name);
        email_editText= findViewById(R.id.signUp_email);
        password_editText= findViewById(R.id.signUp_password);
        btn= findViewById(R.id.signUp_btn);
        layout=findViewById(R.id.sign_Up_Layout);
        progressBar=findViewById(R.id.prog_bar);
        signUp_have_already_acc=findViewById(R.id.signUp_have_already_acc);
        tokenManager=new TokenManager(this);

    }
    private void signUp(){
        String name=name_editText.getText().toString().trim();
        String email=email_editText.getText().toString().trim();
        String password=password_editText.getText().toString().trim();

        if (name.isEmpty()||email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Check your data !!", Toast.LENGTH_LONG).show();
        }
        else {
            Map<String, String>user=new HashMap();
            user.put("name",name);
            user.put("email",email);
            user.put("password",password);

            progressBar.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);

            ApiManager.getUserService().userServiceRegister(user).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()){
                        tokenManager.saveToken(response.body().getToken());
                        String s =tokenManager.getToken();
                        Log.d("dddddddddddddddddd", "onResponse: "+s);
                        Log.d("ddddddddddddddd", "onResponse: "+response.body().getToken());
                        Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                    else Log.d("dddddddddddd", "onResponse: "+response.message());


                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    Log.d("ddddddddddddddd", "onFailure: "+t.getLocalizedMessage());
                    Toast.makeText(SignUpActivity.this, "Check your data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}