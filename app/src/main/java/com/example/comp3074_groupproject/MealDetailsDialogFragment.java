package com.example.comp3074_groupproject;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MealDetailsDialogFragment extends DialogFragment {

    private String mealName, ingredients, instructions, nutrition;

    public MealDetailsDialogFragment(String mealName, String ingredients, String instructions, String nutrition) {
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.nutrition = nutrition;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Inflate the custom layout
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_meal_details, null);
        dialog.setContentView(view);

        // Set data in views
        TextView tvMealName = view.findViewById(R.id.tvMealName);
        TextView tvIngredients = view.findViewById(R.id.tvIngredients);
        TextView tvInstructions = view.findViewById(R.id.tvInstructions);
        TextView tvNutrition = view.findViewById(R.id.tvNutrition);
        TextView btnClose = view.findViewById(R.id.btnClose);

        tvMealName.setText(mealName);
        tvIngredients.setText(ingredients);
        tvInstructions.setText(instructions);
        tvNutrition.setText(nutrition);

        // Close button click
        btnClose.setOnClickListener(v -> dismiss());

        return dialog;
    }
}