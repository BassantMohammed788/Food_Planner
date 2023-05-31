package com.example.foodplanner.favourite.view;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface FavouriteFragmentViewInterface {

    public void showFavMeals(LiveData<List<Meal>> meals);
    public void deleteMeal(Meal meal);
}
