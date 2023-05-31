package com.example.foodplanner.database.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{
    private MealDAO mealDAO;
    public static ConcreteLocalSource concreteLocalSourceInstance=null;
    private LiveData<List<Meal>> mealList;

    public ConcreteLocalSource(Context context) {
        AppDataBase appDataBase=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=appDataBase.mealDAO();
        mealList=mealDAO.getAllFavourites();
    }

    public static ConcreteLocalSource getInstance(Context context)
    {
        if(concreteLocalSourceInstance==null)
            concreteLocalSourceInstance=new ConcreteLocalSource(context);
        return concreteLocalSourceInstance;
    }

    @Override
    public void insertFavorite(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertFavourite(meal);
            }
        }).start();

    }

    @Override
    public void removeFavorite(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteFavourite(meal);
            }
        }).start();

    }

    @Override
    public LiveData<List<Meal>> getAllStoredFavorites() {
        return mealList;
    }
}
