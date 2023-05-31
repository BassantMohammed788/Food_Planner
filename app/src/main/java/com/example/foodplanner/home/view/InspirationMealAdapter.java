package com.example.foodplanner.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.home.view.HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meal;

import java.util.List;

public class InspirationMealAdapter extends RecyclerView.Adapter<InspirationMealAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private OnHomeClickListener listener;
    private static final String TAG = "RecyclerView";

    public InspirationMealAdapter(Context context, List <Meal> values, OnHomeClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView category;

        public ImageView mealPhoto;
        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;

            title = itemView.findViewById(R.id.inspiration_title_text_view);
            category = itemView.findViewById(R.id.inspiration_category_text_view);
            mealPhoto = itemView.findViewById(R.id.inspiration_image_view);
            cardView = itemView.findViewById(R.id.inspiration_card_view);

        }
    }

    @NonNull
    @Override
    public InspirationMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.inspiration_meal_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InspirationMealAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(values.get(position).getStrMeal());
        holder.category.setText(values.get(position).getStrCategory());
        Glide.with(context)
                .load(values.get(position).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealPhoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action=
                        HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(values.get(position).getStrMeal());
                Navigation.findNavController(view).navigate(action);

                Toast.makeText(context, values.get(position).getStrMeal(), Toast.LENGTH_SHORT).show();
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
