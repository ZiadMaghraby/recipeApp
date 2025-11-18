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

        btnLogin.setOnClickListener(v -> {
            String inputEmail = etEmail.getText().toString().trim();
            String inputPassword = etPassword.getText().toString().trim();

            // التحقق الشكلي (Validation)
            if (!inputEmail.matches(EMAIL_PATTERN)) { etEmail.setError("Invalid Email Format"); return; }
            if (!inputPassword.matches(PASSWORD_PATTERN)) { etPassword.setError("Password too short"); return; }

            // --- الجزء الجديد: التأكد من الحساب (Authentication) ---

            // 1. نفتح نفس "النوتة" اللي اسمها UserData
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

            // 2. نقرأ البيانات المحفوظة (لو ملقاش حاجة هيرجع فاضي "")
            String savedEmail = sharedPreferences.getString("saved_email", "");
            String savedPassword = sharedPreferences.getString("saved_password", "");

            // 3. نقارن اللي المستخدم كتبه باللي محفوظ
            if (inputEmail.equals(savedEmail) && inputPassword.equals(savedPassword)) {
                // البيانات صح ومطابقة!
                Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                // ندخل على الصفحة الرئيسية
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            } else {
                // البيانات غلط أو المستخدم ده مش موجود
                Toast.makeText(getContext(), "Wrong Email or Password!", Toast.LENGTH_LONG).show();
            }
        });

        btnGoToRegister.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}