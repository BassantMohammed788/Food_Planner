package com.example.foodplanner.favourite.presenter;

import com.example.foodplanner.favourite.view.FavouriteFragmentViewInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class FavouritePresenter  {
    FavouriteFragmentViewInterface viewInterface;
    Repository repo;

    public FavouritePresenter(FavouriteFragmentViewInterface viewInterface, Repository repo) {
        this.viewInterface = viewInterface;
        this.repo = repo;
    }


    public void getAllFavourites()
    {
        viewInterface.showFavMeals(repo.getAllStoredFavorites());
    }

    public void deleteFromFav(Meal meal)
    {
        repo.removeFavourite(meal);
    }

}
