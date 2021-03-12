package main;

import handler.DatabaseHandler;
import handler.LoginHandler;
import handler.PasswordHandler;
import object.User;

public class Main {

    private static LoginHandler loginHandler;
    private static DatabaseHandler databaseHandler;
    private static PasswordHandler passwordHandler;
    private static Boolean loggedIn;

    public static void main(String[] args) {

        // Creating the objects
        loginHandler = new LoginHandler();
        databaseHandler = new DatabaseHandler();
        passwordHandler = new PasswordHandler();

        System.out.println("1: " + loginHandler.login("bob", "testtest").getMessage());
        System.out.println("2: " + loginHandler.signup("bob", "testtest").getMessage());
        System.out.println("3: " + loginHandler.login("bob", "testtest").getMessage());

    }

    public static Boolean isLoggedIn() {
        return loggedIn;
    }

    // This is so you can get the loginHandler from any class.
    public static LoginHandler getLoginHandler() {
        return loginHandler;
    }

    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public static PasswordHandler getPasswordHandler() {
        return passwordHandler;
    }

}
