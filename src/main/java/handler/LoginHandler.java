package handler;

import org.apache.commons.codec.digest.DigestUtils;
import util.LoginInformation;
import util.SignupInformation;

import java.util.HashMap;

public class LoginHandler {

    // This is a pseudo-database. Should usually be a MySQL or Google Sheets connection.
    private HashMap<String, String> database;

    public LoginHandler() {
        database = new HashMap<>();
    }

    public LoginInformation logIn(String username, String password) {
        username = username.toLowerCase(); // username should be conformed, or at least .equalsIgnoreCase() should be used

        // You need to compare the SHA hashes TO the one in the database.
        String newEncryptedPassword = DigestUtils.sha512Hex(password);


        try {
            // Get the encrypted HASH within the database.
            String encryptedPasswordInDatabase = database.get(username);

            // Checks to see if an account with that username exists
            if (encryptedPasswordInDatabase == null) {
                // If no username exists, return the no user enum
                return LoginInformation.NO_USER;
            }

            if (encryptedPasswordInDatabase.equals(newEncryptedPassword)) {
                // Login if the password hashes match
                return LoginInformation.SUCCESSFUL_LOGIN;
            } else {
                // Don't login if they don't
                return LoginInformation.INCORRECT_PASSWORD;
            }
        } catch (Exception e) {

            // Additionally, when connecting to an SQL server,
            // if the server cannot be reached, return a CONNECTION_ERROR.
            // For example, an SQL connection may produce an SQLException
            return LoginInformation.CONNECTION_ERROR;
        }

    }

    public SignupInformation signUp(String username, String password) {
        username = username.toLowerCase();

        String newEncryptedPassword = DigestUtils.sha512Hex(password);

        try {
            // Check to see if an account exists already
            if (database.get(username) != null) {
                // Return an enum for a user already existing
                return SignupInformation.USER_ALREADY_EXISTS;
            }

            // If the account doesn't exist, add the new account to the database
            // WITH the encrypted password.
            database.put(username, newEncryptedPassword);
            return SignupInformation.SUCCESSFUL_SIGNUP;


        } catch (Exception e) {

            // Produce an Exception if there is no available connection
            // to be made.
            return SignupInformation.CONNECTION_ERROR;
        }

    }

}
