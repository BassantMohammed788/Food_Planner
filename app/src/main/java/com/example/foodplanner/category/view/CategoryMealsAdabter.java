package com.example.foodplanner.category.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.home.view.HomeFragmentDirections;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;

import java.util.List;

public class CategoryMealsAdabter extends RecyclerView.Adapter<CategoryMealsAdabter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private OnCategoriesClickListenter listener;
    private static final String TAG = "RecyclerView";

    public CategoryMealsAdabter(Context context, List<Meal> values, OnCategoriesClickListenter listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView meal;
        public ImageView mealPhoto;
        public CardView cardView;
        public View layout;

        ImageView favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            meal = itemView.findViewById(R.id.categoryMeal_title_text_view);
            mealPhoto = itemView.findViewById(R.id.category_meal_image_view);
            cardView = itemView.findViewById(R.id.category_meal_card);
            favBtn = itemView.findViewById(R.id.add_to_favourite_btn);

        }
    }
    @NonNull
    @Override
    public CategoryMealsAdabter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.meal_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMealsAdabter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder.meal != null) {
            holder.meal.setText(values.get(position).getStrMeal());
        }

        Glide.with(context).load(values.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.mealPhoto.getWidth(),holder.mealPhoto.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealPhoto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryFragmentDirections.ActionCategoryFragmentToMealDetailsFragment action=
                        CategoryFragmentDirections.actionCategoryFragmentToMealDetailsFragment(values.get(position).getStrMeal());
                Navigation.findNavController(v).navigate(action);
            }
        });
            holder.favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.addToFavoriteOnClick(values.get(position));
                    Log.i(TAG, "onClick: adapter");
                }
            });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public void setList(List<Meal> updateList)
    {
        this.values=updateList;
    }
}

