package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import java.util.Calendar; // for time

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // this part show greeting depend on time of day
        TextView tvGreeting = view.findViewById(R.id.tvGreeting);
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        // here I check what time now and change text
        if (timeOfDay >= 0 && timeOfDay < 12) {
            tvGreeting.setText("Good Morning,");
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            tvGreeting.setText("Good Afternoon,");
        } else {
            tvGreeting.setText("Good Evening,");
        }

        // this banner is new and open details when user click
        CardView bannerDaily = view.findViewById(R.id.bannerDaily);
        bannerDaily.setOnClickListener(v -> openDetails("Spicy Tacos"));

        // this button open the drawer menu in main activity
        CardView btnMenu = view.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) ((MainActivity) getActivity()).openDrawer();
        });

        // this button go to profile fragment
        CardView btnProfile = view.findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .addToBackStack(null).commit()
        );

        // here I open categories when user click on any one
        view.findViewById(R.id.catBreakfast).setOnClickListener(v -> openCategory("Breakfast"));
        view.findViewById(R.id.catLunch).setOnClickListener(v -> openCategory("Lunch"));
        view.findViewById(R.id.catDessert).setOnClickListener(v -> openCategory("Dessert"));

        // this is trending items, user click to see more info
        CardView trend1 = view.findViewById(R.id.cardTrend1);
        trend1.setOnClickListener(v -> openDetails("Super Supreme Pizza"));

        CardView trend2 = view.findViewById(R.id.cardTrend2);
        trend2.setOnClickListener(v -> openDetails("Cheese Burger"));

        return view;
    }

    // this method open category fragment with name
    private void openCategory(String categoryName) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString("CATEGORY_NAME", categoryName);
        fragment.setArguments(args);

        // here I change the screen to category fragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit();
    }

    // this method open the recipe details
    private void openDetails(String recipeName) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString("RECIPE_NAME", recipeName);
        fragment.setArguments(args);

        // this replace screen and show details fragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit();
    }
}
