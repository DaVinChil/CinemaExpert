package hse.nativ.speakers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MoviesContainerFragment extends Fragment {
    private LinearLayout view;
    private AppCompatActivity context;

    public MoviesContainerFragment() {
        this.context = MainScreenActivity.context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) return view;

        LinearLayout linearLayout =  (LinearLayout)inflater.inflate(R.layout.fragment_movies_container,
                container, false);

        FragmentTransaction ft = context.getSupportFragmentManager().beginTransaction();
        MoviesListFragment moviesListFragment1 = new MoviesListFragment("Top");
        moviesListFragment1.setCount(100);
        ft.add(R.id.movies_list_1, moviesListFragment1);
        ft.add(R.id.movies_list_2, new MoviesListFragment("Drama"));
        ft.add(R.id.movies_list_3, new MoviesListFragment("Mystery"));
        ft.add(R.id.movies_list_4, new MoviesListFragment("Thriller"));
        ft.add(R.id.movies_list_5, new MoviesListFragment("Comedy"));
        ft.add(R.id.movies_list_6, new MoviesListFragment("Romance"));
        ft.add(R.id.movies_list_7, new MoviesListFragment("Crime"));
        ft.add(R.id.movies_list_8, new MoviesListFragment("Sci-Fi"));
        ft.add(R.id.movies_list_9, new MoviesListFragment("Biography"));
        ft.add(R.id.movies_list_10, new MoviesListFragment("War"));
        ft.add(R.id.movies_list_11, new MoviesListFragment("Fantasy"));
        ft.commit();

        view = linearLayout;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigationView = context.findViewById(R.id.bottom_navigation);
        navigationView.getMenu().getItem(0).setChecked(true);
    }
}