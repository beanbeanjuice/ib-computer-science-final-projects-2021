package handler;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

public class PasswordHandler {

    @NotNull
    public String encryptPassword(@NotNull String password) {
        return DigestUtils.sha512Hex(password);
    }

    @NotNull
    public Boolean comparePasswords(@NotNull String unEncryptedPassword, @NotNull String encryptedPasswordInDatabase) {
        return (DigestUtils.sha512Hex(unEncryptedPassword).equals(encryptedPasswordInDatabase));
    }

}
