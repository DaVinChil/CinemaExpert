package hse.nativ.speakers;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String id;
    private String title;
    private double chartRating;
    private List<String> genre;
    private long year;
    private long runningTimeInMinutes;
    private ImageDatabase image;
    private List<Actor> actors = new ArrayList<>();
    private List<String> directors = new ArrayList<>();
    private List<String> writers = new ArrayList<>();
    private String description;

    static class Actor {
        private String fullName;
        private String id;
        private List<String> characters;
        public Actor() {}

        public Actor(String fullName, String id) {
            this.id = id;
            this.fullName = fullName;
        }

        public String getFullName() {return fullName;}
        public void setFullName(String fullName) {this.fullName = fullName;}

        public String getId() {return id;}
        public void setId(String id) {this.id = id;}

        public List<String> getCharacters() {return characters;}
        public void setCharacters(List<String> characters) {this.characters = characters;}
        public void addCharacter(String character) {
            characters.add(character);
        }
    }

    public Movie(){}

    public void setChartRating(double chartRating) {this.chartRating = chartRating;}
    public double getChartRating() {return chartRating;}

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public void setTitle(String title) {this.title = title;}
    public String getTitle() {return title;}

    public void setGenre(List<String> genre) {this.genre = genre;}
    public List<String> getGenre() {return genre;}

    public void setYear(long year) {this.year = year;}
    public long getYear() {return year;}

    public void setActors(List<Actor> actors) {this.actors = actors;}
    public List<Actor> getActors() {return actors;}
    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void setRunningTimeInMinutes(long runningTimeInMinutes) {this.runningTimeInMinutes = runningTimeInMinutes;}
    public long getRunningTimeInMinutes() {return runningTimeInMinutes;}

    public void setImage(ImageDatabase image) {this.image = image;}
    public ImageDatabase getImage() {return image;}

    public void setDescription(String description) {this.description = description;}
    public String getDescription() {return description;}

    public void setDirectors(List<String> directors) {this.directors = directors;}
    public List<String> getDirectors() {return directors;}
    public void addDirector(String directorId) {directors.add(directorId);}

    public void setWriters(List<String> writers) {this.writers = writers;}
    public List<String> getWriters() {return writers;}
    public void addWriter(String writerId) {writers.add(writerId);}
}

