package com.example.comp3074_groupproject;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MealPlannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_planner);

        // Example: Open pop-up when a meal is clicked
        findViewById(R.id.yogurt_bowl).setOnClickListener(v -> showMealDetails());
    }

    private void showMealDetails() {
        MealDetailsDialogFragment dialogFragment = new MealDetailsDialogFragment(
                "Greek Yogurt Bowl",
                "• 1 cup Greek yogurt\n• 1/2 cup mixed berries\n• 1 tbsp honey\n• 1/4 cup granola",
                "1. Layer yogurt in a bowl\n2. Top with berries\n3. Drizzle with honey\n4. Sprinkle granola on top",
                "320 calories | 20g protein | 45g carbs | 8g fat"
        );
        dialogFragment.show(getSupportFragmentManager(), "MealDetailsDialog");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

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