package hse.nativ.speakers;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesListFragment extends Fragment {

    private String moviesCategory;
    private int count;
    private AppCompatActivity context;

    private TextView getAllMoviesButton;
    private TextView moviesCategoryText;
    private RecyclerView moviesRecycler;

    public MoviesListFragment(String moviesCategory) {
        this.context = MainScreenActivity.context;
        this.moviesCategory = moviesCategory;
    }

    public void setCount(int count) {this.count = count;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(
                R.layout.fragment_movies_list,
                container, false);

        findAllViews(constraintLayout);

        if (moviesCategory.contains("Top")) {
            moviesCategoryText.setText(moviesCategory + " " + count);
            DataInflater.inflateTopRatedMovies(moviesRecycler, count);
        }
        else {
            DataInflater.inflateTopByGenre(moviesRecycler, moviesCategory);
            moviesCategoryText.setText(moviesCategory);
        }

        moviesRecycler.addItemDecoration(new CustomizeHelper.EdgeDecorator(40));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        moviesRecycler.setLayoutManager(layoutManager);

        setButton();

        return constraintLayout;
    }

    private void findAllViews(ConstraintLayout view) {
        getAllMoviesButton = view.findViewById(R.id.get_all_movies);
        moviesCategoryText = view.findViewById(R.id.movie_category);
        moviesRecycler = view.findViewById(R.id.movie_recycler);
    }

    private void setButton() {
        getAllMoviesButton.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
            MoviesByCategoryFragment movieList = new MoviesByCategoryFragment(moviesCategoryText.getText().toString(),
                    moviesRecycler.getAdapter());
            fragmentTransaction.replace(R.id.container, movieList);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }
}