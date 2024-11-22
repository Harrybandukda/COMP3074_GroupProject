package com.example.comp3074_groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.summary), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Filter Setup
        Spinner meal_types = findViewById(R.id.filter_meal_types);
        String[] meal_types_arr = getResources().getStringArray(R.array.meal_types);
        ArrayAdapter<String> meal_types_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meal_types_arr);
        meal_types_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_types.setAdapter(meal_types_adapter);

        Spinner cal_range = findViewById(R.id.filer_calorie_range);
        String[] cal_range_arr = getResources().getStringArray(R.array.calorie_range);
        ArrayAdapter<String> cal_range_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cal_range_arr);
        cal_range_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cal_range.setAdapter(cal_range_adapter);

        findViewById(R.id.common_meal).setOnClickListener(v -> showMealDetails());

    }

    public void toMealHistoryList(View view) {
        Intent intent = new Intent(this, MealHistoryActivity.class);
        startActivity(intent);
    }

    public void toAvgDailyCalories(View view) {
        Toast.makeText(this, "Avg. Daily Calories: 2404", Toast.LENGTH_SHORT).show();
    }

    public void showMealDetails() {
        MealDetailsDialogFragment dialogFragment = new MealDetailsDialogFragment(
                "Greek Yogurt Bowl",
                "• 1 cup Greek yogurt\n• 1/2 cup mixed berries\n• 1 tbsp honey\n• 1/4 cup granola",
                "1. Layer yogurt in a bowl\n2. Top with berries\n3. Drizzle with honey\n4. Sprinkle granola on top",
                "320 calories | 20g protein | 45g carbs | 8g fat"
        );

        dialogFragment.show(getSupportFragmentManager(), "MealDetailsDialog");
    }

    public void toStreak(View view) {
        Toast.makeText(this, "Streak Length: 4 Days", Toast.LENGTH_SHORT).show();
    }

    public void toRecommendations(View view) {
        Intent intent = new Intent(this, RecommendationsActivity.class);
        startActivity(intent);
    }

    public void resetFilters(View view) {
        ((EditText) view.findViewById(R.id.filter_date)).setHint("YYYY-MM-DD");
        (view.findViewById(R.id.filter_meal_types)).setSelected(false);
        (view.findViewById(R.id.filer_calorie_range)).setSelected(false);
    }




    // Navigation bar methods
    public void navigateToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
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

}