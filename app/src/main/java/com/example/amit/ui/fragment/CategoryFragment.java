package com.example.amit.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amit.R;
import com.example.amit.data.adapter.category.CategoryAdapter;
import com.example.amit.data.api.ApiManager;
import com.example.amit.data.model.category.CategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {

    CategoryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView productRecycler;

    public CategoryFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler(view);
        initApiManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    private void initRecycler(View v){

        adapter=new CategoryAdapter(getContext());
        productRecycler=v.findViewById(R.id.homeRecycler);
        layoutManager=new GridLayoutManager(getContext(),2);
        productRecycler.setAdapter(adapter);
        productRecycler.setLayoutManager(layoutManager);

    }

    private void initApiManager(){
        ApiManager.categoryService().getCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    Log.d("dddddddddd", "onResponse: "+response.body().getCategories().get(0).getName());
                    adapter.setCategoriesItems(response.body().getCategories());

                }

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.d("dddddddddd", "onFailure: "+t.getLocalizedMessage());

            }
        });
    }
}