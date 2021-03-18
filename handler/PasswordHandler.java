package handler;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

/**
 * The class used for handling passwords.
 */
public class PasswordHandler {

    /**
     * Encrypts the password entered.
     * @param password The password to be encrypted.
     * @return The encrypted, sha512 password.
     */
    @NotNull
    public String encryptPassword(@NotNull String password) {
        return DigestUtils.sha3_512Hex(password);
    }

    /**
     * Compares two passwords together.
     * @param encryptedPassword1 The first password to compare.
     * @param encryptedPassword2 The second password to compare.
     * @return Whether or not the passwords match.
     */
    @NotNull
    public Boolean comparePasswords(@NotNull String encryptedPassword1, @NotNull String encryptedPassword2) {
        return (encryptedPassword1.equals(encryptedPassword2));
    }

}
