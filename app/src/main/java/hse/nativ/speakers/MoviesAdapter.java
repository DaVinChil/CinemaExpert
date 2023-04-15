package hse.nativ.speakers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
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
    private Context mContext;

    public MoviesAdapter(Context context, String[] names, int[] imagesID, String[] grades, String[] genres) {
        mContext = context;
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
        ViewHolder holder = new ViewHolder(movieView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LinearLayout movieView = holder.linearLayout;

        ImageView imageMovie = movieView.findViewById(R.id.movie_picture);
        Drawable drawable = ContextCompat.getDrawable(movieView.getContext(), imagesID[position]);
        imageMovie.setImageDrawable(drawable);

        TextView movieName = movieView.findViewById(R.id.movie_name);
        movieName.setText(names[position]);

        TextView movieGrade = movieView.findViewById(R.id.movie_grade);
        movieGrade.setText(grades[position]);
        setGradeBackground(movieGrade, grades[position]);

        TextView movieGenre = movieView.findViewById(R.id.movie_genre);
        movieGenre.setText(genres[position]);
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
