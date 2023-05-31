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
import com.example.foodplanner.home.view.HomeFragmentDirections;
import com.example.foodplanner.models.MealIngradient;
import com.example.foodplanner.models.MealsCountry;

import java.util.List;

public class SearchCountryAdapter extends RecyclerView.Adapter<SearchCountryAdapter.ViewHolder> {

    private final Context context;
    private List<MealsCountry> values;
    private String[] countriesFlags;
    private OnSearchClickListener searchClickListener;
    private static final String TAG = "SearchCountryAdapter";

    public SearchCountryAdapter(Context context, List<MealsCountry> values, OnSearchClickListener searchClickListener) {
        this.context = context;
        this.values = values;
        countriesFlags = context.getResources().getStringArray(R.array.flags);
        this.searchClickListener = searchClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView country;
        public ImageView countryPhoto;
        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;

            country = itemView.findViewById(R.id.categoryName);
            countryPhoto = itemView.findViewById(R.id.imageCategory);
            cardView = itemView.findViewById(R.id.cardCategory);
        }
    }

    @NonNull
    @Override
    public SearchCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.activity_category_item, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCountryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(countriesFlags[position])
                .apply(new RequestOptions().override(150, 150)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.countryPhoto);
        holder.country.setText(values.get(position).getStrArea());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SearchFragmentDirections.ActionSearchFragmentToCountryFragment action =
                        SearchFragmentDirections.actionSearchFragmentToCountryFragment(values.get(position).getStrArea());
                Navigation.findNavController(view).navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }


    public void setList(List<MealsCountry> updateList) {
        this.values = updateList;
    }


}
