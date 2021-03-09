package com.example.amit.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amit.R;
import com.example.amit.data.adapter.product.AddCartResponse;
import com.example.amit.data.adapter.product.ProductClickListner;
import com.example.amit.data.api.ApiManager;
import com.example.amit.data.model.product.Product;
import com.example.amit.helper.TokenManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsFragment extends Fragment implements ProductClickListner {
    Product product;
    Context context;
    TokenManager tokenManager;
    ProductClickListner clickListner;



    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null) {
            product = (Product) getArguments().getSerializable("products");
            Log.d("dddddddddd", "onCreate: "+product.getTitle());
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false); }

        ImageView imageView;
        TextView title,desc,price,name;
        Button btn;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        linking();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart(product);

            }
        });



    }
    private void initView(View v){

        imageView=v.findViewById(R.id.details_image);
        name=v.findViewById(R.id.details_name);
        price=v.findViewById(R.id.details_price);
        desc=v.findViewById(R.id.details_description);
        title=v.findViewById(R.id.details_title);
        btn=v.findViewById(R.id.details_button);

    }
    private void linking(){
        Picasso.get().load(product.getAvatar()).into(imageView);
        name.setText(product.getName());
        price.setText(String.valueOf(product.getPriceFinal()+" "+"EGB") );
        if (product.getDescription()==null){
            desc.setText("No Description");
        }
        else desc.setText(String.valueOf(product.getDescription()));
        title.setText(product.getTitle());
    }

    @Override
    public void showProductDetails(Product product) {

    }

    @Override
    public void addProductToCart(Product product) {
        tokenManager= new TokenManager(getActivity());
        String token= tokenManager.getToken();
        Log.d("dddddddddddddd", "addProductToCart: "+token);

        ApiManager.productService().addProductToCart(product.getId(),
                "Bearer " +token,1).enqueue(new Callback<AddCartResponse>() {
            @Override
            public void onResponse(Call<AddCartResponse> call, Response<AddCartResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCartResponse> call, Throwable t) {
                Log.d("ddddddddd", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}