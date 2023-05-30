package hse.nativ.speakers;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DataInflater {

    private static FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void inflateTopRatedMovies(RecyclerView recyclerView, int count) {
       executorService.submit(() -> {
           List<Movie> movies = new ArrayList<>();
           database.collection("Movies")
                   .orderBy("chartRating", Query.Direction.DESCENDING)
                   .limit(count)
                   .get()
                   .addOnCompleteListener(task -> {
                       if (task.isSuccessful()) {
                           for (QueryDocumentSnapshot documentTask : task.getResult()) {
                               Movie movie = documentTask.toObject(Movie.class);
                               movies.add(movie);
                           }
                           MoviesAdapter moviesAdapter = new MoviesAdapter(movies);
                           recyclerView.setAdapter(moviesAdapter);
                       }
                   });
        });
    }

    public static void inflateTopByGenre(RecyclerView recyclerView, String genre) {
        executorService.submit(() -> {
            List<Movie> movies = new ArrayList<>();
            database.collection("Movies")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentTask : task.getResult()) {
                                Movie movie = documentTask.toObject(Movie.class);
                                List<String> genres = movie.getGenre();
                                for (String movieGenre : genres) {
                                    if (movieGenre.equalsIgnoreCase(genre)) {
                                        movies.add(movie);
                                    }
                                }
                            }
                            Collections.shuffle(movies);
                            recyclerView.setAdapter(new MoviesAdapter(movies));
                        }
                    });
        });
    }
}