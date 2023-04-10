package hse.nativ.speakers;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesListFragment extends Fragment {

    private String[] moviesNames;
    private String[] moviesGenres;
    private String[] moviesGrades;
    private int[] imagesID;
    private String moviesCategory;
    private FragmentActivity context;

    public MoviesListFragment() {
    }

    public MoviesListFragment(FragmentActivity context, Movie[] movies, String moviesCategory) {
        this.context = context;
        this.moviesCategory = moviesCategory;

        moviesNames = new String[movies.length];
        moviesGrades = new String[movies.length];
        imagesID = new int[movies.length];
        moviesGenres = new String[movies.length];
        for (int i = 0; i < movies.length; ++i) {
            moviesNames[i] = movies[i].getName();
            moviesGenres[i] = movies[i].getGenre();
            moviesGrades[i] = movies[i].getGrade();
            imagesID[i] = movies[i].getImageResourceID();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConstraintLayout linearLayout = (ConstraintLayout) inflater.inflate(
                R.layout.fragment_movies_list,
                container, false);

        TextView nameCategory = linearLayout.findViewById(R.id.movie_category);
        nameCategory.setText(moviesCategory);

        MoviesAdapter moviesAdapter = new MoviesAdapter(moviesNames, imagesID, moviesGrades, moviesGenres);

        RecyclerView movieRecycler = linearLayout.findViewById(R.id.movie_recycler);
        movieRecycler.setAdapter(moviesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        movieRecycler.setLayoutManager(layoutManager);

        return linearLayout;
    }


}