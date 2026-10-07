package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // I make the view from my layout file.
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        TextView tvTitle = view.findViewById(R.id.tvCategoryTitle);

        // I find the TextViews for the items and the back button.
        TextView tvItem1 = view.findViewById(R.id.tvItem1Name);
        TextView tvItem2 = view.findViewById(R.id.tvItem2Name);
        TextView tvItem3 = view.findViewById(R.id.tvItem3Name);
        TextView tvItem4 = view.findViewById(R.id.tvItem4Name);
        ImageButton btnBack = view.findViewById(R.id.btnBackCat);

        // Get arguments sent from the previous screen (e.g. HomeFragment).
        Bundle args = getArguments();
        if (args != null) {
            // I read the category name that was sent to me.
            String category = args.getString("CATEGORY_NAME");
            tvTitle.setText(category + " Menu"); // I set the screen title here.

            // --- This code decides which recipes names to show based on the category sent. ---
            if (category.equals("Breakfast")) {
                tvItem1.setText("Pancakes & Honey");
                tvItem2.setText("Omelette Sandwich");
                tvItem3.setText("Falafel Plate");
                tvItem4.setText("Cheese Toast");

            } else if (category.equals("Lunch")) {
                tvItem1.setText("Grilled Chicken");
                tvItem2.setText("Beef Steak");
                tvItem3.setText("Pasta Bechamel");
                tvItem4.setText("Mixed Grill");

            } else if (category.equals("Dessert")) {
                tvItem1.setText("Chocolate Cake");
                tvItem2.setText("Ice Cream");
                tvItem3.setText("Cheese Cake");
                tvItem4.setText("Fruit Salad");
            }
        }

        // --- I set the action for when the user click on a recipe name. ---
        // When click happen, the item name is send to the details screen.
        tvItem1.setOnClickListener(v -> openDetails(tvItem1.getText().toString()));
        tvItem2.setOnClickListener(v -> openDetails(tvItem2.getText().toString()));
        tvItem3.setOnClickListener(v -> openDetails(tvItem3.getText().toString()));
        tvItem4.setOnClickListener(v -> openDetails(tvItem4.getText().toString()));

        // The back button for going to previous screen.
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }

    // This function is for opening the recipe details screen.
    private void openDetails(String mealName) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        // I put the meal name into the arguments bundle.
        args.putString("RECIPE_NAME", mealName);
        fragment.setArguments(args);

        // Start the transaction for change the screen (fragment).
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment) // Replace the current fragment with DetailsFragment.
                .addToBackStack(null) // This is important for enabling the back button on phone.
                .commit();
    }
}