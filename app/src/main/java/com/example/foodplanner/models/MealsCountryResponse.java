package com.example.foodplanner.models;

import java.util.List;

public class MealsCountryResponse {
    public List<MealsCountry> meals;

    public MealsCountryResponse(List<MealsCountry> meals) {
        this.meals = meals;
    }

    public List<MealsCountry> getMeals() {
        return meals;
    }

    public void setMeals(List<MealsCountry> meals) {
        this.meals = meals;
    }
}
