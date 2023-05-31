package com.example.foodplanner.meal_details.view;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface MealDetailsViewInterface {
    void ViewMealDetails(List<Meal> meal);

    void addMealToPlan(Meal meal);

    public void addToFavorite (Meal meal);
}
