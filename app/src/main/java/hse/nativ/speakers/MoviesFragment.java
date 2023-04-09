package hse.nativ.speakers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MoviesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout =  (LinearLayout)inflater.inflate(R.layout.fragment_movies,
                container, false);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        MoviesListFragment moviesList1 = new MoviesListFragment();
        setFragmentData(moviesList1, "Top 100");
        ft.add(R.id.movies_list_1, moviesList1);

        MoviesListFragment moviesList2 = new MoviesListFragment();
        setFragmentData(moviesList2, "Top 200");
        ft.add(R.id.movies_list_2, moviesList1);

        ft.commit();
        return linearLayout;
    }

    private void setFragmentData(MoviesListFragment listFragment, String category) {
        String[] moviesNames = new String[Movie.movies.length];
        String moviesCategory = category;
        String[] moviesGrades = new String[Movie.movies.length];
        int[] moviesImages = new int[Movie.movies.length];
        String[] moviesGenres = new String[Movie.movies.length];
        for (int i = 0; i < Movie.movies.length; ++i) {
            moviesNames[i] = Movie.movies[i].getName();
            moviesGenres[i] = Movie.movies[i].getGenre();
            moviesGrades[i] = Movie.movies[i].getGrade();
            moviesImages[i] = Movie.movies[i].getImageResourceID();
        }
        listFragment.setMoviesNames(moviesNames);
        listFragment.setImagesID(moviesImages);
        listFragment.setMoviesCategory(moviesCategory);
        listFragment.setMoviesGrades(moviesGrades);
        listFragment.setMoviesGenres(moviesGenres);
    }
}