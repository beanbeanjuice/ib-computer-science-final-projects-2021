package main;

import database.SQLiteDataSource;
import database.UserHandler;

public class Main {

    private static SQLiteDataSource sqLiteDataSource;
    private static UserHandler userHandler;

    public static void main(String[] args) {

        // Initializes it
        sqLiteDataSource = new SQLiteDataSource();
        userHandler = new UserHandler();

        // Creates the database
        sqLiteDataSource.createDatabase("database.db");

        // Creates the table
        sqLiteDataSource.createUsersTable();

        userHandler.addUser("test1", "password1");

        System.out.println(userHandler.getUser("test1").getID()); // Should print out the ID of user "test1"
        System.out.println("Username: " + userHandler.getUser("test1").getName());
        System.out.println("Password: " + userHandler.getUser("test1").getPassword());
        //System.out.println(userHandler.getUser("test2").getID()); // Returns null because the user was never added

        userHandler.getUser("test1").updateName("1test");
        System.out.println("New Username: " + userHandler.getUser("1test").getName());
        userHandler.getUser("1test").updatePassword("1password");


    }

    // Get the conenction from any class
    public static SQLiteDataSource getSQLiteDataSource() {
        return sqLiteDataSource;
    }

    public static UserHandler getUserHandler() {
        return userHandler;
    }


}
