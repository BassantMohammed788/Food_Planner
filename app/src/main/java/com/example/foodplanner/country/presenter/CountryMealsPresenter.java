package com.example.foodplanner.country.presenter;

import com.example.foodplanner.country.view.CountriesViewInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class CountryMealsPresenter implements NetworkDelegate {
    CountriesViewInterface countriesViewInterface;
    Repository repo;

    public CountryMealsPresenter(CountriesViewInterface countriesViewInterface, Repository repo) {
        this.countriesViewInterface = countriesViewInterface;
        this.repo = repo;
    }

    public void getAllCountryMeals(String country){repo.getAllCountryMeals(this,country);}

    @Override
    public void onSuccessCountryMeals(List<Meal> meals) {
        countriesViewInterface.ViewCountriesMeal(meals);
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

    @Override
    public void onSuccessCategoryMeals(List<Meal> meals) {

    }


}
