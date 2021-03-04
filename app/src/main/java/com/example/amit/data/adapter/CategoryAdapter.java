package com.example.amit.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amit.R;
import com.example.amit.data.model.category.CategoriesItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryHolder> {

    private Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    private List<CategoriesItem>categoriesItems=new ArrayList<>();

    public void setCategoriesItems(List<CategoriesItem> categoriesItems) {
        this.categoriesItems = categoriesItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public categoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).
                inflate(R.layout.category_design,parent,false);
        return new categoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryHolder holder, int position) {
     CategoriesItem categories=categoriesItems.get(position);
     if (categories!=null){
         holder.name.setText(categories.getName());
         Glide.with(context).load(categories.getAvatar()).into(holder.image);
     }
    }

    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    static class categoryHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;


        public categoryHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.category_name);
            image=itemView.findViewById(R.id.category_image);
        }
    }
}
