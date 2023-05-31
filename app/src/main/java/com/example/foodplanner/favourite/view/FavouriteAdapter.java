package com.example.foodplanner.favourite.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meal;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private OnFavouriteClickListener listener;
    private static final String TAG = "RecyclerView";

    public FavouriteAdapter(Context context, List<Meal> values, OnFavouriteClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView meal;
        public ImageView mealPhoto;
        public View layout;

        ImageView delBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            mealPhoto = itemView.findViewById(R.id.image_meal_fav);
            meal = itemView.findViewById(R.id.mealName_fav);
            delBtn = itemView.findViewById(R.id.delete_fav);
        }
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.favourite_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.meal.setText(values.get(position).getStrMeal());


        Glide.with(context).load(values.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.mealPhoto.getWidth(), holder.mealPhoto.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealPhoto);

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteFromFavoriteOnClick(values.get(position));
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



