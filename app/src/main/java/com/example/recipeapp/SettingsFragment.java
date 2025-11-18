package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton; // استيراد مهم
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // تعريف وتشغيل زرار الرجوع
        ImageButton btnBack = view.findViewById(R.id.btnBackSettings);
        btnBack.setOnClickListener(v -> {
            // بيرجعك لآخر صفحة كنت فاتحها (سواء كانت Home أو القائمة)
            getParentFragmentManager().popBackStack();
        });

        return view;
    }
}