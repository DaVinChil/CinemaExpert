package hse.nativ.speakers.fragments;

import android.os.Bundle;

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

import hse.nativ.speakers.help_classes.CustomizeHelper;
import hse.nativ.speakers.database_classes.DataInflater;
import hse.nativ.speakers.activities.MainScreenActivity;
import hse.nativ.speakers.database_classes.Movie;
import hse.nativ.speakers.R;

public class MovieDetailsFragment extends Fragment {

    private final Movie movie;

    public MovieDetailsFragment(Movie movie) {this.movie = movie;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView view = (ScrollView) inflater.inflate(R.layout.fragment_movie_details, container, false);

        ImageView movieImage = view.findViewById(R.id.movie_details_image);
        Glide.with(MainScreenActivity.context)
                .load(movie.getImage().getUrl())
                .into(movieImage);

        TextView movieTitle = view.findViewById(R.id.movie_details_title);
        movieTitle.setText(movie.getTitle());
        movieTitle.setTextColor(getResources().getColor(R.color.white));

        TextView movieDescription = view.findViewById(R.id.movie_details_description);
        movieDescription.setText(movie.getDescription());

        TextView movieGrade = view.findViewById(R.id.movie_details_grade);
        movieGrade.setText(String.valueOf(movie.getChartRating()));
        CustomizeHelper.setGradeBackground(movieGrade, String.valueOf(movie.getChartRating()));

        TextView movieGenres = view.findViewById(R.id.movie_details_genres);
        String genres = "";
        for (String genre : movie.getGenre()) {
            genres += genre + ", ";
        }
        genres = genres.substring(0, genres.length() - 2);
        movieGenres.setText(genres);

        TextView movieYear = view.findViewById(R.id.movie_details_premier_year);
        movieYear.setText(String.valueOf(movie.getYear()));

        TextView movieRunningTime = view.findViewById(R.id.movie_details_running_time);
        long runningTimeInMinutes = movie.getRunningTimeInMinutes();
        long hours = runningTimeInMinutes / 60;
        long minutes = runningTimeInMinutes % 60;
        movieRunningTime.setText(hours + "h " + minutes + "m");

        RecyclerView actors = view.findViewById(R.id.movie_details_actors);
        LinearLayoutManager actorsManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        actors.setLayoutManager(actorsManager);
        DataInflater.inflateActorsByMovieId(actors, movie.getId());

        RecyclerView directors = view.findViewById(R.id.movies_details_creators);
        LinearLayoutManager creatorsManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        directors.setLayoutManager(creatorsManager);
        DataInflater.inflateCreatorsByMovieId(directors, movie.getId());

        return view;
    }
}