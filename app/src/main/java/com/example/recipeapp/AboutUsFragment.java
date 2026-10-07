package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton; //   this import for the back button
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class AboutUsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // This function is where the fragment view is created.
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        //  the back button found from the layout file.
        ImageButton btnBack = view.findViewById(R.id.btnBackAbout);

        // Set action for button click. When click happen, this code run.
        btnBack.setOnClickListener(v -> {
            // Go back to the last screen (fragment). It remove this fragment from stack.
            getParentFragmentManager().popBackStack();
        });

        //  return the finished view to show it on screen.
        return view;
    }
}