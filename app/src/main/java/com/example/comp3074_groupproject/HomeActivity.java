package com.example.comp3074_groupproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.comp3074_groupproject.database.DatabaseHelper;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        try(DatabaseHelper dbHelper = new DatabaseHelper(this)){
            if(dbHelper.getUserById(1) == null)
                dbHelper.insertInitialData();
        }catch (Exception e){
            Log.e("Error", "---------------Error getting db");
        }

        findViewById(R.id.btnStartPlanning).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Navigate to Meal Planner Activity
                Intent intent = new Intent(HomeActivity.this, MealPlannerActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Navigation bar methods
    public void navigateToHome(View view) {
        // Do nothing, as the user is already on the Home page
    }

    public void navigateToMealPlanner(View view) {
        Intent intent = new Intent(this, MealPlannerActivity.class);
        startActivity(intent);
    }

    public void navigateToProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void navigateToHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    // Meet the team
    public void navigateToTeam(View view) {
        Intent intent = new Intent(this, TeamActivity.class);
        startActivity(intent);
    }
}