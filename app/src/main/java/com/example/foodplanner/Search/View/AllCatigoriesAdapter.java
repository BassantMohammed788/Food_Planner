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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Category;

import java.util.List;

public class AllCatigoriesAdapter extends RecyclerView.Adapter<AllCatigoriesAdapter.ViewHolder> {

    private final Context context;
    private List<Category> values;
    private OnSearchClickListener listener;
    private static final String TAG = "RecyclerView";

    public AllCatigoriesAdapter(Context context, List <Category> values, OnSearchClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public ImageView mealPhoto;
        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;

            category = itemView.findViewById(R.id.categoryName);
            mealPhoto = itemView.findViewById(R.id.imageCategory);
            cardView = itemView.findViewById(R.id.cardCategory);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.activity_category_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.category.setText(values.get(position).getStrCategory());
        Glide.with(context)
                .load(values.get(position).getStrCategoryThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealPhoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "hello from adapter", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public void setList(List<Category> updateList)
    {
        this.values=updateList;
    }
}

