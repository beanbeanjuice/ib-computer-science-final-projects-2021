package object;

import org.jetbrains.annotations.NotNull;

/**
 * The {@link User} that will be in the database.
 */
public class User {

    private String username;
    private String encryptedPassword;

    // Ideally, you'd have the user information like age, score, and other things on a SEPARATE database.
    private int age;

    /**
     * Creates a new {@link User}.
     * @param username The username for the {@link User}.
     * @param encryptedPassword The encrypted password for the {@link User}.
     */
    public User(@NotNull String username, @NotNull String encryptedPassword) {
        this.username = username.toLowerCase();
        this.encryptedPassword = encryptedPassword;
    }

    /**
     * @return The username for the {@link User}.
     */
    @NotNull
    public String getUsername() {
        return username;
    }

    /**
     * @return The encrypted password for the {@link User}.
     */
    @NotNull
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    /**
     * Sets the age for the {@link User}.
     * @param age The age to be set in years.
     */
    public void setAge(@NotNull Integer age) {
        this.age = age;
    }

    /**
     * @return The current age of the {@link User} in years.
     */
    @NotNull
    public Integer getAge() {
        return age;
    }


}
