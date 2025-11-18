package com.example.recipeapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SplashFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        ImageView logo = view.findViewById(R.id.imgLogo);

        // تشغيل الأنيميشن
        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        logo.startAnimation(fadeIn);

        // الانتظار 3 ثواني ثم الانتقال
        new Handler().postDelayed(() -> {
            if (isAdded()) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }, 3000);

        return view;
    }
}