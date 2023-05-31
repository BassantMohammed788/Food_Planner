package com.example.foodplanner.Search.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.SearchPresenter;
import com.example.foodplanner.Search.View.AllCatigoriesAdapter;
import com.example.foodplanner.database.room.ConcreteLocalSource;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.RemoteMeal;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface , OnSearchClickListener {

    SearchPresenter searchPresenter;
    SearchIngradientsAdapter searchIngradientsMealAdapter;
    SearchCountryAdapter searchCountryAdapter;
    LinearLayoutManager ingradientLinearLayoutManager;
    LinearLayoutManager countryLinearLayoutManager;
    RecyclerView ingradienRecyclerView;
    RecyclerView countryRecyclerView;

    RecyclerView allCategoryRecyclerView;
    AllCatigoriesAdapter allCatigoriesAdapter;
    LinearLayoutManager allCategoriesLayoutManager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initUI(view);
        ingradientLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        searchIngradientsMealAdapter = new SearchIngradientsAdapter(getContext(), new ArrayList<>(), this);
        ingradienRecyclerView.setLayoutManager(ingradientLinearLayoutManager);
        ingradienRecyclerView.setAdapter(searchIngradientsMealAdapter);

        countryLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        searchCountryAdapter = new SearchCountryAdapter(getContext(), new ArrayList<>(), this);
        countryRecyclerView.setLayoutManager(countryLinearLayoutManager);
        countryRecyclerView.setAdapter(searchCountryAdapter);

        allCategoriesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        allCatigoriesAdapter = new AllCatigoriesAdapter(getContext(), new ArrayList<>(), this);
        allCategoryRecyclerView.setLayoutManager(allCategoriesLayoutManager);
        allCategoryRecyclerView.setAdapter(allCatigoriesAdapter);


        searchPresenter = new SearchPresenter(this, Repository.getInstance(RemoteMeal.getInstance(), getContext(), ConcreteLocalSource.getInstance(getContext())));

        searchPresenter.getAllIngradients();
        searchPresenter.getAllCountries();
        searchPresenter.getAllCategories();

        return view;
    }

    @Override
    public void onClick(MealIngradient meal) {

    }

    @Override
    public void countryItemOnClick(String country) {

    }

    @Override
    public void countryOnClick() {

    }

    @Override
    public void categoryItemOnClick() {

    }

    @Override
    public void showIngradientsMeal(List<MealIngradient> mealIngradients) {
        searchIngradientsMealAdapter.setList(mealIngradients);
        searchIngradientsMealAdapter.notifyDataSetChanged();
        System.out.println("hello from ingradient");

    }

    @Override
    public void showCountries(List<MealsCountry> mealsCountries) {
        searchCountryAdapter.setList(mealsCountries);
        searchCountryAdapter.notifyDataSetChanged();
        System.out.println("hello from ingradient");

    }

    @Override
    public void showAllCatigories(List<Category> categories) {
        allCatigoriesAdapter.setList(categories);
        allCatigoriesAdapter.notifyDataSetChanged();
        System.out.println("hello from category");


    }

    private void initUI(View view) {
        ingradienRecyclerView = view.findViewById(R.id.ingradientRecycler);
        countryRecyclerView = view.findViewById(R.id.countryRecycler);
        allCategoryRecyclerView=view.findViewById(R.id.searchCategoryRecycler);
    }
}