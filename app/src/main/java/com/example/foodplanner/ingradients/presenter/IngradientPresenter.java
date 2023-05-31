package com.example.foodplanner.ingradients.presenter;

import com.example.foodplanner.ingradients.view.IngradientFragmentViewInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class IngradientPresenter implements NetworkDelegate {

    IngradientFragmentViewInterface ingradientFragmentViewInterface;
    Repository repo;

    public IngradientPresenter(IngradientFragmentViewInterface ingradientFragmentViewInterface, Repository repo) {
        this.ingradientFragmentViewInterface = ingradientFragmentViewInterface;
        this.repo = repo;
    }
    public void getAllIngradientMeals(String ingradient){repo.getMealsByIngradient(this,ingradient);}

    @Override
    public void onSuccessResponse(List<Meal> meals) {
        ingradientFragmentViewInterface.ViewIngradientMeal(meals);
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
