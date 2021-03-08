package com.example.amit.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.amit.R;
import com.example.amit.data.adapter.cart.CartAdapter;
import com.example.amit.data.adapter.category.CategoryAdapter;
import com.example.amit.data.api.ApiManager;
import com.example.amit.data.model.cart.CartResponse;
import com.example.amit.data.model.product.Product;
import com.example.amit.helper.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {

    CartAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView cartRecycler;
    TextView amount;
    ImageButton addBtn,removeBtn;
    TokenManager tokenManager;

    public CartFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler(view);
        initApiManager();

    }

    private void initRecycler(View v){

        adapter=new CartAdapter();
        cartRecycler=v.findViewById(R.id.cartContainer);
        layoutManager=new LinearLayoutManager(getContext());
        cartRecycler.setAdapter(adapter);
        cartRecycler.setLayoutManager(layoutManager);
    }
    private void initApiManager(){
        tokenManager= new TokenManager(getActivity());
        String token= tokenManager.getToken();
        Log.d("dddddddddddddddd", "initApiManager: "+token);
        ApiManager.cartService().getCarts("Bearer "+token).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()){

                    Log.d("ddddddddddddddddd", "onResponse: "+response
                            .message());
                    adapter.setProducts(response.body().getProducts());
                }
                else Log.d("dddddddddddddddd", "onResponse: "+response.message());
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.d("dddddddddddddddddddd", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}