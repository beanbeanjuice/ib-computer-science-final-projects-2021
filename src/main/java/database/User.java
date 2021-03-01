package database;

import main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// This is an object that corresponds with a single row in the "users" table in the "database.db" file.
// This is just an example, there can be a "Game" object or "Settings" object.
public class User {

    private int id;
    private String name;
    private String password; // In an actual database, this will be the password hash

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean updateName(String name) {
        this.name = name;

        User user = Main.getUserHandler().getUser(name);
        // First, check to see if the username is already taken
        if (user != null) {
            return false;
        }

        // Next, update it.

        try {
            Connection connection = Main.getSQLiteDataSource().getConnection();

            // The question mark is a essentially a variable placeholder
            // This is done to prevent, the unlikely, SQL injection
            String arguments = "UPDATE users SET username = ? WHERE username = '" + name + "';";

            // Since we want to edit some things in the statement, we use prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(arguments);

            preparedStatement.setString(1, name);

            // Since we are returning something we execute the query
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getPassword() {
        return password;
    }

    public boolean updatePassword(String password) {
        this.password = password;

        try {
            Connection connection = Main.getSQLiteDataSource().getConnection();

            // The question mark is a essentially a variable placeholder
            // This is done to prevent, the unlikely, SQL injection
            String arguments = "UPDATE users SET password = ? WHERE username = '" + name + "';";

            // Since we want to edit some things in the statement, we use prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(arguments);

            preparedStatement.setString(1, password);

            // Since we are returning something we execute the query
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
