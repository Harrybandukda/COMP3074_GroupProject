package com.example.comp3074_groupproject.Listerners;

import com.example.comp3074_groupproject.Models.RecipeDetailResponse;

public interface RecipeDetailListener {
    void didFetch(RecipeDetailResponse response, String message);
    void didError(String message);
}
