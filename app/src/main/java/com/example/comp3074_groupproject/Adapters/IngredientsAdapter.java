package com.example.comp3074_groupproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp3074_groupproject.Models.ExtendedIngredient;
import com.example.comp3074_groupproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.meal_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.ingredient_name.setText(list.get(position).name);
        holder.ingredient_name.setSelected(true);
        holder.ingredients_quantity.setText(list.get(position).original);
        Picasso.get()
                .load("https://spoonacular.com/cdn/ingredients_100x100/"+ list.get(position).image)
                .into(holder.ingredient_image);
        holder.ingredients_quantity.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder{

    TextView ingredients_quantity, ingredient_name;
    ImageView ingredient_image;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredients_quantity = itemView.findViewById(R.id.ingredients_quantity);
        ingredient_name = itemView.findViewById(R.id.ingredient_name);
        ingredient_image = itemView.findViewById(R.id.ingredient_image);
    }
}
