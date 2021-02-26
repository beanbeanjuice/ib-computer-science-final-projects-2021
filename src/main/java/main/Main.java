package main;

import database.SQLiteDataSource;
import database.UserHandler;

public class Main {

    private static final String username = "ENTER USERNAME HERE"; // MySQL Server Username
    private static final String password = "ENTER PASSWORD HERE"; // MySQL Server Password
    private static final String url = "ENTER URL HERE"; // MySQL Server URL

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

        System.out.println(userHandler.getUser("test1").getID());
        System.out.println(userHandler.getUser("test2").getID()); // Returns null



    }

    // Get the conenction from any class
    public static SQLiteDataSource getSQLiteDataSource() {
        return sqLiteDataSource;
    }


}
