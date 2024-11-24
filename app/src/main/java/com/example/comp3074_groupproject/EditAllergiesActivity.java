package com.example.comp3074_groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditAllergiesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_edit_allergies);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.anr_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout list = findViewById(R.id.anr_list);

        for(String s: allergies){
            CheckBox temp = new CheckBox(this);
            temp.setText(s);
            temp.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    // Save to somewhere so the profile is updated
                }
            });

            list.addView(temp);
        }
    }

    public void toSave(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
