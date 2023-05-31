package com.example.foodplanner.database.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.models.Meal;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("SELECT * FROM Meals")
    LiveData<List<Meal>> getAllFavourites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(Meal meal);

    @Delete
    void deleteFavourite(Meal meal);
}
