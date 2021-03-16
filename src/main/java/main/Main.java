package main;

import handler.DatabaseHandler;
import handler.LoginHandler;
import handler.PasswordHandler;
import object.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.LoginInformation;

public class Main {

    private static LoginHandler loginHandler;
    private static DatabaseHandler databaseHandler;
    private static PasswordHandler passwordHandler;
    private static Boolean loggedIn;
    private static User currentUser;

    public static void main(String[] args) {

        // Creating the objects
        loginHandler = new LoginHandler();
        databaseHandler = new DatabaseHandler();
        passwordHandler = new PasswordHandler();

        // No account should exist yet.
        System.out.println("1: " + loginHandler.login("bob", "testtest").getMessage());

        // Signs up an account. Should work since no account exists.
        System.out.println("2: " + loginHandler.signup("bob", "testtest").getMessage());

        // Should be able to login now.
        System.out.println("3: " + loginHandler.login("bob", "testtest").getMessage());

        // Should not be allowed to signup, since the account exists.
        System.out.println("4: " + loginHandler.signup("Bob", "testtest").getMessage());

        // Should not be able to login because of the incorrect password.
        System.out.println("5: " + loginHandler.login("Bob", "test1").getMessage());


        // This is how you would use this in a GUI environment.
        LoginInformation loginInformation = loginHandler.login("bob", "testtest");

        switch (loginInformation) {
            case INCORRECT_PASSWORD -> {
                // Methods to run when there is an incorrect password.
                // For example, you can display the loginInformation.getMessage() on the gui.
                System.out.println("INCORRECT PASSWORD: " + loginInformation.getMessage());
            }

            case CONNECTION_ERROR -> {
                // Methods to run when there is a connection error.
                System.out.println("CONNECTION ERROR: " + loginInformation.getMessage());
            }

            case NO_USER -> {
                // Methods to be run when there is no user that exists with that name.
                System.out.println("NO USER: " + loginInformation.getMessage());
            }

            case SUCCESSFUL_LOGIN -> {
                // Methods to be run when the user is able to login.
                System.out.println("SUCCESSFUL: " + loginInformation.getMessage());
            }
        }

        // If you are trying to get data from the user, here is an example.
        currentUser.setAge(100);
        System.out.println("The current user is " + currentUser.getAge() + " years old.");

        loginHandler.logOut(); // Logs out the current user.
    }

    /**
     * @param bool Whether or not there is a logged in {@link User}.
     */
    public static void setLoggedIn(@NotNull Boolean bool) {
        loggedIn = bool;
    }

    /**
     * @return The logged in state of any {@link User].}
     */
    public static Boolean getLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the current logged in {@link User}.
     * @param user The {@link User} to be logged in.
     */
    public static void setCurrentUser(@Nullable User user) {
        currentUser = user;
    }

    /**
     * @return The {@link User} currently logged in.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    // This is so you can get the loginHandler from any class.
    /**
     * @return The current {@link LoginHandler}.
     */
    public static LoginHandler getLoginHandler() {
        return loginHandler;
    }

    // This is so you can get the databaseHandler from any class.
    /**
     * @return The current {@link DatabaseHandler}.
     */
    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    // This is so you can get the passwordHandler from any class.
    /**
     * @return The current {@link PasswordHandler}.
     */
    public static PasswordHandler getPasswordHandler() {
        return passwordHandler;
    }

}
