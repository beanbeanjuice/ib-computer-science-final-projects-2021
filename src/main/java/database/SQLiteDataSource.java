package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDataSource {

    private final HikariConfig config;
    private HikariDataSource dataSource;
    

    // Creates a new config
    public SQLiteDataSource() {
        config = new HikariConfig();
    }

    public void createDatabase(String filePath) {

        try {

            // This doesn't actually create the file
            // This sets it as an object
            final File dbFile = new File(filePath);

            // Checks if the file exists
            if (!dbFile.exists()) {

                // If it doesn't exist, create a new file
                if (dbFile.createNewFile()) {

                    // Do stuff if the database is able to be created
                    System.out.println("Created database file.");

                } else {

                    // Do stuff if the database isn't able to be created
                    System.out.println("Could not create database file.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        config.setJdbcUrl("jdbc:sqlite:database.db"); // "JAR:TYPE:DATABASE NAME"
        config.setConnectionTestQuery("SELECT 1"); // Tries to select 1 from the database and sees if it fails

        // Found on the HikariCP github (https://github.com/brettwooldridge/HikariCP)
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // Creates a new SQLite database with the settings above
        dataSource = new HikariDataSource(config);

        connection = dataSource.getConnection();

    }

    public void createUsersTable() {

        try {
            Connection connection = getConnection(); // Gets a "connection" to the SQLite database

            // Gets a statement object that can be executed
            Statement statement = connection.createStatement();

            // Language = SQLite -- SQL each has similar yet different language
            // This is specifically for SQLite
            // SQLite Data Types can be found here (https://www.sqlite.org/datatype3.html)
            String arguments = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "password TEXT);";

            statement.execute(arguments);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Retrieves the actual SQLite database connection
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
