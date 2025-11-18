package com.example.recipeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Bind Views
        TextView tvName = view.findViewById(R.id.tvProfileNameDisplay);
        TextView tvEmail = view.findViewById(R.id.tvProfileEmailDisplay);
        EditText etPassword = view.findViewById(R.id.etProfilePassword);
        Button btnUpdate = view.findViewById(R.id.btnUpdatePassword);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        ImageButton btnBack = view.findViewById(R.id.btnBackProfile);

        // Bind the clickable rows (LinearLayouts)
        View btnFav = view.findViewById(R.id.btnGoToFavorites);
        View btnShop = view.findViewById(R.id.btnGoToShopping);
        View btnAdd = view.findViewById(R.id.btnGoToAddRecipe);

        // Load Data
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String savedName = sharedPreferences.getString("saved_name", "Chef Name");
        String savedEmail = sharedPreferences.getString("saved_email", "chef@email.com");

        tvName.setText(savedName);
        tvEmail.setText(savedEmail);

        // Password Update Logic
        btnUpdate.setOnClickListener(v -> {
            String newPass = etPassword.getText().toString();
            if (newPass.length() < 6) {
                etPassword.setError("Min 6 chars");
                return;
            }
            sharedPreferences.edit().putString("saved_password", newPass).apply();
            Toast.makeText(getContext(), "Password Updated!", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
        });

        // Navigation Logic
        btnFav.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FavoritesFragment())
                        .addToBackStack(null)
                        .commit()
        );

        btnShop.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ShoppingListFragment())
                        .addToBackStack(null)
                        .commit()
        );

        btnAdd.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AddRecipeFragment())
                        .addToBackStack(null)
                        .commit()
        );

        // Logout Logic
        btnLogout.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
            Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
        });

        // Back Button Logic
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }
}