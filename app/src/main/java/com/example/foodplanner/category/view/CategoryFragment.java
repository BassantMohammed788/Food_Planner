package com.example.foodplanner.category.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.SearchPresenter;
import com.example.foodplanner.Search.View.AllCatigoriesAdapter;
import com.example.foodplanner.Search.View.SearchIngradientsAdapter;
import com.example.foodplanner.category.presenter.CategoryPresenter;
import com.example.foodplanner.database.room.ConcreteLocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.RemoteMeal;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoriesViewInterface,OnCategoriesClickListenter {
    RecyclerView allCategoryMealRecyclerView;
    CategoryMealsAdabter categoryMealsAdabter;
    LinearLayoutManager allCategoriesMealLayoutManager;
    CategoryPresenter categoryPresenter;
    String categoryMealName;
    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        initUI(view);
        allCategoriesMealLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        categoryMealsAdabter = new CategoryMealsAdabter(getContext(), new ArrayList<>(), this);
        allCategoryMealRecyclerView.setLayoutManager(allCategoriesMealLayoutManager);
        allCategoryMealRecyclerView.setAdapter(categoryMealsAdabter);
        categoryPresenter = new CategoryPresenter(this, Repository.getInstance(RemoteMeal.getInstance(), getContext(), ConcreteLocalSource.getInstance(getContext())));

        categoryMealName = CategoryFragmentArgs.fromBundle(getArguments()).getCategoryName();
        if (categoryMealName != null) {
            textView.setText(categoryMealName);
        }
        categoryPresenter.getAllCategoryMeals(categoryMealName);
        return view;
    }

    @Override
    public void ViewCategoriesMeal(List<Meal> meals) {
        categoryMealsAdabter.setList(meals);
        categoryMealsAdabter.notifyDataSetChanged();
    }

    @Override
    public void addToFavorite(Meal meal) {
        categoryPresenter.addToFav(meal);
        Log.i("TAG", "addToFavorite: category presenter");
    }

    @Override
    public void addToFavoriteOnClick(Meal meal) {
        addToFavorite(meal);
        Toast.makeText(getContext(),"Added successfully to favourite",Toast.LENGTH_LONG).show();
    }
    private void initUI(View view) {
        allCategoryMealRecyclerView = view.findViewById(R.id.categoryMealRecycler);
        textView = view.findViewById(R.id.categoryNameCategory);
    }
}