package com.example.foodplanner.ingradients.view;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.foodplanner.country.view.CountryFragmentDirections;
import com.example.foodplanner.country.view.CountryMealsAdapter;
import com.example.foodplanner.models.Meal;

import java.util.List;

public class MealsByIngradientAdapter extends RecyclerView.Adapter<MealsByIngradientAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private OnIngradientClickListener listener;
    private static final String TAG = "RecyclerView";

    public MealsByIngradientAdapter(Context context, List<Meal> values, OnIngradientClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView meal;
        public ImageView mealPhoto;
        public CardView cardView;
        public View layout;
        public Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            meal = itemView.findViewById(R.id.categoryMeal_title_text_view);
            mealPhoto = itemView.findViewById(R.id.category_meal_image_view);
            cardView = itemView.findViewById(R.id.category_meal_card);
        }
    }

    @NonNull
    @Override
    public MealsByIngradientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.meal_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsByIngradientAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.meal.setText(values.get(position).getStrMeal());

        Glide.with(context).load(values.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.mealPhoto.getWidth(), holder.mealPhoto.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealPhoto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngradientFragmentDirections.ActionIngradientFragmentToMealDetailsFragment action =
                        IngradientFragmentDirections.actionIngradientFragmentToMealDetailsFragment(values.get(position).getStrMeal());
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public void setList(List<Meal> updateList) {
        this.values = updateList;
    }


}
