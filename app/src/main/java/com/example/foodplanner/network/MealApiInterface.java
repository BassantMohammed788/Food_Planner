package com.example.foodplanner.network;

import com.example.foodplanner.models.CategoryResponse;
import com.example.foodplanner.models.MealIngradientResponse;
import com.example.foodplanner.models.MealResponse;
import com.example.foodplanner.models.MealsCountryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApiInterface {

    @GET("random.php")
    public Call<MealResponse> getAllMeals();
    @GET("categories.php")
    public Call <CategoryResponse> getAllCategories();
    @GET("list.php?i=list")
    public Call <MealIngradientResponse> getAllIngredient();
    @GET("list.php?a=list")
    public Call <MealsCountryResponse> getAllCountries();
    @GET("filter.php")
    public Call<MealResponse> getMealsByCategories(@Query("c") String category);
    @GET("filter.php")
    public Call<MealResponse> getMealsByCountries(@Query("a") String country);
    @GET("search.php")
    public Call<MealResponse> getMealsByName(@Query("s") String name);

    @GET("filter.php")
    public Call<MealResponse> getMealsByIngredients(@Query("i") String ingredient);

}
