package com.example.foodplanner.Search.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.foodplanner.Search.View.SearchViewInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class SearchPresenter implements NetworkDelegate {
    Context context;
    SearchViewInterface searchViewInterface;
    Repository repo;

    public SearchPresenter(SearchViewInterface searchViewInterface, Repository repo) {
        this.searchViewInterface = searchViewInterface;
        this.repo = repo;
    }

    public void getAllIngradients() {
        repo.getAllIngradients(this);
    }
    public void getAllCountries() {
        repo.getAllCountries(this);
    }
    public void getAllCategories() {
        repo.getAllCategories(this);
    }

    @Override
    public void onSuccessIngredients(List<MealIngradient> ingredients) {
        searchViewInterface.showIngradientsMeal(ingredients);
    }

    @Override
    public void onSuccessCountries(List<MealsCountry> countries) {
        searchViewInterface.showCountries(countries);

    }

    @Override
    public void onSuccessCategoryMeals(List<Meal> meals) {

    }

    @Override
    public void onSuccessCountryMeals(List<Meal> meals) {

    }

    @Override
    public void onSuccessResponse(List<Meal> meals) {


    }

    @Override
    public void onFailureResponse(String errorMsg) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessCategories(List<Category> categoryList) {
        searchViewInterface.showAllCatigories((categoryList));

    }

}
