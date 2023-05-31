package hse.nativ.speakers;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MovieDetailsFragment extends Fragment {

    private Movie movie;

    MovieDetailsFragment(Movie movie) {this.movie = movie;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ConstraintLayout view = (ConstraintLayout)inflater.inflate(R.layout.fragment_movie_details, container, false);

        ImageView movieImage = view.findViewById(R.id.movie_image);
        Glide.with(MainScreenActivity.context)
                .load(movie.getImage().getUrl())
                .into(movieImage);

        TextView movieTitle = view.findViewById(R.id.movie_title);
        movieTitle.setText(movie.getTitle());
        movieTitle.setTextColor(getResources().getColor(R.color.white));

        TextView movieDescription = view.findViewById(R.id.movie_description);
        movieDescription.setText(movie.getDescription());

        TextView movieGrade = view.findViewById(R.id.grade);
        movieGrade.setText(String.valueOf(movie.getChartRating()));
        CustomizeHelper.setGradeBackground(movieGrade, String.valueOf(movie.getChartRating()));

        TextView movieGenres = view.findViewById(R.id.movie_genres);
        String genres = "";
        for (String genre : movie.getGenre()) {
            genres += genre + ", ";
        }
        genres = genres.substring(0, genres.length() - 2);
        movieGenres.setText(genres);

        TextView movieYear = view.findViewById(R.id.premier_year);
        movieYear.setText(String.valueOf(movie.getYear()));

        RecyclerView actors = view.findViewById(R.id.movie_details_actors);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        actors.setLayoutManager(layoutManager);
        DataInflater.inflateActorsByMovieId(actors, movie.getId());

        return view;
    }
}