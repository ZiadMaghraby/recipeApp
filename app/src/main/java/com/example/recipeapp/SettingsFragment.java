package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // I create the view for the settings screen from the layout file.
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // I find the back button from the XML.
        ImageButton btnBack = view.findViewById(R.id.btnBackSettings);

        // This action run when the back button is clicked.
        btnBack.setOnClickListener(v -> {
            // Go back to the screen that was open before this one.
            getParentFragmentManager().popBackStack();
        });

        // IMPORTANT: The switches and radio buttons (dark mode, notifications, language)
        // need codes here for save their status permanently (using SharedPreferences).

        return view;
    }
}