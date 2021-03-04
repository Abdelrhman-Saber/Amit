package com.example.amit.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.amit.R;
import com.example.amit.data.adapter.product.ProductAdapter;
import com.example.amit.data.adapter.product.ProductClickListner;
import com.example.amit.data.api.ApiManager;
import com.example.amit.data.model.product.ProductResponse;
import com.example.amit.data.model.product.ProductsItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements ProductClickListner {

    ProductAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView productRecycler;
    NavController navController;
    LinearLayout layout;




    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController= Navigation.findNavController(view);

        initRecycler(view);
        initView(view);
     ApiManager.productService().getProducts().enqueue(new Callback<ProductResponse>() {
         @Override
         public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
             if (response.isSuccessful()){

                 adapter.setProductsItems(response.body().getProducts());
             }
         }

         @Override
         public void onFailure(Call<ProductResponse> call, Throwable t) {
             Log.d("ddddddddddddddddddd", "onFailure: "+t.getLocalizedMessage());

         }
     });


    }
    private void initRecycler(View v){

        adapter=new ProductAdapter(getContext(),this);
        productRecycler=v.findViewById(R.id.homeRecycler);
        layoutManager=new GridLayoutManager(getContext(),2);
        productRecycler.setAdapter(adapter);
        productRecycler.setLayoutManager(layoutManager);
    }

    private void initView(View v){
        layout=v.findViewById(R.id.product_layout);
    }

    @Override
    public void showProductDetails(ProductsItem product) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("product",product);
        navController.navigate(R.id.action_menu_home_to_detailsFragment);

    }

    @Override
    public void addProductToCart(ProductsItem product) {

    }
}
