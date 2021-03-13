package util;

public enum AccountInformation {

    INCORRECT_PASSWORD("Sorry, the password you entered was incorrect."),
    SAME_PASSWORD("Sorry, your new password cannot be the same as your old password."),
    SAME_USERNAME("Sorry, your new username cannot be the same as your old username."),
    SUCCESS("You have successfully updated your account."),
    CONNECTION_ERROR("There was an error retrieving the database.");

    private final String message;

    AccountInformation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
