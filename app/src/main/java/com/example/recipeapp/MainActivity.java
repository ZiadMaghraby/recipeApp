package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback; // this import for custom back button
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // this code make custom back button work in smart way
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                // if drawer menu is open, we close it
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                // if we have fragments in back stack, we go back to them
                else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                }

                // if no more pages, app will exit
                else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });

        // here we open splash screen only first time
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SplashFragment())
                    .commit();

            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    // this method open the drawer from other fragments
    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Fragment selectedFragment = null;

        // here we check which item user click in drawer
        if (id == R.id.nav_home) {
            selectedFragment = new HomeFragment();
        } else if (id == R.id.nav_profile) {
            selectedFragment = new ProfileFragment();
        } else if (id == R.id.nav_favorites) {
            selectedFragment = new FavoritesFragment();
        } else if (id == R.id.nav_shopping) {
            selectedFragment = new ShoppingListFragment();
        } else if (id == R.id.nav_add_recipe) {
            selectedFragment = new AddRecipeFragment();
        } else if (id == R.id.nav_settings) {
            selectedFragment = new SettingsFragment();
        } else if (id == R.id.nav_about) {
            selectedFragment = new AboutUsFragment();
        }

        else if (id == R.id.nav_logout) {
            // when user logout we remove back stack, so he cant go back
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();

            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();

            // clear all fragment history
            getSupportFragmentManager().popBackStack(null,
                    androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        // this part change screen to the fragment user choose
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .addToBackStack(null) // this make back button work
                    .commit();
        }

        // close drawer after click
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
