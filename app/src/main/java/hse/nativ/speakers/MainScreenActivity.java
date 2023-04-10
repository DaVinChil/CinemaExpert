package hse.nativ.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MoviesContainerFragment list = new MoviesContainerFragment(this);
        ft.add(R.id.container, list);
        ft.commit();
    }
}