package com.example.recipeapp;

import java.util.ArrayList;
import java.util.List;

// This class is the manager for all favorite recipes. It holds the list in memory.
public class FavoritesManager {
    // This list holds the name of all recipes that user favorites.
    // IMPORTANT: When app close, this list empty! Need database for save data forever.
    private static final List<String> favoriteRecipes = new ArrayList<>();

    // Function for adding a recipe to the favorite list.
    public static void addRecipe(String name) {
        // I check first if the recipe is not already in the list.
        if (!favoriteRecipes.contains(name)) {
            favoriteRecipes.add(name);
        }
    }

    // Function for removing a recipe from the favorite list.
    public static void removeRecipe(String name) {
        favoriteRecipes.remove(name);
    }

    // Function for check if a recipe is a favorite or not.
    public static boolean isFavorite(String name) {
        return favoriteRecipes.contains(name);
    }

    // Function for getting the complete list of favorite recipes.
    public static List<String> getFavorites() {
        return favoriteRecipes;
    }
}