package hse.nativ.speakers.DatabaseClasses;

public class UserSettings {
    private String photoUri;
    private String firstName;
    private String lastName;
    private String userName;

    public void setPhotoUri(String photoUri) {this.photoUri = photoUri;}
    public String getPhotoUri() {return photoUri;}

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getFirstName() {return firstName;}

    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getLastName() {return lastName;}

    public void setUserName(String userName) {this.userName = userName;}
    public String getUserName() {return userName;}
}
