package main.password;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

/**
 * A password generator that can be used to generate passwords.
 */
public class PasswordGenerator {

    int length;
    boolean useDictionary;

    /**
     * Generate a new {@link PasswordGenerator} object.
     * @param length The length of the passwords to be generated.
     * @param useDictionary Choose whether or not to include dictionary words.
     */
    public PasswordGenerator(@NotNull Integer length, @NotNull Boolean useDictionary) {
        this.length = length;
        this.useDictionary = useDictionary;
    }

    /**
     * Gets a new generated password.
     * @return Returns a new password generated from the specified length.
     */
    @NotNull
    public String generatePassword() {
        return "test"; // Create password here
    }

}
