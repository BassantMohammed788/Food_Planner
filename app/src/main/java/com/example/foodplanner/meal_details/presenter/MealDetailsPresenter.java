package com.example.foodplanner.meal_details.presenter;

import com.example.foodplanner.meal_details.view.MealDetailsViewInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class MealDetailsPresenter implements NetworkDelegate {
    MealDetailsViewInterface mealDetailsViewInterface;
    Repository repo;

    public MealDetailsPresenter(MealDetailsViewInterface mealDetailsViewInterface, Repository repo) {
        this.mealDetailsViewInterface = mealDetailsViewInterface;
        this.repo = repo;
    }


    public void getMealDetails(String mealName) {

        repo.getMealsByName(this,mealName);
    }


    public void insertFavourite(Meal meal){
        repo.insertFavourite(meal);

    };

    @Override
    public void onSuccessResponse(List<Meal> meals) {
        mealDetailsViewInterface.ViewMealDetails(meals);

    }

    @Override
    public void onFailureResponse(String errorMsg) {

    }

    @Override
    public void onSuccessCategories(List<Category> categoryList) {

    }

    @Override
    public void onSuccessIngredients(List<MealIngradient> ingredients) {

    }

    @Override
    public void onSuccessCountries(List<MealsCountry> countries) {

    }

    @Override
    public void onSuccessCategoryMeals(List<Meal> meals) {

    }

    @Override
    public void onSuccessCountryMeals(List<Meal> meals) {

    }
}
