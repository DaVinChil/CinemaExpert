package hse.nativ.speakers;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainScreenActivity extends AppCompatActivity {

    public static AppCompatActivity context;
    private static MoviesContainerFragment moviesContainerFragment;
    private static ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_CinemaExpert);
        setContentView(R.layout.activity_main_screen);
        context = this;

        Toolbar toolbar = findViewById(R.id.up_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.orange));
        setSupportActionBar(toolbar);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (item.isChecked()) return true;
            switch (item.getItemId()) {
                case R.id.profile:
                    if (profileFragment == null) {
                        profileFragment = new ProfileFragment();
                    }
                    fragmentTransaction.replace(R.id.container, profileFragment)
                            .addToBackStack(null)
                            .commit();
                    break;

                case R.id.home:
                    fragmentTransaction.replace(R.id.container, moviesContainerFragment)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
            return true;
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        moviesContainerFragment = new MoviesContainerFragment();
        ft.add(R.id.container, moviesContainerFragment);
        ft.commit();
    }
}