package com.example.recipeapp;

import android.content.Context;
import android.content.SharedPreferences; // استيراد مكتبة الحفظ
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

    private final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final String PASSWORD_PATTERN = "^.{6,}$";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        EditText etName = view.findViewById(R.id.etRegName);
        EditText etEmail = view.findViewById(R.id.etRegEmail);
        EditText etPassword = view.findViewById(R.id.etRegPassword);
        Button btnRegister = view.findViewById(R.id.btnRegister);
        Button btnBackToLogin = view.findViewById(R.id.btnBackToLogin);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // التحقق من البيانات (Validation)
            if (name.isEmpty()) { etName.setError("Name is required"); return; }
            if (!email.matches(EMAIL_PATTERN)) { etEmail.setError("Invalid Email"); return; }
            if (!password.matches(PASSWORD_PATTERN)) { etPassword.setError("Password too short"); return; }

            // --- الجزء الجديد: حفظ البيانات (Saving Data) ---

            // 1. بنفتح "النوتة" اللي اسمها UserData
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

            // 2. بنجيب "قلم" عشان نكتب بيه (Editor)
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // 3. بنكتب البيانات
            editor.putString("saved_email", email);
            editor.putString("saved_password", password);
            editor.putString("saved_name", name);

            // 4. بنحفظ ونقفل النوتة
            editor.apply();

            Toast.makeText(getContext(), "Account Created! Please Login.", Toast.LENGTH_SHORT).show();

            // نروح لصفحة الدخول عشان نجرب ندخل
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        });

        btnBackToLogin.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        });

        return view;
    }
}