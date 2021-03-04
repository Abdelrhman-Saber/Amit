package com.example.amit.data.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amit.R;
import com.example.amit.data.model.product.ProductsItem;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.productHolder> {

    private List<ProductsItem>productsItems=new ArrayList<>();
    private ProductClickListner clickListner;

    public void setProductsItems(List<ProductsItem> productsItems) {
        this.productsItems = productsItems;
        notifyDataSetChanged();
    }


    public ProductAdapter(Context context,ProductClickListner productClickListner) {
        this.context = context;
        this.clickListner=productClickListner;
    }

    private Context context;


    @NonNull
    @Override
    public productHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.products_desgin,parent,false);
        return new productHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull productHolder holder, int position) {
       ProductsItem product =productsItems.get(position);
       if (product != null){
           Glide.with(context).load(product.getAvatar()).into(holder.image);
           holder.name.setText(product.getName());
           holder.price.setText(String.valueOf(product.getPriceFinal())+"EGB");
           holder.desc.setText(String.valueOf(product.getDescription()));
       }

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               clickListner.showProductDetails(product);

           }
       });
    }

    @Override
    public int getItemCount() {
        return productsItems.size();
    }

    static class productHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,desc,price;
        Button btn;
        public productHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.product_imageView);
            name=itemView.findViewById(R.id.product_name);
            desc=itemView.findViewById(R.id.product_description);
            price=itemView.findViewById(R.id.product_price);
        }
    }
}
