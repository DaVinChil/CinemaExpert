package hse.nativ.speakers;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoviesByCategoryFragment extends Fragment {

    private String category;
    private FragmentActivity context;
    private RecyclerView.Adapter movieRecyclerAdapter;

    public MoviesByCategoryFragment() {}
    public MoviesByCategoryFragment(String category, RecyclerView.Adapter adapter) {
        this.category = category;
        this.context = MainScreenActivity.context;
        this.movieRecyclerAdapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_movies_by_category, container, false);
        TextView categoryMovies = layout.findViewById(R.id.movies_category);
        categoryMovies.setText(category);

        RecyclerView recyclerView = layout.findViewById(R.id.movies_category_recycler);
        recyclerView.setAdapter(movieRecyclerAdapter);
        GridLayoutManager manager = new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView view = context.findViewById(R.id.bottom_navigation);
        view.getMenu().getItem(0).setChecked(true);
    }
}