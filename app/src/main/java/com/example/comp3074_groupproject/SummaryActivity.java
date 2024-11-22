package com.example.comp3074_groupproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


    }


}
