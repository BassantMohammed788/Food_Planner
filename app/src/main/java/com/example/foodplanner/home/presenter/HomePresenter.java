package com.example.foodplanner.home.presenter;


import com.example.foodplanner.home.view.HomeViewInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class HomePresenter implements NetworkDelegate {
    HomeViewInterface inspirationMealViewInterface;
    Repository repo;

    public HomePresenter(HomeViewInterface inspirationMealViewInterface, Repository repo) {
        this.inspirationMealViewInterface = inspirationMealViewInterface;
        this.repo = repo;
    }

    public void getInspirationMeals() {
        repo.getInspirationMeals(this);
    }

    public void getAllCategories() {
        repo.getAllCategories(this);
    }

    @Override
    public void onSuccessResponse(List<Meal> meals) {
        inspirationMealViewInterface.showInspirationMeal(meals);

    }

    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);
    }

    @Override
    public void onSuccessCategories(List<Category> categoryList) {
        inspirationMealViewInterface.showAllCategories(categoryList);

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
