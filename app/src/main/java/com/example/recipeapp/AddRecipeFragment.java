package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast; // I need this for showing short messages
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddRecipeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // This function make the view from XML file.
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        // I find the three buttons on the screen: Save, Cancel, and Back arrow.
        Button btnSave = view.findViewById(R.id.btnSaveRecipe);
        Button btnCancel = view.findViewById(R.id.btnCancelAdd);

        // I define the new back button.
        ImageButton btnBack = view.findViewById(R.id.btnBackAddRecipe);

        // This action run when the user press Save button.
        btnSave.setOnClickListener(v -> {
            // Show a short message to user that the recipe is saved.
            Toast.makeText(getContext(), "Recipe Saved Successfully!", Toast.LENGTH_SHORT).show();
            // After save finish, I go back to the last screen.
            getParentFragmentManager().popBackStack();
        });

        // This button is for cancel. It just go back to previous screen.
        btnCancel.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        // The back arrow button do the same thing: go back to previous screen.
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        // I return the view to show it on screen.
        return view;
    }
}