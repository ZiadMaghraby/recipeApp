package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 1. تعريف زرار القائمة (ده اللي كان ناقص!)
        CardView btnMenu = view.findViewById(R.id.btnMenu);

        // 2. تشغيله: لما تدوس عليه، ينادي على MainActivity يفتح القائمة
        btnMenu.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        // --- باقي الزراير القديمة ---

        // زرار البروفايل
        CardView btnProfile = view.findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // زرار البحث
        CardView cardSearch = view.findViewById(R.id.cardSearch);
        cardSearch.setOnClickListener(v ->
                Toast.makeText(getContext(), "Searching...", Toast.LENGTH_SHORT).show()
        );

        // زراير التصنيفات
        CardView catBreakfast = view.findViewById(R.id.catBreakfast);
        CardView catLunch = view.findViewById(R.id.catLunch);
        CardView catDessert = view.findViewById(R.id.catDessert);

        catBreakfast.setOnClickListener(v -> openCategory("Breakfast"));
        catLunch.setOnClickListener(v -> openCategory("Lunch"));
        catDessert.setOnClickListener(v -> openCategory("Dessert"));

        // زراير الأكلات
        CardView cardBigPizza = view.findViewById(R.id.cardBigPizza);
        CardView cardBurgerSmall = view.findViewById(R.id.cardBurgerSmall);
        CardView cardPasta = view.findViewById(R.id.cardPasta);

        View.OnClickListener detailsListener = v -> openDetails();
        cardBigPizza.setOnClickListener(detailsListener);
        cardBurgerSmall.setOnClickListener(detailsListener);
        cardPasta.setOnClickListener(detailsListener);

        return view;
    }

    // دالة فتح صفحة التصنيف
    private void openCategory(String categoryName) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString("CATEGORY_NAME", categoryName);
        fragment.setArguments(args);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    // دالة فتح التفاصيل
    private void openDetails() {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DetailsFragment())
                .addToBackStack(null)
                .commit();
    }
}