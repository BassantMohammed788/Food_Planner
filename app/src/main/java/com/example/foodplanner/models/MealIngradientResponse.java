package com.example.foodplanner.models;

import java.util.ArrayList;
import java.util.List;

public class MealIngradientResponse {
    private List<MealIngradient> meals ;

    public List<MealIngradient> getMealsIngeadient() {
        return meals;
    }

    public void setMeals(List<MealIngradient> meals) {
        this.meals = meals;
    }

}
