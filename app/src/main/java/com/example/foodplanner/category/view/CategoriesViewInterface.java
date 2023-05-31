package com.example.foodplanner.category.view;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface CategoriesViewInterface {
    public void ViewCategoriesMeal(List<Meal> meals);
    public void addToFavorite (Meal meal);
}
