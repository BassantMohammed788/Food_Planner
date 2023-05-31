package com.example.foodplanner.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.database.room.ConcreteLocalSource;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.RemoteMeal;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface, OnHomeClickListener {
    RecyclerView inspirationRecyclerView;
    RecyclerView allCategoryRecyclerView;
    InspirationMealAdapter inspirationMealAdapter;
    AllCatigoriesAdapter allCatigoriesAdapter;
    HomePresenter homePresenter;
    LinearLayoutManager inspirationLayoutManager;
    GridLayoutManager allCategoriesLayoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);

        inspirationLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        inspirationMealAdapter = new InspirationMealAdapter(getContext(), new ArrayList<>(), this);
        inspirationRecyclerView.setLayoutManager(inspirationLayoutManager);
        inspirationRecyclerView.setAdapter(inspirationMealAdapter);
        homePresenter = new HomePresenter(this,Repository.getInstance(RemoteMeal.getInstance(), getContext(), ConcreteLocalSource.getInstance(getContext())));


        allCategoriesLayoutManager = new GridLayoutManager(getContext(), 2);
        allCatigoriesAdapter = new AllCatigoriesAdapter(getContext(), new ArrayList<>(), this);
        allCategoryRecyclerView.setLayoutManager(allCategoriesLayoutManager);
        allCategoryRecyclerView.setAdapter(allCatigoriesAdapter);
        homePresenter.getInspirationMeals();
        homePresenter.getAllCategories();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void showInspirationMeal(List<Meal> meal) {
        inspirationMealAdapter.setList(meal);
        inspirationMealAdapter.notifyDataSetChanged();

    }

    @Override
    public void showAllCategories(List<Category> category) {
        allCatigoriesAdapter.setList(category);
        allCatigoriesAdapter.notifyDataSetChanged();
        System.out.println("hello from category");

    }

    private void initUI(View view) {
        inspirationRecyclerView = view.findViewById(R.id.daily_Inspiration_RecyclerView);
        allCategoryRecyclerView = view.findViewById(R.id.allCategory_RecyclerView);
    }

    @Override
    public void onClick(Meal meal) {

    }
}