package util;

/**
 * A static {@link SignupInformation} class containing the Signup Information.
 */
public enum SignupInformation {

    USER_ALREADY_EXISTS("An account with this username already exists."),
    SUCCESSFUL_SIGNUP("You have been successfully signed up!"),
    CONNECTION_ERROR("Unable to connect to the database.");

    private final String message;

    /**
     * Creates a new static {@link SignupInformation} class.
     * @param message Returns the message associated with that class.
     */
    SignupInformation(String message) {
        this.message = message;
    }

    /**
     * Gets the message associated with the class.
     * @return The message to be shown when the user tries to signup.
     */
    public String getMessage() {
        return message;
    }

}
