package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton; // استيراد ImageButton
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // تعريف زرار الرجوع
        ImageButton btnBack = view.findViewById(R.id.btnBack);

        // لما تدوس عليه
        btnBack.setOnClickListener(v -> {
            // يرجعك للصفحة اللي فاتت
            getParentFragmentManager().popBackStack();
        });

        return view;
    }
}