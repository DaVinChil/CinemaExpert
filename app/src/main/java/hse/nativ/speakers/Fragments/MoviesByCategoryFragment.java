package hse.nativ.speakers.Fragments;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hse.nativ.speakers.Activities.MainScreenActivity;
import hse.nativ.speakers.R;

public class MoviesByCategoryFragment extends Fragment {

    private String category;
    private RecyclerView.Adapter movieRecyclerAdapter;

    public MoviesByCategoryFragment() {}
    public MoviesByCategoryFragment(String category, RecyclerView.Adapter adapter) {
        this.category = category;
        this.movieRecyclerAdapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.fragment_movies_by_category, container, false);
        TextView categoryMovies = view.findViewById(R.id.movies_category);
        categoryMovies.setText(category);

        RecyclerView recyclerView = view.findViewById(R.id.movies_category_recycler);
        recyclerView.setAdapter(movieRecyclerAdapter);
        GridLayoutManager manager = new GridLayoutManager(MainScreenActivity.context, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView view = MainScreenActivity.context.findViewById(R.id.bottom_navigation);
        view.getMenu().getItem(0).setChecked(true);
    }
}