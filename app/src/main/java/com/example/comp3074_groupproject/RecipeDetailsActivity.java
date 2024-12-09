package com.example.comp3074_groupproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp3074_groupproject.Adapters.IngredientsAdapter;
import com.example.comp3074_groupproject.Listerners.RecipeDetailListener;
import com.example.comp3074_groupproject.Models.RecipeDetailResponse;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    TextView meal_name, meal_source, meal_summary;
    ImageView meal_image;
    RecyclerView recycler_meal_ingredients;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_details);


        findViews();
        id = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("id")));
        manager = new RequestManager(this);
        manager.getDetails(detailListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.show();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void findViews() {
        meal_name = findViewById(R.id.meal_name);
        meal_source = findViewById(R.id.meal_source);
        meal_summary = findViewById(R.id.meal_summary);
        meal_image = findViewById(R.id.meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);


    }

    private final RecipeDetailListener detailListener = new RecipeDetailListener() {
        @Override
        public void didFetch(RecipeDetailResponse response, String message) {
            dialog.dismiss();
            meal_name.setText(response.title);
            meal_source.setText(response.sourceName);
            meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager
                    (new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);


        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };
}