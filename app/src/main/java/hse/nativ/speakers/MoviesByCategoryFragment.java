package hse.nativ.speakers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MoviesByCategoryFragment extends Fragment {

    private String category;

    public MoviesByCategoryFragment() {}
    public MoviesByCategoryFragment(String category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.fragment_movies_by_category, container, false);
        TextView categoryMovies = layout.findViewById(R.id.movies_category);
        categoryMovies.setText(category);

        return layout;
    }
}