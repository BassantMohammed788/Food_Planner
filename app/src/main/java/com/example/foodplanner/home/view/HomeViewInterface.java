package com.example.foodplanner.home.view;

import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;

import java.util.List;

public interface HomeViewInterface {

    public void showInspirationMeal(List<Meal> meal);
    public void showAllCategories(List<Category> category);
}
