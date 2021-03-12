package util;

public enum SignupInformation {

    USER_ALREADY_EXISTS("An account with this username already exists."),
    SUCCESSFUL_SIGNUP("You have been successfully signed up!"),
    CONNECTION_ERROR("Unable to connect to the database.");

    private final String message;

    SignupInformation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
