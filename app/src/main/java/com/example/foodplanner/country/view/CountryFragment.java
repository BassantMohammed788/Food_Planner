package com.example.foodplanner.country.view;

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
import com.example.foodplanner.database.room.ConcreteLocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.RemoteMeal;

import java.util.ArrayList;
import java.util.List;


public class CountryFragment extends Fragment implements CountriesViewInterface,OnCountryClickListener{
    RecyclerView allCountryMealRecyclerView;
    CountryMealsAdapter countryMealsAdapter;
    LinearLayoutManager allCountryMealLayoutManager;
    CountryMealsPresenter countryMealsPresenter;
    String countryName;
    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_country, container, false);
        initUI(view);
        allCountryMealLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        countryMealsAdapter = new CountryMealsAdapter(getContext(), new ArrayList<>(), this);
        allCountryMealRecyclerView.setLayoutManager(allCountryMealLayoutManager);
        allCountryMealRecyclerView.setAdapter(countryMealsAdapter);
        countryMealsPresenter = new CountryMealsPresenter(this, Repository.getInstance(RemoteMeal.getInstance(), getContext(), ConcreteLocalSource.getInstance(getContext())));

        countryName = CountryFragmentArgs.fromBundle(getArguments()).getCountryyName();
        if (countryName != null) {
            textView.setText(countryName);
        }
        countryMealsPresenter.getAllCountryMeals(countryName);
        return view;
    }
    private void initUI(View view) {
        allCountryMealRecyclerView = view.findViewById(R.id.countryMealRecyclerView);
        textView = view.findViewById(R.id.countryNameCountry);
    }

    @Override
    public void addToFavoriteOnClick(Meal meal) {

    }

    @Override
    public void ViewCountriesMeal(List<Meal> meals) {
        countryMealsAdapter.setList(meals);
        countryMealsAdapter.notifyDataSetChanged();

    }
}