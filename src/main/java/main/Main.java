package main;

import handler.DatabaseHandler;
import handler.LoginHandler;
import handler.PasswordHandler;

public class Main {

    private static LoginHandler loginHandler;
    private static DatabaseHandler databaseHandler;
    private static PasswordHandler passwordHandler;

    public static void main(String[] args) {

        // Creating the objects
        loginHandler = new LoginHandler();
        databaseHandler = new DatabaseHandler();
        passwordHandler = new PasswordHandler();

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
