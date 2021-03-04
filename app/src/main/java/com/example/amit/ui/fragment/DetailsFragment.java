package com.example.amit.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.amit.R;
import com.example.amit.data.model.product.ProductsItem;
import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {
    ProductsItem product;
    Context context;



    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null) {
            product = (ProductsItem) getArguments().getSerializable("product");
            Log.d("fffffffff", "onCreate: "+product.getTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false); }
/*ImageView imageView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView=view.findViewById(R.id.details_image);
        Picasso.get().load(product.getAvatar()).into(imageView);


    }*/
}