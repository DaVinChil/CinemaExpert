package hse.nativ.speakers.RecyclerAdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import hse.nativ.speakers.HelpClasses.CustomizeHelper;
import hse.nativ.speakers.DatabaseClasses.Movie;
import hse.nativ.speakers.Fragments.MovieDetailsFragment;
import hse.nativ.speakers.DatabaseClasses.ImageDatabase;
import hse.nativ.speakers.Activities.MainScreenActivity;
import hse.nativ.speakers.R;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        public ViewHolder(LinearLayout view) {
            super(view);
            linearLayout = view;
        }
    }

    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout movieView = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LinearLayout movieView = holder.linearLayout;

        Movie movie = movies.get(position);

        ImageDatabase movieImage = movie.getImage();
        ImageView imageMovie = movieView.findViewById(R.id.movie_picture);
        Glide.with(MainScreenActivity.context).load(movieImage.getUrl()).into(imageMovie);

        TextView movieName = movieView.findViewById(R.id.movie_name);
        movieName.setText(movie.getTitle());

        TextView movieGrade = movieView.findViewById(R.id.movie_grade);
        movieGrade.setText(String.valueOf(movie.getChartRating()));
        CustomizeHelper.setGradeBackground(movieGrade, String.valueOf(movie.getChartRating()));

        TextView movieGenre = movieView.findViewById(R.id.movie_genre);
        movieGenre.setText(movie.getGenre().get(0));

        movieView.setOnClickListener(view -> {
            FragmentTransaction ft = MainScreenActivity.context.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, new MovieDetailsFragment(movie));
            ft.addToBackStack(null);
            ft.commit();
        });
    }
}
