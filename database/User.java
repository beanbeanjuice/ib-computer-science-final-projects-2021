package database;

import main.Main;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// This is an object that corresponds with a single row in the "users" table in the "database.db" file.
// This is just an example, there can be a "Game" object or "Settings" object.

/**
 * A user class that is used as an example of retrieving/adding users from the {@link UserHandler}.
 */
public class User {

    private int id;
    private String name;
    private String password; // In an actual database, this will be the password hash

    /**
     * Create a new {@link User} object.
     * @param id The {@link User} object's id.
     * @param name The {@link User} object's name.
     * @param password The {@link User} object's password (unencrypted).
     */
    public User(@NotNull Integer id, @NotNull String name, @NotNull String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * @return The ID of the specified {@link User}.
     */
    @NotNull
    public Integer getID() {
        return id;
    }

    /**
     * @return The ID of the specified {@link User}.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * @return The (unencrypted) password of the specified {@link User}.
     */
    @NotNull
    public String getPassword() {
        return password;
    }

    /**
     * Updates the specified {@link User}'s password.
     * @param password The new password for the {@link User}.
     * @return Whether or not changing the password was successful.
     */
    @NotNull
    public Boolean updatePassword(@NotNull String password) {
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
