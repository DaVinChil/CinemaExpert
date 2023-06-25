package hse.nativ.speakers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import hse.nativ.speakers.database_classes.UserSettings;

public class CurrentUser {
    private CurrentUser(){};

    public static final String USER_ID_DOCUMENT_TAG = "USER_ID_DOCUMENT";
    public static final String USER_NAME_TAG = "USER_NAME";
    public static final String USER_EMAIL_TAG = "USER_EMAIL";
    public static final String USER_PASSWORD_TAG = "USER_PASSWORD";

    private static String USER_ID_DOCUMENT = null;
    private static String USER_EMAIL = null;
    private static String USER_PASSWORD = null;
    private static DocumentReference database = null;
    private static UserSettings userSettings = new UserSettings();

    public static String getUserPassword() {
        return USER_PASSWORD;
    }
    public static void setUserPassword(String userPassword) {
        USER_PASSWORD = userPassword;
    }

    public static void connectToDatabase() {
        CollectionReference accounts = FirebaseFirestore.getInstance().collection("AccountSettings");
        accounts.document(userSettings.getUserEmail())
                .get()
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       database = accounts.document(userSettings.getUserEmail());
                   }
                });
    }

    public static void updateUserSettings() {
        new Thread(() -> {
            while (database == null);
            database.set(userSettings);
            FirebaseAuth.getInstance()
                    .getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder()
                            .setDisplayName(userSettings.getUserName()).build());
        }).start();
    }

    public static void uploadUserSettings() {
        new Thread(() -> {
            while (database == null);
            database.get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            userSettings = task.getResult().toObject(UserSettings.class);
                        }
                    });
        }).start();
    }

    public static void setFirstName(String firstName) {
        userSettings.setFirstName(firstName);
    }
    public static void setLastName(String lastName) {
        userSettings.setLastName(lastName);
    }
    public static void setPhoto(String photoUri) {
        userSettings.setPhotoUri(photoUri);
    }
    public static String getPhoto() {return userSettings.getPhotoUri();}

    public static void setUserName(String userName) {
        userSettings.setUserName(userName);
    }
    public static String getUserName() {return userSettings.getUserName();}

    public static String getUserIdDocument() {
        return USER_ID_DOCUMENT;
    }
    public static void setUserIdDocument(String userIdDocument) {USER_ID_DOCUMENT = userIdDocument;}

    public static String getUserEmail() {
        return USER_EMAIL;
    }
    public static void setUserEmail(String userEmail) {
        userSettings.setUserEmail(userEmail);
    }
}
