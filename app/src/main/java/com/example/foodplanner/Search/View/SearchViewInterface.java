package com.example.foodplanner.Search.View;

import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;

import java.util.List;

public interface SearchViewInterface {

    public void showIngradientsMeal(List<MealIngradient> mealIngradients);
    public void showCountries(List<MealsCountry> mealsCountries);
    public void showAllCatigories(List<Category> categories);
}
