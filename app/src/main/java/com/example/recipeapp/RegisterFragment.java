package com.example.recipeapp;

import android.content.Context;
import android.content.SharedPreferences; // I import this library for saving data permanently
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    // I make patterns for check if email and password are correct format.
    private final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final String PASSWORD_PATTERN = "^.{6,}$"; // Password must be 6 characters minimum

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // I find all input fields and buttons from the layout.
        EditText etName = view.findViewById(R.id.etRegName);
        EditText etEmail = view.findViewById(R.id.etRegEmail);
        EditText etPassword = view.findViewById(R.id.etRegPassword);
        Button btnRegister = view.findViewById(R.id.btnRegister);
        Button btnBackToLogin = view.findViewById(R.id.btnBackToLogin);

        // This action happens when the user clicks the SIGN UP button.
        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Data Validation: I check if data is correct.
            if (name.isEmpty()) { etName.setError("Name is required"); return; } // Name cannot be empty
            if (!email.matches(EMAIL_PATTERN)) { etEmail.setError("Invalid Email"); return; } // Check email format
            if (!password.matches(PASSWORD_PATTERN)) { etPassword.setError("Password too short"); return; } // Check password length

            // --- The new part: Saving Data Permanently ---

            // 1. I get the shared preferences file named "UserData".
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

            // 2. I get the editor (the 'pen') to start writing data.
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // 3. I write the new user data (email, password, name).
            editor.putString("saved_email", email);
            editor.putString("saved_password", password);
            editor.putString("saved_name", name);

            // 4. I save the changes permanently.
            editor.apply();

            // I show a success message to user.
            Toast.makeText(getContext(), "Account Created! Please Login.", Toast.LENGTH_SHORT).show();

            // I navigate to the Login screen after successful registration.
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        });

        // This button navigates the user directly to the Login screen.
        btnBackToLogin.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        });

        return view;
    }
}