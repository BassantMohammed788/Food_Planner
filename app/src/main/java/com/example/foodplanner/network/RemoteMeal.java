package com.example.foodplanner.network;

import android.widget.Toast;

import com.example.foodplanner.models.CategoryResponse;
import com.example.foodplanner.models.MealIngradientResponse;
import com.example.foodplanner.models.MealResponse;
import com.example.foodplanner.models.MealsCountryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteMeal implements RemoteSource {
    private static final String BASE_URL = " http://www.themealdb.com:80/api/json/v1/1/";
    private static final String TAG = "Remote_Meal";
    private static RemoteMeal instance = null;
    MealApiInterface mealApiInterface;

    public RemoteMeal() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealApiInterface = retrofit.create(MealApiInterface.class);
    }

    public static RemoteMeal getInstance() {
        if (instance == null)
            instance = new RemoteMeal();

        return instance;
    }

    @Override
    public void getRandomInspiartion(NetworkDelegate networkDelegate) {
        Call<MealResponse> call = mealApiInterface.getAllMeals();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    networkDelegate.onSuccessResponse(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());

            }


        });
    }

    @Override
    public void getAllCategories(NetworkDelegate networkDelegate) {
        Call<CategoryResponse> call = mealApiInterface.getAllCategories();
        call.enqueue(new Callback<CategoryResponse>() {


            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    networkDelegate.onSuccessCategories(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        });

    }

    @Override
    public void getAllIngredient(NetworkDelegate networkDelegate) {
        Call<MealIngradientResponse> call = mealApiInterface.getAllIngredient();
        call.enqueue(new Callback<MealIngradientResponse>() {


            @Override
            public void onResponse(Call<MealIngradientResponse> call, Response<MealIngradientResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    networkDelegate.onSuccessIngredients(response.body().getMealsIngeadient());
            }

            @Override
            public void onFailure(Call<MealIngradientResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        });


    }

    @Override
    public void getAllCountries(NetworkDelegate networkDelegate) {
        Call<MealsCountryResponse> call = mealApiInterface.getAllCountries();
        call.enqueue(new Callback<MealsCountryResponse>() {
            @Override
            public void onResponse(Call<MealsCountryResponse> call, Response<MealsCountryResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    networkDelegate.onSuccessCountries(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealsCountryResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        });
    }

    @Override
    public void getMealsByCountries(NetworkDelegate networkDelegate, String country) {
        Call<MealResponse> call = mealApiInterface.getMealsByCountries(country);
        call.enqueue((new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkDelegate.onSuccessCountryMeals(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        }));

    }

    @Override
    public void getMealsByCategories(NetworkDelegate networkDelegate, String category) {
        Call<MealResponse> call = mealApiInterface.getMealsByCategories(category);
        call.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkDelegate.onSuccessCategoryMeals(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        });
    }

    @Override
    public void getMealsByName(NetworkDelegate networkDelegate, String name) {
        Call<MealResponse> call = mealApiInterface.getMealsByName(name);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkDelegate.onSuccessResponse(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());

            }
        });
    }

    @Override
    public void getMealsByIngredients(NetworkDelegate networkDelegate, String ingredient) {
        Call<MealResponse> call = mealApiInterface.getMealsByName(ingredient);
        call.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkDelegate.onSuccessResponse(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }

            });
        }
}
