package hse.nativ.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainScreenActivity extends AppCompatActivity {

    public static AppCompatActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        context = this;

        Toolbar toolbar = findViewById(R.id.up_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.orange));
        setSupportActionBar(toolbar);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MoviesContainerFragment list = new MoviesContainerFragment();
        ft.add(R.id.container, list);
        ft.commit();
    }
}