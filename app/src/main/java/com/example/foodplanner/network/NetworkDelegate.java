package com.example.foodplanner.network;

import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;

import java.util.List;

public interface NetworkDelegate {
    public void onSuccessResponse(List<Meal> meals);
    public void onFailureResponse(String errorMsg);

    public void onSuccessCategories(List<Category> categoryList);

    public void onSuccessIngredients(List<MealIngradient> ingredients);
    public void onSuccessCountries(List<MealsCountry> countries);
    public void onSuccessCategoryMeals(List<Meal> meals);
    public void onSuccessCountryMeals(List<Meal> meals);


}
