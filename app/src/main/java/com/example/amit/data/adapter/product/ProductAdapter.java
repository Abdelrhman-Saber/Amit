package com.example.amit.data.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amit.R;
import com.example.amit.data.model.product.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.productHolder> {

    private List<Product> products =new ArrayList<>();
    private ProductClickListner clickListner;

    public void setProducts(List<Product> products) {
        this.products = products;
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
       Product product = products.get(position);
       if (product != null){
           Glide.with(context).load(product.getAvatar()).into(holder.image);
           holder.title.setText(product.getTitle());
           holder.price.setText(String.valueOf(product.getPriceFinal())+" "+"EGB");
           holder.name.setText(String.valueOf(product.getName()));
       }

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               clickListner.showProductDetails(product);

           }
       });

       holder.add_Btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickListner.addProductToCart(product);
           }
       });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class productHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,name,price;
        ImageButton add_Btn;
        public productHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.product_imageView);
            title=itemView.findViewById(R.id.product_name);
            name=itemView.findViewById(R.id.product_description);
            price=itemView.findViewById(R.id.product_price);
            add_Btn=itemView.findViewById(R.id.product_imageButton);
        }
    }
}
