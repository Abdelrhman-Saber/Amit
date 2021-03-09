package com.example.amit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.amit.R;
import com.example.amit.ui.activity.LoginActivity;
import com.example.amit.ui.activity.MainActivity;
import com.example.amit.ui.activity.SignUpActivity;


public class UserFragment extends Fragment {

    TextView name;
    Button btn;

    public UserFragment() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user, container, false);
    }


    private void initView(View v){

        btn=v.findViewById(R.id.user_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),
                        LoginActivity.class);
                startActivity(intent);
                getActivity().finishAffinity();


            }
        });


    }
}