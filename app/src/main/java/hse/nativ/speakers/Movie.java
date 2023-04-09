package hse.nativ.speakers;

public class Movie {

    private String name;
    private int imageResourceID;
    private String grade;
    private String genre;

    public static final Movie[] movies = {
            new Movie("Forrest Gump", R.drawable.forrest, "8.9", "drama"),
            new Movie("American Psycho", R.drawable.americanpsycho, "7.2", "thriller"),
            new Movie("Mind Games", R.drawable.mind_games, "8.6", "drama")
    };

    private Movie(String name, int imageResourceID, String grade, String genre) {
        this.name = name;
        this.grade = grade;
        this.imageResourceID = imageResourceID;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public String getGrade() {
        return grade;
    }

    public String getGenre() {
        return genre;
    }
}
