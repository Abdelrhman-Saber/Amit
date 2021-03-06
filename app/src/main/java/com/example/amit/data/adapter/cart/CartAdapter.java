package com.example.amit.data.adapter.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amit.R;
import com.example.amit.data.adapter.product.ProductAdapter;
import com.example.amit.data.model.cart.ProductsItem;
import com.example.amit.data.model.product.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartHolder>{
  List<ProductsItem>products=new ArrayList<>();
    Context context;

    public void setProducts(List<ProductsItem> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public cartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.
                cart_design,parent,false);
        return new cartHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull cartHolder holder, int position) {
        ProductsItem product=products.get(position);
        if (product!=null){
            Picasso.get().load(product.getProduct().getAvatar()).into(holder.image);
            holder.name.setText(product.getProduct().getName());
            holder.title.setText(product.getProduct().getTitle());
            holder.price.setText(String.valueOf(product.getProduct().getPriceFinal()));
        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class cartHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,name,price;

        public cartHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.cart_image);
            title=itemView.findViewById(R.id.cart_title);
            name=itemView.findViewById(R.id.cart_name);
            price=itemView.findViewById(R.id.cart_price);
        }
    }
}
