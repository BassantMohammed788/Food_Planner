package com.example.foodplanner.meal_details.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Ingredient;

import java.util.List;

public class IngradientAdapter extends RecyclerView.Adapter<IngradientAdapter.ViewHolder> {

    private final Context context;
    private List<Ingredient> values;
    private OnMealDetailsClickListener listener;
    private static final String TAG = "RecyclerView";

    public IngradientAdapter(Context context, List<Ingredient> values, OnMealDetailsClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public View layout;
        public TextView ingradientName;
        public ImageView ingradientPhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ingradientName=itemView.findViewById(R.id.ingradIenttextView);
            ingradientPhoto=itemView.findViewById(R.id.ingradientImageView);
        }
    }
    @NonNull
    @Override
    public IngradientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.ingradient_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngradientAdapter.ViewHolder holder, int position) {
        holder.ingradientName.setText(values.get(position).getName());

        Glide.with(context).load(values.get(position).getImg())
            .apply(new RequestOptions().override(holder.ingradientPhoto.getWidth(),holder.ingradientPhoto.getHeight()))
            .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ingradientPhoto);

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public void setList(List<Ingredient> updateList)
    {
        this.values=updateList;
    }

}
