package hse.nativ.speakers;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;

        public ViewHolder(LinearLayout view) {
            super(view);
            linearLayout = view;
        }
    }

    private String[] names;
    private int[] imagesID;
    private String[] grades;
    private String[] genres;

    public MoviesAdapter(String[] names, int[] imagesID, String[] grades, String[] genres) {
        this.names = names;
        this.imagesID = imagesID;
        this.grades = grades;
        this.genres = genres;
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout movieView = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinearLayout movieView = holder.linearLayout;

        ImageView imageMovie = (ImageView) movieView.findViewById(R.id.movie_picture);
        Drawable drawable = ContextCompat.getDrawable(movieView.getContext(), imagesID[position]);
        imageMovie.setImageDrawable(drawable);

        TextView movieName = (TextView) movieView.findViewById(R.id.movie_name);
        movieName.setText(names[position]);

        TextView movieGrade = (TextView) movieView.findViewById(R.id.movie_grade);
        movieGrade.setText(grades[position]);
        setGradeBackground(movieGrade, grades[position]);

        TextView movieGenre = (TextView) movieView.findViewById(R.id.movie_genre);
        movieGenre.setText(genres[position]);
    }

    private void setGradeBackground(TextView movieGrade, String grade) {
        if (Double.parseDouble(grade) >= 7) {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_green);
        }
        else {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_gray);
        }
    }
}
