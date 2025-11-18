package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        TextView tvTitle = view.findViewById(R.id.tvCategoryTitle);
        TextView tvItem1 = view.findViewById(R.id.tvItem1Name);
        TextView tvItem2 = view.findViewById(R.id.tvItem2Name);
        ImageButton btnBack = view.findViewById(R.id.btnBackCat);

        // 1. استقبال البيانات من الصفحة الرئيسية
        Bundle args = getArguments();
        if (args != null) {
            String category = args.getString("CATEGORY_NAME");
            tvTitle.setText(category + " Menu"); // تغيير العنوان

            // 2. تغيير الأكلات بناء على القسم
            if (category.equals("Breakfast")) {
                tvItem1.setText("Pancakes & Honey");
                tvItem2.setText("Omelette Sandwich");
            } else if (category.equals("Lunch")) {
                tvItem1.setText("Grilled Chicken");
                tvItem2.setText("Beef Steak");
            } else if (category.equals("Dessert")) {
                tvItem1.setText("Chocolate Cake");
                tvItem2.setText("Ice Cream");
            }
        }

        // زرار الرجوع
        btnBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        return view;
    }
}