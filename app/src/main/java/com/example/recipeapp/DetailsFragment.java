package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    String currentRecipeName = ""; // This variable hold the name of recipe I see now
    FloatingActionButton fab; // This button is the heart icon (Favorite button)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // I create the view for the details screen
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // I link the views from the XML layout to my code
        TextView tvName = view.findViewById(R.id.tvRecipeName);
        TextView tvIngreds = view.findViewById(R.id.tvIngredients);
        TextView tvSteps = view.findViewById(R.id.tvInstructions);
        ImageButton btnBack = view.findViewById(R.id.btnBackDetails);
        fab = view.findViewById(R.id.fabFavorite);

        // I try to get data that was sent to me from previous screen
        Bundle args = getArguments();
        if (args != null) {
            // I read the recipe name from the bundle
            currentRecipeName = args.getString("RECIPE_NAME");

            if(currentRecipeName != null) {
                tvName.setText(currentRecipeName);
                updateFabIcon(); // I call this function for show heart is full or empty

                // I call the function that puts ingredients and steps on the screen
                loadRecipeData(currentRecipeName, tvIngreds, tvSteps);
            }
        }

        // Action for the Favorite button (FAB). This code run when user click the heart.
        fab.setOnClickListener(v -> {
            if (!currentRecipeName.isEmpty()) {
                // Check if this recipe is already favorite. I use FavoritesManager class.
                if (FavoritesManager.isFavorite(currentRecipeName)) {
                    FavoritesManager.removeRecipe(currentRecipeName); // I remove it from favorites
                    Toast.makeText(getContext(), "Removed 💔", Toast.LENGTH_SHORT).show();
                } else {
                    FavoritesManager.addRecipe(currentRecipeName); // I add it to favorites
                    Toast.makeText(getContext(), "Added ❤️", Toast.LENGTH_SHORT).show();
                }
                updateFabIcon(); // I change the heart icon to be full or empty
            }
        });

        // The back button for going to previous screen.
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }

    // This function changes the icon of the Favorite button (heart).
    private void updateFabIcon() {
        // If it is favorite, show the star filled.
        if (FavoritesManager.isFavorite(currentRecipeName)) {
            fab.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            // If it is not favorite, show the star empty.
            fab.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }

    // --- This big function has all the recipe data (ingredients and steps). ---
    // It finds the correct data based on the recipe name I give it.
    private void loadRecipeData(String name, TextView tvIngreds, TextView tvSteps) {

        // === Breakfast Recipes ===
        if (name.contains("Pancakes")) {
            tvIngreds.setText("• 1 cup Flour\n• 1 cup Milk\n• 1 Egg\n• Honey & Butter");
            tvSteps.setText("1. Mix flour, milk, and egg.\n2. Pour into hot pan.\n3. Flip when bubbly.\n4. Serve with honey.");
        }
        else if (name.contains("Omelette")) {
            // Ingredients for Omelette recipe.
            tvIngreds.setText("• 3 Eggs\n• Salt & Pepper\n• Cheese\n• Green Peppers");
            // Steps for Omelette recipe.
            tvSteps.setText("1. Beat eggs well.\n2. Pour in pan with butter.\n3. Add cheese and peppers.\n4. Fold and serve.");
        }
        // ... (other recipes use same logic) ...

        // === Lunch Recipes ===
        else if (name.contains("Grilled Chicken")) {
            // Ingredients for Grilled Chicken recipe.
            tvIngreds.setText("• Chicken Breast\n• Lemon Juice\n• Garlic & Herbs\n• Olive Oil");
            // Steps for Grilled Chicken recipe.
            tvSteps.setText("1. Marinate chicken for 2 hours.\n2. Grill on medium heat.\n3. Serve with rice.");
        }
        // ... (other recipes use same logic) ...

        // === Dessert Recipes ===
        else if (name.contains("Cake")) {
            // This condition is true for both Chocolate Cake and Cheese Cake.
            tvIngreds.setText("• Flour & Sugar\n• Cocoa Powder\n• Eggs & Milk\n• Baking Powder");
            tvSteps.setText("1. Mix dry ingredients.\n2. Add wet ingredients.\n3. Bake at 180°C for 40 mins.");
        }
        // ... (other recipes use same logic) ...

        // === Trending Recipes ===
        else if (name.contains("Pizza")) {
            tvIngreds.setText("• Dough\n• Tomato Sauce\n• Mozzarella\n• Pepperoni");
            tvSteps.setText("1. Roll dough.\n2. Add toppings.\n3. Bake at 220°C.");
        }
        // ... (other recipes use same logic) ...

        // If the recipe name is not found in the list, show this default message.
        else {
            tvIngreds.setText("• Secret Ingredients");
            tvSteps.setText("Recipe details coming soon!");
        }
    }
}