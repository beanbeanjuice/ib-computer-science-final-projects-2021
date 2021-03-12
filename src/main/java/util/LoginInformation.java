package util;

public enum LoginInformation {

    NO_USER("There is no account in the database."),
    INCORRECT_PASSWORD("You have entered the incorrect password."),
    SUCCESSFUL_LOGIN("You have been successfully logged in."),
    CONNECTION_ERROR("Unable to connect to the database.");

    private final String message;

    LoginInformation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
