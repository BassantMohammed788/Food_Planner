package com.example.foodplanner.category.presenter;

import com.example.foodplanner.category.view.CategoriesViewInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class CategoryPresenter implements NetworkDelegate {
    CategoriesViewInterface categoriesViewInterface;
    Repository repo;

    public CategoryPresenter(CategoriesViewInterface categoriesViewInterface, Repository repo) {
        this.categoriesViewInterface = categoriesViewInterface;
        this.repo = repo;
    }


    public void addToFav(Meal meal)
    {
        repo.insertFavourite(meal);
    }
    public void getAllCategoryMeals(String category){repo.getAllCategoryMeals(this,category);}
    @Override
    public void onSuccessCategoryMeals(List<Meal> meals) {
        categoriesViewInterface.ViewCategoriesMeal(meals);

    }

    @Override
    public void onSuccessCountryMeals(List<Meal> meals) {

    }

    @Override
    public void onSuccessResponse(List<Meal> meals) {

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


}
