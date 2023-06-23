package hse.nativ.speakers.RecyclerAdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hse.nativ.speakers.DatabaseClasses.Movie;
import hse.nativ.speakers.DatabaseClasses.Person;
import hse.nativ.speakers.Fragments.MovieDetailsFragment;
import hse.nativ.speakers.Fragments.PersonDetailsFragment;
import hse.nativ.speakers.Activities.MainScreenActivity;
import hse.nativ.speakers.R;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout view;
        public ViewHolder(ConstraintLayout view) {
            super(view);
            this.view = view;
        }
    }

    private List<Person> persons;
    private List<Movie> movies;

    public SearchResultsAdapter(List<Movie> movies, List<Person> persons) {
        this.persons = persons;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_search_suggestion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConstraintLayout view = holder.view;

        TextView name = view.findViewById(R.id.view_search_suggestion_name);
        TextView year = view.findViewById(R.id.view_search_suggestion_year);
        ImageView imageView = view.findViewById(R.id.view_search_suggestion_image);

        if (position >= movies.size()) {
            int personPosition = position - movies.size();
            Person person = persons.get(personPosition);
            if (!person.getBirthDate().equals("?")) {
                year.setText(person.getBirthDate().substring(0, 4));
            }
            name.setText(person.getFullName());
            Glide.with(MainScreenActivity.context).load(person.getPhoto().getUrl()).into(imageView);
        }
        else {
            Movie movie = movies.get(position);
            name.setText(movie.getTitle());
            year.setText(String.valueOf(movie.getYear()));
            Glide.with(MainScreenActivity.context).load(movie.getImage().getUrl()).into(imageView);
        }

        view.setOnClickListener(item -> {
            FragmentTransaction fragmentTransaction = MainScreenActivity.context.getSupportFragmentManager().beginTransaction();
            if (position >= movies.size()) {
                fragmentTransaction.replace(R.id.container, new PersonDetailsFragment(persons.get(position - movies.size())))
                        .addToBackStack(null)
                        .commit();
            } else {
                fragmentTransaction.replace(R.id.container, new MovieDetailsFragment(movies.get(position)))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size() + persons.size();
    }
}
