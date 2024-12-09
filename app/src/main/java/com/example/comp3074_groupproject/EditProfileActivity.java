package com.example.comp3074_groupproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.comp3074_groupproject.database.DatabaseHelper;
import com.example.comp3074_groupproject.database.User;

import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editName,
            editHeightFt,
            editHeightIn,
            editWeight;
    private TextView displayYearJoined;
    private Spinner editActivityLvl;
    private final int userId = 1;
    private int year_joined_hold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edit_profile_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("EditProfileActivity", "Entered");

        editName = findViewById(R.id.edit_username);
        editHeightFt = findViewById(R.id.height_ft);
        editHeightIn = findViewById(R.id.height_in);
        editWeight = findViewById(R.id.profile_weight);
        editActivityLvl = findViewById(R.id.profile_activity_level);
        displayYearJoined = findViewById(R.id.year_joined);

        loadUserData();
    }

    private void loadUserData() {
        Log.d("EditProfileActivity", "Called loadUserData");

        dbHelper = new DatabaseHelper(this);
        User user = dbHelper.getUserById(userId);
        Log.d("EditProfileActivity", user.getName());

        editName.setText(user.getName());
        editHeightFt.setText(String.valueOf(user.getHeightFt()));
        editHeightIn.setText(String.valueOf(user.getHeightIn()));
        editWeight.setText(String.valueOf(user.getWeight()));
        editActivityLvl.setSelection(user.getActivityLvlPosition());
        year_joined_hold = user.getYearJoined();
        displayYearJoined.setText("Member since "+ year_joined_hold);
    }

    public void toSave(View view) {

        try {
            String name = editName.getText().toString().trim();
            String heightFtString = editHeightFt.getText().toString().trim();
            String heightInString = editHeightIn.getText().toString().trim();
            String weightString = editWeight.getText().toString().trim();
            String activityLvl = editActivityLvl.getSelectedItem().toString();
            String activityLvlPositionString = String.valueOf(editActivityLvl.getSelectedItemPosition());
            String yearJoinedString = displayYearJoined.getText().toString().trim();

            if (name.isEmpty() ||
                    heightFtString.isEmpty() ||
                    heightInString.isEmpty() ||
                    weightString.isEmpty() ||
                    activityLvl.isEmpty() ||
                    yearJoinedString.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int heightFt = Integer.parseInt(heightFtString);
            int heightIn = Integer.parseInt(heightInString);
            long weight = Long.parseLong(weightString);
            int activityLvlPosition = Integer.parseInt(activityLvlPositionString);

            long result = dbHelper.updateUser(userId, name, heightFt, heightIn, weight, activityLvl, activityLvlPosition, year_joined_hold);

            if (result != -1) {
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                dbHelper.close();
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Log.e("EditProfileActivity, toSave", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
