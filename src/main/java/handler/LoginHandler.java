package handler;

import main.Main;
import object.User;
import org.jetbrains.annotations.NotNull;
import util.LoginInformation;
import util.SignupInformation;

public class LoginHandler {

    @NotNull
    public LoginInformation login(@NotNull String username, @NotNull String password) {

        String encryptedPassword = Main.getPasswordHandler().encryptPassword(password);

        User temporaryUser = new User(username, encryptedPassword);

        if (!Main.getDatabaseHandler().isInDatabase(temporaryUser)) {
            return LoginInformation.NO_USER;
        }

        User user = Main.getDatabaseHandler().getUser(temporaryUser);

        if (!Main.getPasswordHandler().comparePasswords(encryptedPassword, user.getEncryptedPassword())) {
            return LoginInformation.INCORRECT_PASSWORD;
        }

        return LoginInformation.SUCCESSFUL_LOGIN;

    }

    @NotNull
    public SignupInformation signup(@NotNull String username, @NotNull String password) {

        String encryptedPassword = Main.getPasswordHandler().encryptPassword(password);
        User temporaryUser = new User(username, password);

        if (Main.getDatabaseHandler().isInDatabase(temporaryUser)) {
            return SignupInformation.USER_ALREADY_EXISTS;
        }


        if (Main.getDatabaseHandler().addToDatabase(username, password)) {
            return SignupInformation.SUCCESSFUL_SIGNUP;
        }

        return SignupInformation.CONNECTION_ERROR;

    }

}
