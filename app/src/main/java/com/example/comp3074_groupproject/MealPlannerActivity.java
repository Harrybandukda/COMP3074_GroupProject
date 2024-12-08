package com.example.comp3074_groupproject;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.comp3074_groupproject.Adapters.RandomRecipeAdapter;
import com.example.comp3074_groupproject.Listerners.RandomRecipeListener;
import com.example.comp3074_groupproject.Models.RandomRecipeResponse;

public class MealPlannerActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter recipeAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_planner);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private final RandomRecipeListener randomRecipeResponseListener = new RandomRecipeListener() {
        @Override
        public void didFetch(RandomRecipeResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MealPlannerActivity.this, 1));
            recipeAdapter = new RandomRecipeAdapter(MealPlannerActivity.this, response.recipes);
            recyclerView.setAdapter(recipeAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(MealPlannerActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    // Navigation between weeks
    public void previousWeek(View view) {
        Toast.makeText(this, "Previous week clicked", Toast.LENGTH_SHORT).show();
    }

    public void nextWeek(View view) {
        Toast.makeText(this, "Next week clicked", Toast.LENGTH_SHORT).show();
    }

    public void generateNewPlan(View view) {
        Toast.makeText(this, "Generating new plan...", Toast.LENGTH_SHORT).show();
    }

    // Share buttons
    public void shareToFacebook(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        startActivity(intent);
    }

    public void shareToTwitter(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"));
        startActivity(intent);
    }

    public void shareToEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out my meal plan!");
        intent.putExtra(Intent.EXTRA_TEXT, "Here's my meal plan for the week. Stay healthy!");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    // Navigation bar methods
    public void navigateToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void navigateToMealPlanner(View view) {
        // Do nothing, as the user is already on the Home page
    }

    public void navigateToProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void navigateToHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}