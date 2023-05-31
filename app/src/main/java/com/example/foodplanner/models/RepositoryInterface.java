package com.example.foodplanner.models;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    //retrofit
    public void getInspirationMeals(NetworkDelegate networkDelegate);
    public void getAllCategories(NetworkDelegate networkDelegate);
    public void getAllIngradients(NetworkDelegate networkDelegate);
    public void getAllCountries(NetworkDelegate networkDelegate);
    public void getAllCategoryMeals(NetworkDelegate networkDelegate,String category);
    public void getAllCountryMeals(NetworkDelegate networkDelegate,String country);
    public void getMealsByName(NetworkDelegate networkDelegate,String name);
    public void getMealsByIngradient(NetworkDelegate networkDelegate,String ingradient);

    //room

    public void insertFavourite(Meal meal);
    public void removeFavourite(Meal meal);
    public LiveData<List<Meal>> getAllStoredFavorites();
}
