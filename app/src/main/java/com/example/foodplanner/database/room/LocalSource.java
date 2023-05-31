package com.example.foodplanner.database.room;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface LocalSource {
    void insertFavorite(Meal meal);
    void removeFavorite(Meal meal);
    public LiveData<List<Meal>> getAllStoredFavorites();
}
