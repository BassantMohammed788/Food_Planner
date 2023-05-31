package com.example.foodplanner.network;

import com.example.foodplanner.models.Meal;

public interface RemoteSource {
    public void getRandomInspiartion(NetworkDelegate networkDelegate);
    public void getAllCategories(NetworkDelegate networkDelegate);

    public void getAllIngredient(NetworkDelegate networkDelegate);

    public void getAllCountries(NetworkDelegate networkDelegate);
    public void getMealsByCountries(NetworkDelegate networkDelegate, String country);
    public void getMealsByCategories(NetworkDelegate networkDelegate, String category);
    public void  getMealsByName(NetworkDelegate networkDelegate,String name);
    void getMealsByIngredients(NetworkDelegate networkDelegate, String ingredient);
}
