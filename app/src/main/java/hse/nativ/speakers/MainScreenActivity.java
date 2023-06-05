package hse.nativ.speakers;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainScreenActivity extends AppCompatActivity {

    public static AppCompatActivity context;
    private MoviesContainerFragment moviesContainerFragment;
    private ProfileFragment profileFragment;
    private SearchMoviesFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setTheme(R.style.Theme_CinemaExpert);
        setContentView(R.layout.activity_main_screen);
        setBottomNavigationView();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        moviesContainerFragment = new MoviesContainerFragment();
        ft.add(R.id.container, moviesContainerFragment);
        ft.commit();
    }

    private void setBottomNavigationView() {
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (item.isChecked()) return true;
            if (item.getItemId() == R.id.profile) {
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                }
                fragmentTransaction
                        .replace(R.id.container, profileFragment)
                        .addToBackStack(null)
                        .commit();
            }
            else if (item.getItemId() == R.id.home) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction
                        .replace(R.id.container, moviesContainerFragment)
                        .commit();
            }
            else if (item.getItemId() == R.id.search) {
                if (searchFragment == null) {
                    searchFragment = new SearchMoviesFragment();
                }
                fragmentTransaction
                        .replace(R.id.container, searchFragment)
                        .addToBackStack(null)
                        .commit();
            }
            return true;
        });
    }
}