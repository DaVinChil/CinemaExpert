package hse.nativ.speakers;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.Resource;

import java.util.ArrayList;
import java.util.List;

public class SearchMoviesFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView results;
    private List<Person> persons = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<Person> searchPersons = new ArrayList<>();
    private List<Movie> searchMovies = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.fragment_search_movies, container, false);
        searchView = view.findViewById(R.id.search_movies_string);
        results = view.findViewById(R.id.search_results_suggestions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        results.setLayoutManager(layoutManager);
        float dip = 80f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
        results.addItemDecoration(new CustomizeHelper.SearchDecorator((int)px));
        DataInflater.inflateAllCollections(results, persons, movies);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               /* searchMovies.clear();
                searchPersons.clear();
                if (query.length() > 0) {
                    for (Movie movie : movies) {
                        if (movie.getTitle().toLowerCase().contains(query.toLowerCase())) {
                            searchMovies.add(movie);
                        }
                    }
                    for (Person person : persons) {
                        if (person.getFullName().toLowerCase().contains(query.toLowerCase())) {
                            searchPersons.add(person);
                        }
                    }
                } else {
                    searchPersons.addAll(persons);
                    searchMovies.addAll(movies);
                }
                results.setAdapter(new SearchResultsAdapter(searchMovies, searchPersons));*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchMovies.clear();
                searchPersons.clear();
                if (newText.length() > 0) {
                    for (Movie movie : movies) {
                        if (movie.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                            searchMovies.add(movie);
//                            System.out.println(searchMovies.size());
                        }
                    }
                    for (Person person : persons) {
                        if (person.getFullName().toLowerCase().contains(newText.toLowerCase())) {
                            searchPersons.add(person);
                            System.out.println(searchPersons.size());
                        }
                    }
                } else {
                    searchPersons.addAll(persons);
                    searchMovies.addAll(movies);
                }
                System.out.println(searchPersons.size());
                results.setAdapter(new SearchResultsAdapter(searchMovies, searchPersons));
                return false;
            }
        });

        return view;
    }
}