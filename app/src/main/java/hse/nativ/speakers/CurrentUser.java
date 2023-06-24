package hse.nativ.speakers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import hse.nativ.speakers.DatabaseClasses.UserSettings;

public class CurrentUser {
    private CurrentUser(){};

    public static final String USER_ID_DOCUMENT_TAG = "USER_ID_DOCUMENT";
    public static final String USER_NAME_TAG = "USER_NAME";
    public static final String USER_EMAIL_TAG = "USER_EMAIL";
    public static final String USER_PASSWORD_TAG = "USER_PASSWORD";

    private static String USER_ID_DOCUMENT = null;
    private static String USER_EMAIL = null;
    private static String USER_PASSWORD = null;
    private static String userName = null;
    private static DocumentReference database = null;
    private static UserSettings userSettings = null;

    public static String getUserIdDocument() {
        return USER_ID_DOCUMENT;
    }
    public static void setUserIdDocument(String userIdDocument) {USER_ID_DOCUMENT = userIdDocument;}

    public static String getUserEmail() {
        return USER_EMAIL;
    }
    public static void setUserEmail(String userEmail) {
        USER_EMAIL = userEmail;
    }

    public static String getUserPassword() {
        return USER_PASSWORD;
    }
    public static void setUserPassword(String userPassword) {
        USER_PASSWORD = userPassword;
    }

    public static void connectToDatabase() {
        CollectionReference accounts = FirebaseFirestore.getInstance().collection("AccountSettings");
        accounts.document(USER_EMAIL)
                .get()
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       database = accounts.document(USER_EMAIL);
                       userSettings = task.getResult().toObject(UserSettings.class);
                       if (userSettings == null) {
                           userSettings = new UserSettings();
                           userSettings.setUserName("User");
                           database.set(userSettings);
                       }
                   }
                });
    }

    public static void setFirstName(String firstName) {
        userSettings.setFirstName(firstName);
        database.update("firstName", firstName);
    }
    public static void setLastName(String lastName) {
        userSettings.setLastName(lastName);
        database.update("lastName", lastName);
    }
    public static void setPhoto(String photoUri) {
        userSettings.setPhotoUri(photoUri);
        database.update("photoUri", photoUri);
    }
    public static void setUserName(String userName) {
        userSettings.setUserName(userName);
        database.update("userName", userName);
        FirebaseAuth.getInstance()
                .getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(userName).build());
    }
    public static String getUserName() {return userSettings.getUserName();}
}
