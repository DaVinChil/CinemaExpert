package hse.nativ.speakers;

import java.util.regex.Pattern;

public class FireStoreTools {
    private FireStoreTools(){};

    public static final String USERS_COLLECTION = "Users";
    public static final String USER_ID_DOCUMENT_TAG = "USER_ID_DOCUMENT";
    public static final String USER_NAME_TAG = "USER_NAME";
    public static final String USER_EMAIL_TAG = "USER_EMAIL";
    public static final String USER_PASSWORD_TAG = "USER_PASSWORD";

    private static Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");


    public static boolean isCorrectEmailInput(String email) {
        return emailPattern.matcher(email).matches();
    }
}
