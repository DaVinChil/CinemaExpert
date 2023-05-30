package hse.nativ.speakers;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

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
        ViewHolder holder = new ViewHolder(movieView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LinearLayout movieView = holder.linearLayout;

        Movie movie = movies.get(position);

        ImageDatabase movieImage = movie.getImage();
        ImageView imageMovie = movieView.findViewById(R.id.movie_picture);
        //Drawable drawable = ContextCompat.getDrawable(movieView.getContext(), movieImage.getUrl());
        //imageMovie.setImageDrawable(drawable);
        Glide.with(MainScreenActivity.context).load(movieImage.getUrl()).into(imageMovie);

        TextView movieName = movieView.findViewById(R.id.movie_name);
        movieName.setText(movie.getTitle());

        TextView movieGrade = movieView.findViewById(R.id.movie_grade);
        movieGrade.setText(String.valueOf(movie.getChartRating()));
        setGradeBackground(movieGrade, String.valueOf(movie.getChartRating()));

        TextView movieGenre = movieView.findViewById(R.id.movie_genre);
        movieGenre.setText(movie.getGenre().get(0));
    }

    private void setGradeBackground(TextView movieGrade, String grade) {
        if (Double.parseDouble(grade) >= 7) {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_green);
        } else if (Double.parseDouble(grade) >= 5) {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_gray);
        } else {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_red);
        }
    }
}
