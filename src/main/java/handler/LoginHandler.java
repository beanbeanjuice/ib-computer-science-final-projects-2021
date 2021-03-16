package handler;

import main.Main;
import object.User;
import org.jetbrains.annotations.NotNull;
import util.LoginInformation;
import util.SignupInformation;

public class LoginHandler {

    /**
     * Used to login a user.
     * @param username The username of the {@link User}.
     * @param password The un-encrypted password of the {@link User}.
     * @return The {@link LoginInformation} containing the enum.
     */
    @NotNull
    public LoginInformation login(@NotNull String username, @NotNull String password) {

        // To login, we compare the encrypted hashes to each other.
        // Therefore, we need to convert the string password into a hash.
        String encryptedPassword = Main.getPasswordHandler().encryptPassword(password);

        // First, check if an account with the username does exist.
        // If it doesn't exist, there is no need to continue.
        // Inform the user if the username does not exist.
        if (!Main.getDatabaseHandler().isInDatabase(username)) {
            return LoginInformation.NO_USER;
        }

        // Since it does exist, get the user.
        User user = Main.getDatabaseHandler().getUser(username);

        // Now we compare the password the user entered to the password stored in the database.
        // Inform the user if the passwords are incorrect.
        if (!Main.getPasswordHandler().comparePasswords(encryptedPassword, user.getEncryptedPassword())) {
            return LoginInformation.INCORRECT_PASSWORD;
        }

        // If everything passes, set the current user to the user
        // and set loggedIn to true.
        Main.setCurrentUser(user);
        Main.setLoggedIn(true);

        // Inform the user that there was a successful login.
        return LoginInformation.SUCCESSFUL_LOGIN;

    }

    /**
     * Used to sign the {@link User} in.
     * @param username The username of the {@link User}.
     * @param password The unencrypted password of the {@link User}.
     * @return The {@link SignupInformation} enum.
     */
    @NotNull
    public SignupInformation signup(@NotNull String username, @NotNull String password) {

        // If an account with that username already exists, inform the user they cannot signup.
        if (Main.getDatabaseHandler().isInDatabase(username)) {
            return SignupInformation.USER_ALREADY_EXISTS;
        }

        // If an account doesn't exist, make one.
        if (Main.getDatabaseHandler().addToDatabase(username, password)) {

            // If the account is successfully created, then retrieve that account and set it to logged in.
            Main.setCurrentUser(Main.getDatabaseHandler().getUser(username));
            Main.setLoggedIn(true);

            // Inform the user that the signup was successful.
            return SignupInformation.SUCCESSFUL_SIGNUP;
        }

        // If something wrong occurs, ie losing an internet connection, inform the user.
        return SignupInformation.CONNECTION_ERROR;

    }

}
