package com.example.recipeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    TextView tvName, tvEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // here we get all views from screen
        tvName = view.findViewById(R.id.tvProfileName);
        tvEmail = view.findViewById(R.id.tvProfileEmail);
        Button btnEdit = view.findViewById(R.id.btnEditProfile);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        ImageButton btnBack = view.findViewById(R.id.btnBackProfile);

        CardView btnFav = view.findViewById(R.id.btnMyFavorites);
        CardView btnShop = view.findViewById(R.id.btnShoppingList);
        CardView btnSettings = view.findViewById(R.id.btnSettings);

        // this update the screen with saved name and email
        updateUI();

        // when user click edit button, we show dialog for edit data
        btnEdit.setOnClickListener(v -> showEditDialog());

        // these buttons open other pages
        btnFav.setOnClickListener(v -> openFragment(new FavoritesFragment()));
        btnShop.setOnClickListener(v -> openFragment(new ShoppingListFragment()));
        btnSettings.setOnClickListener(v -> openFragment(new SettingsFragment()));

        // logout button remove user data and show message
        btnLogout.setOnClickListener(v -> {
            ProfileManager.clearData(getContext()); // delete saved data
            Toast.makeText(getContext(), "Logged Out!", Toast.LENGTH_SHORT).show();
            updateUI(); // refresh screen after logout
        });

        // this back button go to previous page
        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }

    // this method put saved name and email into text views
    private void updateUI() {
        tvName.setText(ProfileManager.getName(getContext()));
        tvEmail.setText(ProfileManager.getEmail(getContext()));
    }

    // this method show dialog to edit name and email
    private void showEditDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Edit Profile");

        // this layout hold inputs inside dialog
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);

        // input for name
        final EditText inputName = new EditText(getContext());
        inputName.setHint("Enter Name");
        inputName.setText(ProfileManager.getName(getContext())); // show current name
        layout.addView(inputName);

        // input for email
        final EditText inputEmail = new EditText(getContext());
        inputEmail.setHint("Enter Email");
        inputEmail.setText(ProfileManager.getEmail(getContext())); // show current email
        layout.addView(inputEmail);

        builder.setView(layout);

        // save button to update data
        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = inputName.getText().toString();
            String newEmail = inputEmail.getText().toString();

            // small check if fields not empty
            if (!newName.isEmpty() && !newEmail.isEmpty()) {

                // save new data in manager
                ProfileManager.saveUserData(getContext(), newName, newEmail);
                updateUI(); // change screen with new data
                Toast.makeText(getContext(), "Profile Updated! ✅", Toast.LENGTH_SHORT).show();
            }
        });

        // cancel button only close dialog
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // this small method open any fragment we want
    private void openFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
