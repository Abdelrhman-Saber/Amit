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
import com.example.amit.ui.fragment.HomeFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText email_editText,password_editText;
    Button btn;
    LinearLayout layout;
    ProgressBar progressBar;
    TextView login_create_new_acc,skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        login_create_new_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }



    private void initView(){

        email_editText=findViewById(R.id.login_email);
        password_editText=findViewById(R.id.login_password);
        btn=findViewById(R.id.login_btn);
        layout=findViewById(R.id.login_layout);
        progressBar=findViewById(R.id.prog_bar);
        login_create_new_acc=findViewById(R.id.login_create_new_acc);
        skip=findViewById(R.id.login_skip);
    }
    private void login(){

        String email=email_editText.getText().toString().trim();
        String password=password_editText.getText().toString().trim();


        if (email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Check your data !!", Toast.LENGTH_SHORT).show();
        }
        else {
            Map<String,String>user=new HashMap();

            user.put("email",email);
            user.put("password",password);

            progressBar.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
            ApiManager.getUserService().userServiceLogin(user).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    if (response.isSuccessful())
                        Log.d("fffffffffffff", "onResponse: "+response.body().getToken());

                    else Log.d("dddddddddddd", "onResponse: "+response.code());

                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    Log.d("dddddddddddd", "onFailure: "+t.getLocalizedMessage());

                }
            });
        }
    }
}