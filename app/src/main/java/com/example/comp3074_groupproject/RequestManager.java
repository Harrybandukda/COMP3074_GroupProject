package com.example.comp3074_groupproject;

import android.content.Context;

import com.example.comp3074_groupproject.Listerners.RandomRecipeListener;
import com.example.comp3074_groupproject.Listerners.RecipeDetailListener;
import com.example.comp3074_groupproject.Models.RandomRecipeResponse;
import com.example.comp3074_groupproject.Models.RecipeDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        String tagsParam = String.join(",", tags);
        Call<RandomRecipeResponse> call =
                callRandomRecipes.callRandomRecipe(context
                        .getString(R.string.api_key), "5", tags);

        call.enqueue(new Callback<RandomRecipeResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeResponse> call, Response<RandomRecipeResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getDetails(RecipeDetailListener listener, int id) {
        RecipeDetails callRecipeDetails = retrofit.create(RecipeDetails.class);
        Call<RecipeDetailResponse> call =
                callRecipeDetails.callDetails(id, context
                        .getString(R.string.api_key));

        call.enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeResponse> callRandomRecipe(
                @Query("apiKey") String apikey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    private interface RecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailResponse> callDetails(
                @Path("id") int id,
                @Query("apiKey") String apikey

        );
    }
}

