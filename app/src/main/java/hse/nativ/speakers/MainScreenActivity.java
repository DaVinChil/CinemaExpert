package hse.nativ.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MoviesListFragment listFragment1 = new MoviesListFragment();
        setFragmentData(listFragment1, "Top 150");
        fragmentTransaction.add(R.id.movie_list_1, listFragment1);

        MoviesListFragment listFragment2 = new MoviesListFragment();
        setFragmentData(listFragment2, "Thrillers");
        fragmentTransaction.add(R.id.movie_list_2, listFragment2);

        fragmentTransaction.commit();
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