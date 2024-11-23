package com.example.comp3074_groupproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    // get from profile at some point
    private final String[] diet_pref_arr = {
            "Vegetarian",
            "Low Carb",
            "High Protein",
            "Gluten-Free"
    };

    // get from profile at some point
    private final String[] allergies = {
            "Peanuts",
            "Tree Nuts",
            "Gluten",
            "Dairy"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Create Dynamic Flow for Diet Pref.
        createDynamicFlow(
                diet_pref_arr,
                R.id.dp_flow_parent,
                R.id.dp_flow,
                getDrawable(R.drawable.rounded_linear_layout)
        );

        // Create Dynamic Flow for Allergies
        createDynamicFlow(
                allergies,
                R.id.anr_flow_parent,
                R.id.anr_flow,
                getDrawable(R.drawable.rounded_layout_red)
        );



    }

    private void createDynamicFlow(String[] arr, int parent_id, int flow_id, Drawable drawable) {
        ConstraintLayout parent = findViewById(parent_id);
        Flow dp_flow = findViewById(flow_id);
        List<Integer> viewId = new ArrayList<>();
        int[] ids = new int[arr.length];

        for(String pref: arr){
            TextView temp = new TextView(this);
            temp.setId(View.generateViewId());
            temp.setText(pref);
            temp.setBackground(drawable);
            temp.setGravity(Gravity.CENTER);
            temp.setHeight(150);
            temp.setWidth(300);

            parent.addView(temp);
            viewId.add(temp.getId());
        }

        for (int i=0; i<arr.length; i++)
            ids[i] = viewId.get(i);
        dp_flow.setReferencedIds(ids);
    }


    // On Clicks
    public void toEditProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void toSettings(View view) {
        // no setting atm
    }

    public void toEditPreferences(View view) {
        Intent intent = new Intent(this, EditDietPrefActivity.class);
        startActivity(intent);
    }

    public void toEditAllergies(View view) {
        Intent intent = new Intent(this, EditAllergiesActivity.class);
        startActivity(intent);
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