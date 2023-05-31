package com.example.foodplanner.models;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.database.room.LocalSource;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.RemoteSource;

import java.util.List;


public class Repository implements RepositoryInterface {

    public static Repository repository=null;
    private Context context;
    RemoteSource remoteSource;
    LocalSource localSource;

    private Repository( RemoteSource remoteSource,Context context,LocalSource localSource) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }
    public static Repository getInstance(RemoteSource rSource,Context cont,LocalSource localSource)
    {
        if(repository==null)
            repository=new Repository(rSource,cont,localSource);
        return repository;
    }

    @Override
    public void getInspirationMeals(NetworkDelegate networkDelegate) {
        remoteSource.getRandomInspiartion(networkDelegate);
    }

    @Override
    public void getAllCategories(NetworkDelegate networkDelegate) {
        remoteSource.getAllCategories(networkDelegate);
    }

    @Override
    public void getAllIngradients(NetworkDelegate networkDelegate) {
        remoteSource.getAllIngredient(networkDelegate);
    }

    @Override
    public void getAllCountries(NetworkDelegate networkDelegate) {
        remoteSource.getAllCountries(networkDelegate);
    }

    @Override
    public void getAllCategoryMeals(NetworkDelegate networkDelegate,String category) {
        remoteSource.getMealsByCategories( networkDelegate, category);
    }

    @Override
    public void getAllCountryMeals(NetworkDelegate networkDelegate, String country) {
        remoteSource.getMealsByCountries(networkDelegate,country);

    }

    @Override
    public void getMealsByName(NetworkDelegate networkDelegate, String name) {
        remoteSource.getMealsByName(networkDelegate,name);

    }

    @Override
    public void getMealsByIngradient(NetworkDelegate networkDelegate, String ingradient) {
        remoteSource.getMealsByIngredients(networkDelegate,ingradient);
    }

    @Override
    public void insertFavourite(Meal meal) {
        localSource.insertFavorite(meal);
    }

    @Override
    public void removeFavourite(Meal meal) {
        localSource.removeFavorite(meal);

    }

    @Override
    public LiveData<List<Meal>> getAllStoredFavorites() {
        return localSource.getAllStoredFavorites();
    }

}
