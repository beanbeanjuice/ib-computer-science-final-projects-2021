package object;

import org.jetbrains.annotations.NotNull;

public class User {

    private String username;
    private String encryptedPassword;

    public User(@NotNull String username, @NotNull String encryptedPassword) {
        this.username = username.toLowerCase();
        this.encryptedPassword = encryptedPassword;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    @NotNull
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(@NotNull String newEncryptedPassword) {
        encryptedPassword = newEncryptedPassword;
    }

    public void setUsername(@NotNull String newUsername) {
        username = newUsername;
    }


}
