package com.example.comp3074_groupproject;

import android.content.Context;

import com.example.comp3074_groupproject.Listerners.RandomRecipeListener;
import com.example.comp3074_groupproject.Models.RandomRecipeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
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

    public void getRandomRecipes(RandomRecipeListener listener){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeResponse> call =
                callRandomRecipes.callRandomRecipe(context
                        .getString(R.string.api_key), "5" );

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

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeResponse> callRandomRecipe(
                @Query("apiKey") String apikey,
                @Query("number") String number
        );
    }
}

