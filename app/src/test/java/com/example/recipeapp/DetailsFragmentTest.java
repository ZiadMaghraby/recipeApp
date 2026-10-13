package com.example.recipeapp;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
@LooperMode(LooperMode.Mode.PAUSED)
public class DetailsFragmentTest {
    private void clickFavoriteWithArguments(Bundle args) {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            FragmentManager manager = controller.get().getSupportFragmentManager();
            manager.executePendingTransactions();
            DetailsFragment details = new DetailsFragment();
            if (args != null) details.setArguments(args);
            manager.beginTransaction().replace(R.id.fragment_container, details).commitNow();
            int before = FavoritesManager.getFavorites().size();
            FloatingActionButton favorite = details.requireView().findViewById(R.id.fabFavorite);
            favorite.performClick();
            assertEquals(before, FavoritesManager.getFavorites().size());
        }
    }

    @Test public void absentArgumentsDoNotAddAFavorite() {
        clickFavoriteWithArguments(null);
    }

    @Test public void missingRecipeNameDoesNotCrash() {
        clickFavoriteWithArguments(new Bundle());
    }

    @Test public void explicitNullRecipeNameDoesNotCrash() {
        Bundle args = new Bundle();
        args.putString("RECIPE_NAME", null);
        clickFavoriteWithArguments(args);
    }

    @Test public void validRecipeStillTogglesFavorite() {
        String name = "Regression Pancakes";
        FavoritesManager.removeRecipe(name);
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            FragmentManager manager = controller.get().getSupportFragmentManager();
            manager.executePendingTransactions();
            DetailsFragment details = new DetailsFragment();
            Bundle args = new Bundle();
            args.putString("RECIPE_NAME", name);
            details.setArguments(args);
            manager.beginTransaction().replace(R.id.fragment_container, details).commitNow();
            FloatingActionButton favorite = details.requireView().findViewById(R.id.fabFavorite);
            favorite.performClick();
            assertTrue(FavoritesManager.isFavorite(name));
            favorite.performClick();
            assertFalse(FavoritesManager.isFavorite(name));
        } finally {
            FavoritesManager.removeRecipe(name);
        }
    }
}
