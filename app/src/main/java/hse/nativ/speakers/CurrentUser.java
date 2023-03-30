package hse.nativ.speakers;

public class CurrentUser {
    private CurrentUser(){};

    public static final String USER_ID_DOCUMENT_TAG = "USER_ID_DOCUMENT";
    public static final String USER_NAME_TAG = "USER_NAME";
    public static final String USER_EMAIL_TAG = "USER_EMAIL";
    public static final String USER_PASSWORD_TAG = "USER_PASSWORD";

    private static String USER_ID_DOCUMENT = null;
    private static String USER_NAME = null;
    private static String USER_EMAIL = null;
    private static String USER_PASSWORD = null;

    public static String getUserIdDocument() {
        return USER_ID_DOCUMENT;
    }

    public static void setUserIdDocument(String userIdDocument) {
        USER_ID_DOCUMENT = userIdDocument;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static void setUserName(String userName) {
        USER_NAME = userName;
    }

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
}
