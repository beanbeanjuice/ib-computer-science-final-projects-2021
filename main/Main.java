package main;

import database.SQLiteDataSource;
import database.UserHandler;

// IMPORTANT
// Go to Tools -> Preferences -> Libraries
// Then add the jars from the "Required Jars" files into there.
// This will allow the program to run.

public class Main {

    private static SQLiteDataSource sqLiteDataSource;
    private static UserHandler userHandler;

    public static void main(String[] args) {

        // Initializes it
        sqLiteDataSource = new SQLiteDataSource();
        userHandler = new UserHandler();

        // Creates the database
        sqLiteDataSource.createDatabase("database.db");

        // Create the connection to the database
        boolean connected = sqLiteDataSource.createConnection();

        if (connected) {
            // Things to do if the database is connected.
            System.out.println("Connected to the database!");
        }

        // Creates the table
        sqLiteDataSource.createUsersTable();

        userHandler.addUser("test1", "password1");

        System.out.println("User ID: " + userHandler.getUser("test1").getID()); // Should print out the ID of user "test1"
        System.out.println("Username: " + userHandler.getUser("test1").getName());
        System.out.println("Password: " + userHandler.getUser("test1").getPassword());
        //System.out.println(userHandler.getUser("test2").getID()); // Returns null because the user was never added

        userHandler.getUser("test1").updatePassword("1password");
        System.out.println("New Password: " + userHandler.getUser("test1").getPassword());

    }

    /**
     * @return The {@link SQLiteDataSource} that was created in the {@link Main} class.
     */
    public static SQLiteDataSource getSQLiteDataSource() {
        return sqLiteDataSource;
    }

    /**
     * @return The {@link UserHandler} that was created in the {@link Main} class.
     */
    public static UserHandler getUserHandler() {
        return userHandler;
    }


}
