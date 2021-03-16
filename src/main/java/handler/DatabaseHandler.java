package handler;

import main.Main;
import object.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.AccountInformation;

import java.util.ArrayList;

/**
 * The {@link DatabaseHandler} class.
 */
public class DatabaseHandler {

    private ArrayList<User> database;

    /**
     * Creates a new {@link DatabaseHandler} object.
     */
    public DatabaseHandler() {
        database = new ArrayList<>();
    }

    /**
     * Adds a new {@link User} to the database.
     * @param username The username of the {@link User}.
     * @param password The password of the {@link User}.
     * @return Whether or not adding to the database was successful.
     */
    @NotNull
    public Boolean addToDatabase(@NotNull String username, @NotNull String password) {

        String encryptedPassword = Main.getPasswordHandler().encryptPassword(password);

        return database.add(new User(username, encryptedPassword));
    }

    /**
     * Adds a new {@link User} to the database.
     * @param user The temporary {@link User}.
     * @return Whether or not adding to the database was successful.
     */
    @NotNull
    public Boolean addToDatabase(@NotNull User user) {
        return database.add(user);
    }

    /**
     * Removes a {@link User} from the database.
     * @param user The {@link User} in the database.
     * @return Whether or not removing the {@link User} from the database was successful.
     */
    @NotNull
    public Boolean removeFromDatabase(@NotNull User user) {
        return database.remove(user);
    }

    /**
     * Sees if a specified username is already used.
     * @param username The username that is being searched.
     * @return Whether or not a {@link User} with that username already exists.
     */
    @NotNull
    public Boolean isInDatabase(@NotNull String username) {

        for (User databaseUser : database) {
            if (databaseUser.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;

    }

    /**
     * Gets a {@link User} within the database.
     * @param username The username of the {@link User}.
     * @return The {@link User} within the database. Returns null if no user exists.
     */
    @Nullable
    public User getUser(@NotNull String username) {

        for (User user : database) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;

    }



}
