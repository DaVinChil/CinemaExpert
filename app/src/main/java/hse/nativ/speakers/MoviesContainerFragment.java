package hse.nativ.speakers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoviesContainerFragment extends Fragment {

    private AppCompatActivity context;

    public MoviesContainerFragment() {
        this.context = MainScreenActivity.context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout =  (LinearLayout)inflater.inflate(R.layout.fragment_movies_container,
                container, false);

        FragmentTransaction ft = context.getSupportFragmentManager().beginTransaction();
        MoviesListFragment moviesList1 = new MoviesListFragment(Movie.movies, "Top 100");
        ft.add(R.id.movies_list_1, moviesList1);

        MoviesListFragment moviesList2 = new MoviesListFragment(Movie.movies, "Top 250");
        ft.add(R.id.movies_list_2, moviesList2);

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