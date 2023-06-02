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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setTheme();
        //setToolbar();
        setBottomNavigationView();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        moviesContainerFragment = new MoviesContainerFragment();
        ft.add(R.id.container, moviesContainerFragment);
        ft.commit();
    }

    private void setTheme() {
        setTheme(R.style.Theme_CinemaExpert);
        setContentView(R.layout.activity_main_screen);
    }

    private void setToolbar() {
        //Toolbar toolbar = findViewById(R.id.up_toolbar);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.orange));
        //setSupportActionBar(toolbar);
    }

    private void setBottomNavigationView() {
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.profile:
                    if (item.isChecked()) return true;
                    if (profileFragment == null) {
                        profileFragment = new ProfileFragment();
                    }
                    fragmentTransaction.replace(R.id.container, profileFragment)
                            .addToBackStack(null)
                            .commit();
                    break;

                case R.id.home:
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentTransaction
                            .replace(R.id.container, moviesContainerFragment)
                            .commit();
                    break;
            }
            return true;
        });
    }
}