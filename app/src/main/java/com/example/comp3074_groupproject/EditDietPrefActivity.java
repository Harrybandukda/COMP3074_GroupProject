package com.example.comp3074_groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.comp3074_groupproject.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditDietPrefActivity extends AppCompatActivity {

    private String[] stringDietPref;
    private String[] userDietPref;
    private final int userId = 1;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_dietary_pref);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dp_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get user prefs
        stringDietPref = getResources().getStringArray(R.array.diet_pref_string);
        dbHelper = new DatabaseHelper(this);
        userDietPref = dbHelper.getDietPrefByUserId(userId);
        List<String> listUserDietPref = new ArrayList<>(Arrays.asList(userDietPref));

        LinearLayout list = findViewById(R.id.dp_list);

        for(String s: stringDietPref){
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(s);
            checkBox.setChecked(listUserDietPref.contains(s));

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    dbHelper.addDietPreference(userId, s);
                } else {
                    dbHelper.deleteDietPreference(userId);
                }
            });
            list.addView(checkBox);
        }
    }

    public void toSave(View view) {
        dbHelper.close();
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
