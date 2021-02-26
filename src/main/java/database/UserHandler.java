package database;

import main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHandler {

    public User getUser(String username) {

        try {

            Connection connection = Main.getSQLiteDataSource().getConnection();

            // The question mark is a essentially a variable placeholder
            // This is done to prevent, the unlikely, SQL injection
            String arguments = "SELECT * FROM users WHERE username = ?;";

            // Since we want to edit some things in the statement, we use prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(arguments);

            preparedStatement.setString(1, username);

            // Since we are returning something we execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt("id");
                // We already have the username, so no need to get it again
                String password = resultSet.getString("password");

                return new User(id, username, password);
            } else {

                return null;

            }

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }

    }

    public boolean addUser(String username, String password) {

        try {
            Connection connection = Main.getSQLiteDataSource().getConnection();

            String arguments = "INSERT INTO users(username, password) VALUES(?, ?);";

            // Again, prepare the statement to be used with different variables
            PreparedStatement preparedStatement = connection.prepareStatement(arguments);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Since we aren't returning anything, we execute it.
            preparedStatement.execute();
            return true;


        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

}
