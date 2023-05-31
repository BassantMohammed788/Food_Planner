package com.example.foodplanner.Search.View;

import com.example.foodplanner.models.MealIngradient;

public interface OnSearchClickListener {
    public void onClick(MealIngradient meal);
    void countryItemOnClick(String country);
    public void countryOnClick();
    public void categoryItemOnClick();

}
