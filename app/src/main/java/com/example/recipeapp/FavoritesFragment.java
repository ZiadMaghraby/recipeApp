package com.example.recipeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView; // I use this for the delete button icon
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast; // I need this for showing short messages
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class FavoritesFragment extends Fragment {

    // This layout is the container where all favorite cards will be added. I can update him fast.
    LinearLayout containerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ImageButton btnBack = view.findViewById(R.id.btnBackFav);
        // I assume fragment_favorites.xml has a LinearLayout named favoritesContainer
        containerLayout = view.findViewById(R.id.favoritesContainer);

        loadFavorites(); // I call this function to start build the list of favorites.

        // The back button for going to previous screen.
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }

    // This function builds the entire list of favorite cards.
    private void loadFavorites() {
        containerLayout.removeAllViews(); // Clear all old cards before adding new ones.

        // Check if the list of favorites is empty or not.
        if (FavoritesManager.getFavorites().isEmpty()) {
            // If empty, I show a message to the user. This is called 'empty state'.
            TextView emptyText = new TextView(getContext());
            emptyText.setText("No favorites yet 😔");
            emptyText.setTextSize(18);
            emptyText.setGravity(Gravity.CENTER);
            emptyText.setPadding(0, 50, 0, 0);
            containerLayout.addView(emptyText);
        } else {
            // Loop through all saved favorite recipes and call function to make a card for each one.
            for (String recipeName : FavoritesManager.getFavorites()) {
                addFavoriteCard(containerLayout, recipeName);
            }
        }
    }

    // This function creates a CardView programmatically for one favorite recipe.
    private void addFavoriteCard(LinearLayout container, String name) {
        // 1. I create the basic card view.
        CardView card = new CardView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 30);
        card.setLayoutParams(params);
        card.setRadius(30); // Make rounded corners
        card.setCardElevation(8); // Make shadow effect
        card.setContentPadding(30, 30, 30, 30);

        // 2. Horizontal layout inside the card for name and delete button.
        LinearLayout row = new LinearLayout(getContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        // 3. TextView for the recipe name.
        TextView tv = new TextView(getContext());
        tv.setText("🍕 " + name);
        tv.setTextSize(18);
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(null, android.graphics.Typeface.BOLD);
        // This parameter makes the name text take all space and push the delete button to end.
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        tv.setLayoutParams(textParams);

        // 4. Delete button (Trash Icon).
        ImageView btnDelete = new ImageView(getContext());
        btnDelete.setImageResource(android.R.drawable.ic_menu_delete); // I use the delete icon
        btnDelete.setColorFilter(Color.RED); // Make the icon red color
        btnDelete.setPadding(10, 10, 10, 10);

        // Action for delete button when click happen.
        btnDelete.setOnClickListener(v -> {
            FavoritesManager.removeRecipe(name); // Remove the recipe from my data (FavoritesManager).
            container.removeView(card); // Remove the card from the screen immediately.

            // If the list is empty now, I call loadFavorites to show the empty message.
            if(FavoritesManager.getFavorites().isEmpty()) {
                loadFavorites();
            }
            Toast.makeText(getContext(), "Removed!", Toast.LENGTH_SHORT).show();
        });

        // I add the parts to the row and the card to the container.
        row.addView(tv);
        row.addView(btnDelete);
        card.addView(row);
        container.addView(card);

        // Action for when the user clicks anywhere on the recipe card. It open the details screen.
        card.setOnClickListener(v -> {
            DetailsFragment fragment = new DetailsFragment();
            Bundle args = new Bundle();
            args.putString("RECIPE_NAME", name); // I send the name of the recipe
            fragment.setArguments(args);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null) // This is good practice for navigation.
                    .commit();
        });
    }
}