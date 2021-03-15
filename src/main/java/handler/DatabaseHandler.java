package handler;

import main.Main;
import object.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.AccountInformation;

import java.util.ArrayList;

public class DatabaseHandler {

    private ArrayList<User> database;

    public DatabaseHandler() {
        database = new ArrayList<>();
    }

    @NotNull
    public Boolean addToDatabase(@NotNull String username, @NotNull String password) {

        String encryptedPassword = Main.getPasswordHandler().encryptPassword(password);

        return database.add(new User(username, encryptedPassword));
    }

    @NotNull
    public Boolean addToDatabase(@NotNull User user) {
        return database.add(user);
    }

    @NotNull
    public Boolean removeFromDatabase(@NotNull User user) {
        return database.remove(user);
    }

    @NotNull
    public Boolean isInDatabase(@NotNull String username) {

        for (User databaseUser : database) {
            if (databaseUser.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;

    }

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
