package com.example.recipeapp;

import android.content.Context;
import android.content.SharedPreferences; // this is for saving small data
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

public class LoginFragment extends Fragment {

    private final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final String PASSWORD_PATTERN = "^.{6,}$";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText etEmail = view.findViewById(R.id.etLoginEmail);
        EditText etPassword = view.findViewById(R.id.etLoginPassword);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        Button btnGoToRegister = view.findViewById(R.id.btnGoToRegister);

        // when user click login button
        btnLogin.setOnClickListener(v -> {
            String inputEmail = etEmail.getText().toString().trim();
            String inputPassword = etPassword.getText().toString().trim();

            // this part check if email and pass is write format
            if (!inputEmail.matches(EMAIL_PATTERN)) {
                etEmail.setError("Invalid Email Format");
                return;
            }
            if (!inputPassword.matches(PASSWORD_PATTERN)) {
                etPassword.setError("Password too short");
                return;
            }

            // here we start the login check with saved data

            // open small storage where we save user data
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

            // read data from storage, if no data return empty string
            String savedEmail = sharedPreferences.getString("saved_email", "");
            String savedPassword = sharedPreferences.getString("saved_password", "");

            // now we compare input email and password with saved one
            if (inputEmail.equals(savedEmail) && inputPassword.equals(savedPassword)) {
                // user is real and data is correct
                Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                // go to home screen
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();

            } else {
                // data is wrong or user not exist
                Toast.makeText(getContext(), "Wrong Email or Password!", Toast.LENGTH_LONG).show();
            }
        });

        // this button go to register page
        btnGoToRegister.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
