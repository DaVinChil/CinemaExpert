package hse.nativ.speakers;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


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

    public static void inflateActorsByMovieId(RecyclerView recyclerView, String movieId) {
        executorService.submit(() -> {
            List<Person> actors = new ArrayList<>();
            Map<String, List<String>> characters = new HashMap<>();
            database.collection("Movies")
                    .document(movieId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Movie movie = task.getResult().toObject(Movie.class);
                            for (Movie.Actor actor : movie.getActors()) {
                                characters.put(actor.getId(), actor.getCharacters());
                                executorService.submit(()-> {
                                    database.collection("Persons")
                                            .document(actor.getId())
                                            .get()
                                            .addOnCompleteListener(task1 -> {
                                                if (task1.isSuccessful()) {
                                                    Person person = task1.getResult().toObject(Person.class);
                                                    actors.add(person);
                                                    if (actors.size() == movie.getActors().size()) {
                                                        recyclerView.setAdapter(new PersonsAdapter(actors, characters));
                                                        return;
                                                    }
                                                }
                                            });
                                });
                            }
                        }
                    });
        });
    }

    public static void inflateFilmographyByPersonId(RecyclerView recyclerView, String personId) {
        executorService.submit(() -> {
            List<Movie> movies = new ArrayList<>();
            database.collection("Movies")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Movie movie = documentSnapshot.toObject(Movie.class);
                                boolean flag = false;
                                for (Movie.Actor actor : movie.getActors()) {
                                    if (personId.equals(actor.getId())) {
                                        movies.add(movie);
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag) continue;
                                for (String directorId : movie.getDirectors()) {
                                    if (personId.equals(directorId)) {
                                        movies.add(movie);
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag) continue;
                                for (String writerId : movie.getWriters()) {
                                    if (personId.equals(writerId)) {
                                        movies.add(movie);
                                        break;
                                    }
                                }
                            }
                            recyclerView.setAdapter(new MoviesAdapter(movies));
                        }
                    });
        });
    }

    public static void inflateCreatorsByMovieId(RecyclerView recyclerView, String movieId) {
        executorService.submit(() -> {
            List<Person> creators = new ArrayList<>();
            Map<String, List<String>> personsTypes = new HashMap<>();
            AtomicInteger countAdded = new AtomicInteger();
            database.collection("Movies")
                    .document(movieId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Movie movie = task.getResult().toObject(Movie.class);
                            List<String> directors = movie.getDirectors();
                            List<String> writers = movie.getWriters();
                            int directorsSize = directors.size();
                            int writersSize = writers.size();
                            for (String directorId : directors) {
                                database.collection("Persons")
                                        .document(directorId)
                                        .get()
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Person director = task1.getResult().toObject(Person.class);
                                                creators.add(director);
                                                countAdded.incrementAndGet();
                                                List<String> types = new ArrayList<>();
                                                types.add("director");
                                                if (writers.contains(directorId)) {
                                                    types.add("writer");
                                                    countAdded.incrementAndGet();
                                                }
                                                personsTypes.put(directorId, types);
                                                if (countAdded.get() == directorsSize + writersSize) {
                                                    recyclerView.setAdapter(new PersonsAdapter(creators, personsTypes));
                                                    return;
                                                }
                                            }
                                        });
                            }
                            for (String writerId : writers) {
                                database.collection("Persons")
                                        .document(writerId)
                                        .get()
                                        .addOnCompleteListener(task1 -> {
                                            Person writer = task1.getResult().toObject(Person.class);
                                            if (!creators.contains(writer)) {
                                                creators.add(writer);
                                                List<String> types = new ArrayList<>();
                                                types.add("writer");
                                                personsTypes.put(writerId, types);
                                                countAdded.incrementAndGet();
                                                if (countAdded.get() == writersSize + directorsSize) {
                                                    recyclerView.setAdapter(new PersonsAdapter(creators, personsTypes));
                                                    return;
                                                }
                                            }
                                        });
                                }
                            }
                    });
        });
    }

    public static void inflateAllCollections(List<Person> persons, List<Movie> movies) {
        executorService.submit(() -> {
            AtomicBoolean personsAdded = new AtomicBoolean(false);
            AtomicBoolean moviesAdded = new AtomicBoolean(false);
           database.collection("Movies")
                   .get()
                   .addOnCompleteListener(task -> {
                      if (task.isSuccessful()) {
                          QuerySnapshot snapshots = task.getResult();
                          int moviesCount = snapshots.size();
                          for (QueryDocumentSnapshot documentSnapshot : snapshots) {
                              Movie movie = documentSnapshot.toObject(Movie.class);
                              movies.add(movie);
                              if (movies.size() == moviesCount) moviesAdded.set(true);
                          }
                      }
                   });
           database.collection("Persons")
                   .get()
                   .addOnCompleteListener(task -> {
                       if (task.isSuccessful()) {
                           QuerySnapshot snapshots = task.getResult();
                           int personsCount = snapshots.size();
                           for (QueryDocumentSnapshot documentSnapshot : snapshots) {
                               Person person = documentSnapshot.toObject(Person.class);
                               persons.add(person);
                               if (persons.size() == personsCount) personsAdded.set(true);
                           }
                       }
                   });
           Collections.shuffle(movies);
           Collections.shuffle(persons);
        });
    }
}