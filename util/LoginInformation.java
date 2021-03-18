package util;

/**
 * A static {@link LoginInformation} class containing the Login Information.
 */
public enum LoginInformation {

    NO_USER("There is no account in the database."),
    INCORRECT_PASSWORD("You have entered the incorrect password."),
    SUCCESSFUL_LOGIN("You have been successfully logged in."),
    CONNECTION_ERROR("Unable to connect to the database.");

    private final String message;

    /**
     * Creates a new static {@link LoginInformation} class.
     * @param message Returns the message associated with that class.
     */
    LoginInformation(String message) {
        this.message = message;
    }

    /**
     * Gets the message associated with the class.
     * @return The message to be shown when the user tries to login.
     */
    public String getMessage() {
        return message;
    }

}
