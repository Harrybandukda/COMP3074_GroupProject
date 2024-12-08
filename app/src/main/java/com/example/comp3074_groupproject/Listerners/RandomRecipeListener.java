package com.example.comp3074_groupproject.Listerners;

import com.example.comp3074_groupproject.Models.RandomRecipeResponse;

public interface RandomRecipeListener {
    void didFetch(RandomRecipeResponse response, String message);
    void didError(String message);
}
