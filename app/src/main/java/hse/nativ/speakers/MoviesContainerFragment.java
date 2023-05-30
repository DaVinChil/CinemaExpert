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

    private MoviesListFragment moviesListFragment1;
    private MoviesListFragment moviesListFragment2;
    private MoviesListFragment moviesListFragment3;
    private MoviesListFragment moviesListFragment4;
    private MoviesListFragment moviesListFragment5;
    private MoviesListFragment moviesListFragment6;
    private MoviesListFragment moviesListFragment7;
    private static LinearLayout view;

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
        moviesListFragment1 = new MoviesListFragment("Top");
        moviesListFragment1.setCount(100);
        ft.add(R.id.movies_list_1, moviesListFragment1);

        moviesListFragment2 = new MoviesListFragment("Drama");
        ft.add(R.id.movies_list_2, moviesListFragment2);

        moviesListFragment3 = new MoviesListFragment("Mystery");
        ft.add(R.id.movies_list_3, moviesListFragment3);

        moviesListFragment4 = new MoviesListFragment("Thriller");
        ft.add(R.id.movies_list_4, moviesListFragment4);

        moviesListFragment5 = new MoviesListFragment("Comedy");
        ft.add(R.id.movies_list_5, moviesListFragment5);

        moviesListFragment6 = new MoviesListFragment("Romance");
        ft.add(R.id.movies_list_6, moviesListFragment6);

        moviesListFragment7 = new MoviesListFragment("Crime");
        ft.add(R.id.movies_list_7, moviesListFragment7);

        view = linearLayout;
        ft.commit();
        return linearLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigationView = context.findViewById(R.id.bottom_navigation);
        navigationView.getMenu().getItem(0).setChecked(true);
    }
}