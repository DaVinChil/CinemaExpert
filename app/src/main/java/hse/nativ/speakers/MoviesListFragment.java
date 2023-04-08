package hse.nativ.speakers;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesListFragment extends Fragment {

    private String[] moviesNames;
    private String[] moviesGenres;
    private String[] moviesGrades;
    private int[] imagesID;
    private String moviesCategory;

    public MoviesListFragment() {}
    public MoviesListFragment(String[] moviesNames, String[] moviesGenres, String[] moviesGrades,
                              int[] imagesID, String moviesCategory) {
        this.moviesGrades = moviesGrades;
        this.moviesNames = moviesNames;
        this.moviesGenres = moviesGenres;
        this.imagesID = imagesID;
        this.moviesCategory = moviesCategory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(
                R.layout.fragment_movies_list,
                container, false);

        TextView nameCategory = (TextView) linearLayout.findViewById(R.id.movie_category);
        nameCategory.setText(moviesCategory);

        RecyclerView movieRecycler = (RecyclerView) linearLayout.findViewById(R.id.movie_recycler);
        MoviesAdapter moviesAdapter = new MoviesAdapter(moviesNames, imagesID, moviesGrades, moviesGenres);
        movieRecycler.setAdapter(moviesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        movieRecycler.setLayoutManager(layoutManager);
        return linearLayout;
    }

    public void setMoviesNames(String[] moviesNames) {
        this.moviesNames = moviesNames;
    }

    public void setMoviesGenres(String[] moviesGenres) {
        this.moviesGenres = moviesGenres;
    }

    public void setImagesID(int[] imagesID) {
        this.imagesID = imagesID;
    }

    public void setMoviesCategory(String moviesCategory) {
        this.moviesCategory = moviesCategory;
    }

    public void setMoviesGrades(String[] moviesGrades) {
        this.moviesGrades = moviesGrades;
    }
}