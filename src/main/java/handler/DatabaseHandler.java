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
            if (databaseUser.getUsername().equals(username)) {
                return true;
            }
        }

        return false;

    }

    @NotNull
    public AccountInformation changeUsername(@NotNull String currentPassword, @NotNull String newUsername) {

        User currentUser = Main.getCurrentUser();

        String encryptedPassword = Main.getPasswordHandler().encryptPassword(currentPassword);
        String encryptedPasswordInDatabase = currentUser.getEncryptedPassword();

        if (Main.getPasswordHandler().comparePasswords(encryptedPassword, encryptedPasswordInDatabase)) {

            if (currentUser.getUsername().equalsIgnoreCase(newUsername)) {
                return AccountInformation.SAME_USERNAME;
            }

            User newUser = currentUser;
            newUser.setUsername(newUsername);

            if (!removeFromDatabase(currentUser)) {
                return AccountInformation.CONNECTION_ERROR;
            }

            if (!addToDatabase(newUser)) {
                return AccountInformation.CONNECTION_ERROR;
            }

            Main.setCurrentUser(newUser);
            Main.setLoggedIn(true);
            return AccountInformation.SUCCESS;

        }

        return AccountInformation.INCORRECT_PASSWORD;
    }

    @Nullable
    public User getUser(@NotNull String username) {

        for (User user : database) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;

    }



}
