package hse.nativ.speakers;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private ImageDatabase photo;
    private String fullName;
    private String birthDate;
    private String birthPlace;
    private String deathDate;
    private String deathCause;
    private String deathPlace;
    private double height;
    private String gender;;
    private List<String> filmography = new ArrayList<>();

    public Person() {}

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public void setFullName(String fullName) {this.fullName = fullName;}
    public String getFullName() {return fullName;}

    public void setPhoto(ImageDatabase photo) {this.photo = photo;}
    public ImageDatabase getPhoto() {return photo;}

    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}
    public String getBirthDate() {return birthDate;}

    public void setBirthPlace(String birthPlace) {this.birthPlace = birthPlace;}
    public String getBirthPlace() {return birthPlace;}

    public void setDeathDate(String deathDate) {this.deathDate = deathDate;}
    public String getDeathDate() {return deathDate;}

    public void setDeathPlace(String deathPlace) {this.deathPlace = deathPlace;}
    public String getDeathPlace() {return deathPlace;}

    public void setDeathCause(String deathCause) {this.deathCause = deathCause;}
    public String getDeathCause() {return deathCause;}

    public void setHeight(double height) {this.height = height;}
    public double getHeight() {return height;}

    public void setGender(String gender) {this.gender = gender;}
    public String getGender() {return gender;}

    public void setFilmography(List<String> filmography) {this.filmography = filmography;}
    public List<String> getFilmography() {return filmography;}
    public void addFilm(String movieId) {
        filmography.add(movieId);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(id.substring(2));
    }

    @Override
    public boolean equals(Object object) {
        if (object != null) {
            if (object instanceof Person) {
                return this.id.equals(((Person) object).id);
            }
        }
        return false;
    }
}
