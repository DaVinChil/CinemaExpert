package hse.nativ.speakers.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hse.nativ.speakers.activities.MainScreenActivity;
import hse.nativ.speakers.R;

public class MoviesContainerFragment extends Fragment {
    private ScrollView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) return view;

        ScrollView root = (ScrollView) inflater.inflate(R.layout.fragment_movies_container,
                container, false);

        FragmentTransaction ft = MainScreenActivity.context.getSupportFragmentManager().beginTransaction();
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

        view = root;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigationView = MainScreenActivity.context.findViewById(R.id.bottom_navigation);
        navigationView.getMenu().getItem(0).setChecked(true);
    }
}