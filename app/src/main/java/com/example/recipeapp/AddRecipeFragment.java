package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddRecipeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        Button btnSave = view.findViewById(R.id.btnSaveRecipe);
        Button btnCancel = view.findViewById(R.id.btnCancelAdd);

        // تعريف زرار الرجوع الجديد
        ImageButton btnBack = view.findViewById(R.id.btnBackAddRecipe);

        btnSave.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Recipe Saved Successfully!", Toast.LENGTH_SHORT).show();
            getParentFragmentManager().popBackStack();
        });

        // زرار الإلغاء (بيرجع للخلف)
        btnCancel.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        // زرار السهم (بيرجع للخلف برضه)
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }
}