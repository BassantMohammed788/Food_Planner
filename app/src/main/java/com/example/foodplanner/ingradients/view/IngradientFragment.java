package com.example.foodplanner.ingradients.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.country.presenter.CountryMealsPresenter;
import com.example.foodplanner.country.view.CountryFragmentArgs;
import com.example.foodplanner.country.view.CountryMealsAdapter;
import com.example.foodplanner.database.room.ConcreteLocalSource;
import com.example.foodplanner.ingradients.presenter.IngradientPresenter;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.RemoteMeal;

import java.util.ArrayList;
import java.util.List;


public class IngradientFragment extends Fragment implements IngradientFragmentViewInterface,OnIngradientClickListener {
    RecyclerView ingradientRecyclerView;
    MealsByIngradientAdapter ingradientAdapter;
    LinearLayoutManager linearLayoutManager;
    IngradientPresenter ingradientPresenter;
    TextView mealNamt_tv;
    String meal_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ingradient, container, false);
        initUI(view);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ingradientAdapter = new MealsByIngradientAdapter(getContext(), new ArrayList<>(), this);
        ingradientRecyclerView.setLayoutManager(linearLayoutManager);
        ingradientRecyclerView.setAdapter(ingradientAdapter);
        ingradientPresenter = new IngradientPresenter(this,Repository.getInstance(RemoteMeal.getInstance(), getContext(), ConcreteLocalSource.getInstance(getContext())));

        meal_name = IngradientFragmentArgs.fromBundle(getArguments()).getIngradientName();
        if (meal_name != null) {
            mealNamt_tv.setText(meal_name);
        }
        ingradientPresenter.getAllIngradientMeals(meal_name);
        return  view;
    }

    @Override
    public void ViewIngradientMeal(List<Meal> meals) {

        ingradientAdapter.setList(meals);
        ingradientAdapter.notifyDataSetChanged();

    }
    private void initUI(View view) {
        ingradientRecyclerView = view.findViewById(R.id.MealBasedOnIngradientRecycler);
        mealNamt_tv = view.findViewById(R.id.MealNameBasedOnIngradient);
    }
}