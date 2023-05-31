package com.example.foodplanner.Search.View;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.models.MealIngradient;

import java.util.List;

public class SearchIngradientsAdapter extends RecyclerView.Adapter<SearchIngradientsAdapter.ViewHolder> {

    private final Context context;
    private List<MealIngradient> values;

    private OnSearchClickListener listener;
    private static final String TAG = "RecyclerView";

    public SearchIngradientsAdapter(Context context, List<MealIngradient> values, OnSearchClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingradientName;
        public ImageView ingradientPhoto;
        public CardView cardView;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView;

            ingradientName = itemView.findViewById(R.id.ingradIenttextView);
            ingradientPhoto = itemView.findViewById(R.id.ingradientImageView);
            cardView = itemView.findViewById(R.id.ingradientCard);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.ingradient_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ingradientName.setText(values.get(position).getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+values.get(position).getStrIngredient()+".png")
                .apply(new RequestOptions().override(200,200)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.ingradientPhoto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SearchFragmentDirections.ActionSearchFragmentToIngradientFragment action =
                        SearchFragmentDirections.actionSearchFragmentToIngradientFragment(values.get(position).getStrIngredient());
                Navigation.findNavController(view).navigate(action);
            }
        });

    }

    public int getItemCount() {
        return values.size();
    }
    public void setList(List<MealIngradient> updateList)
    {
        this.values=updateList;
    }
   }
