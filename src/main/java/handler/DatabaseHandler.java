package handler;

import main.Main;
import object.User;
import org.jetbrains.annotations.NotNull;

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
    public Boolean removeFromDatabase(@NotNull User user) {
        return database.remove(user);
    }

    @NotNull
    public Boolean isInDatabase(@NotNull User user) {

        for (User databaseUser : database) {
            if (databaseUser.getUsername().equals(user.getUsername())) {
                return true;
            }
        }

        return false;

    }

    public User getUser(@NotNull User temporaryUser) {

        for (User user : database) {
            if (user.getUsername().equals(temporaryUser.getUsername())) {
                return user;
            }
        }

        return null;

    }



}
