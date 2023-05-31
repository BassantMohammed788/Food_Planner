package com.example.foodplanner.meal_details.view;

import static java.lang.Boolean.TRUE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.country.presenter.CountryMealsPresenter;
import com.example.foodplanner.country.view.CountryFragmentArgs;
import com.example.foodplanner.country.view.CountryMealsAdapter;
import com.example.foodplanner.database.room.ConcreteLocalSource;
import com.example.foodplanner.home.view.HomeFragment;
import com.example.foodplanner.meal_details.presenter.MealDetailsPresenter;
import com.example.foodplanner.models.Ingredient;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.RemoteMeal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsFragment extends Fragment implements MealDetailsViewInterface,OnMealDetailsClickListener{

    TextView mealdetailsName,mealSteps;
    ImageView MealPhoto;
    RecyclerView ingradientRecycler;

    IngradientAdapter ingradientAdapter;
    LinearLayoutManager IngradientLinearLayoutManager;
    MealDetailsPresenter mealDetailsPresenter;

    List<Ingredient> ingredientList= new ArrayList<Ingredient>();
    Meal model;

    YouTubePlayerView videoView ;
    String[] videoSplit;
    String videoId;

    String mealName;

    ImageButton addToFavBtn , addToPlanBtn;
    boolean flag= true;

    public Meal getModel() {
        return model;
    }

    public void setModel(Meal model) {
        this.model = model;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_meal_details, container, false);
        initUI(view);

        mealName = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName();
        if (mealName != null) {
            mealdetailsName.setText(mealName);
        }


        mealDetailsPresenter = new MealDetailsPresenter(this,Repository.getInstance(RemoteMeal.getInstance(), getContext(), ConcreteLocalSource.getInstance(getContext())));

        mealDetailsPresenter.getMealDetails(mealName);
      /* addToFavBtn.setOnClickListener(event -> {
            addToFavouriteOnClick(getModel());
        });*/

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void ViewMealDetails(List<Meal> meal) {

        model=meal.get(0);
        setModel(model);

        flag=false;
        if(!model.getStrYoutube().equals(""))
        {
            videoSplit =model.getStrYoutube().split("=");
            videoId =videoSplit[1];
        }else{
            videoId =" ";
        }

        mealdetailsName.setText(model.getStrMeal());
        mealSteps.setText(model.getStrInstructions());
        Glide.with(this).load(model.getStrMealThumb())
                .apply(new RequestOptions().override(MealPhoto.getWidth(),MealPhoto.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(MealPhoto);


        videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(videoId, 0);
            }
        });



        if(model.getStrIngredient1()!="")
            ingredientList.add(new Ingredient(model.getStrIngredient1(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient1()+".png"));
        if(!model.getStrIngredient2().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient2(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient2()+".png"));
        if(!model.getStrIngredient3().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient3(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient3()+".png"));
        if(!model.getStrIngredient4().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient4(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient4()+".png"));
        if(!model.getStrIngredient5().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient5(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient5()+".png"));
        if(!model.getStrIngredient6().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient6(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient6()+".png"));
        if(!model.getStrIngredient7().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient7(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient7()+".png"));
        if(!model.getStrIngredient8().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient8(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient8()+".png"));
        if(!model.getStrIngredient9().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient9(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient9()+".png"));
        if(!model.getStrIngredient10().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient10(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient10()+".png"));
        if(!model.getStrIngredient11().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient11(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient11()+".png"));
        if(!model.getStrIngredient12().equals(""))
            ingredientList.add(new Ingredient(model.getStrIngredient12(),"https://www.themealdb.com/images/ingredients/"+model.getStrIngredient12()+".png"));

        IngradientLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ingradientAdapter = new IngradientAdapter(getContext(), ingredientList, this);
        ingradientAdapter.notifyDataSetChanged();
        ingradientRecycler.setLayoutManager(IngradientLinearLayoutManager);
        ingradientRecycler.setAdapter(ingradientAdapter);

    }

    @Override
    public void addMealToPlan(Meal meal) {

    }

    @Override
    public void addToFavorite(Meal meal) {
        mealDetailsPresenter.insertFavourite(meal);

    }
    private void initUI(View view) {
        ingradientRecycler = view.findViewById(R.id.ingredientRecyclerView);
        mealdetailsName = view.findViewById(R.id.mealDetailsMealNamee);
        MealPhoto = view.findViewById(R.id.MealImageIngradient);
        mealSteps = view.findViewById(R.id.itemPageMealSteps);
        videoView = view.findViewById(R.id.videoView);
        addToFavBtn = view.findViewById(R.id.addToFavBtn);
        addToPlanBtn=view.findViewById(R.id.addToPlanBtn);
    }

    @Override
    public void addToFavouriteOnClick(Meal meal) {
        if (meal != null) {
            addToFavorite(meal);
            Toast.makeText(getContext(), "Meal is added to favorite", Toast.LENGTH_SHORT).show();
        }
    }
}