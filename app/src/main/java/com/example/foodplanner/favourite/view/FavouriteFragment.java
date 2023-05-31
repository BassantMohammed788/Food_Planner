package com.example.foodplanner.favourite.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.category.presenter.CategoryPresenter;
import com.example.foodplanner.category.view.CategoryFragmentArgs;
import com.example.foodplanner.category.view.CategoryMealsAdabter;
import com.example.foodplanner.database.room.ConcreteLocalSource;
import com.example.foodplanner.favourite.presenter.FavouritePresenter;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.RemoteMeal;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavouriteFragmentViewInterface,OnFavouriteClickListener{

    RecyclerView recyclerView;
    FavouriteAdapter adapter;
    FavouritePresenter favoritePresenter;
    LinearLayoutManager layoutManager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favourite, container, false);
        initUI(view);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new FavouriteAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        favoritePresenter = new FavouritePresenter(this, Repository.getInstance(RemoteMeal.getInstance(), getContext(), ConcreteLocalSource.getInstance(getContext())));


        favoritePresenter.getAllFavourites();
        return view;
    }

    @Override
    public void showFavMeals(LiveData<List<Meal>> meals) {
        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {

                adapter.setList(meals);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void deleteMeal(Meal meal) {
        favoritePresenter.deleteFromFav(meal);

    }

    @Override
    public void deleteFromFavoriteOnClick(Meal meal) {
        deleteMeal(meal);
        adapter.notifyDataSetChanged();

    }
    private void initUI(View view) {
        recyclerView = view.findViewById(R.id.favouriteRecycler);
    }
}