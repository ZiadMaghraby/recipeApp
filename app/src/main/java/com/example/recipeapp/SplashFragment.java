package com.example.recipeapp;

import android.os.Bundle;
import android.os.Handler; // I need this class for wait some time
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation; // I need this for screen animation
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SplashFragment extends Fragment {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable showLogin = () -> {
        if (!isResumed() || getView() == null || getParentFragmentManager().isStateSaved()) {
            return;
        }
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // I create the view from the XML file (activity_splash.xml).
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        // I find the logo image view to apply animation on it.
        ImageView logo = view.findViewById(R.id.imgLogo);

        // I load and start the animation (fade_in animation).
        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in); // This line load animation file
        logo.startAnimation(fadeIn); // This line starts the animation on the logo

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.removeCallbacks(showLogin);
        handler.postDelayed(showLogin, 3000);
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(showLogin);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        handler.removeCallbacks(showLogin);
        super.onDestroyView();
    }
}
