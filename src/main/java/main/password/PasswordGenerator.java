package main.password;

import main.Main;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * A password generator that can be used to generate passwords.
 */
public class PasswordGenerator {

    private int length;
    private boolean includeSymbols;
    private boolean includeNumbers;
    private boolean useDictionary;

    private boolean usedSymbol;
    private boolean usedNumber;
    private boolean usedDictionary;

    private String symbols = "!@#$%&*()";
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String numbers = "0123456789";

    /**
     * Generate a new {@link PasswordGenerator} object.
     * Symbols take precedence over numbers.
     * Numbers take precedence over the dictionary.
     * If a length is not chosen, a default length of 10 is used.
     * @param length The length of the passwords to be generated.
     * @param includeSymbols Whether or not to randomly include symbols.
     * @param useDictionary Choose whether or not to include dictionary words.
     */
    public PasswordGenerator(@NotNull Integer length, @NotNull Boolean includeSymbols, @NotNull Boolean includeNumbers, @NotNull Boolean useDictionary) {
        this.length = length;

        if (length < 10) {
            this.length = 10;
        }

        this.includeSymbols = includeSymbols;
        this.includeNumbers = includeNumbers;
        this.useDictionary = useDictionary;
    }

    // If a length is not specified, it creates a PasswordGenerator object with a length of 10.
    public PasswordGenerator(@NotNull Boolean includeSymbols, @NotNull Boolean includeNumbers, @NotNull Boolean useDictionary) {
        length = 10;

        this.includeSymbols = includeSymbols;
        this.includeNumbers = includeNumbers;
        this.useDictionary = useDictionary;
    }

    /**
     * Gets a new generated password.
     * @return Returns a new password generated from the specified length.
     */
    @NotNull
    public String generatePassword() {

        String[] passwordArray = new String[length];
        int count = 0;

        usedSymbol = false;
        usedNumber = false;
        usedDictionary = false;

        Random random = new Random();

        while (count < length) {

            // Chooses a random number between 1 and 4.
            int selection = random.nextInt(4) + 1;

            // Symbols
            // Numbers
            // Dictionary
            // Letters
            switch (selection) {
                case 1 -> { // If the password should include a symbol, add one.
                    if (includeSymbols) {
                        passwordArray[count] = chooseRandomSymbol();
                        count++;
                    }
                }
                case 2 -> { // If the password should include a number, add one.
                    if (includeNumbers) {
                        passwordArray[count] = chooseRandomNumber();
                        count++;
                    }
                }
                case 3 -> {
                    if (useDictionary) { // If the password should include a word from the dictionary, add one.

                        int randomNum = random.nextInt((length - count)) + 1;

                        // Used to make sure that if you are making a password with length 1000000,
                        // it doesn't try to find a word that has 1000000 characters.
                        if (Main.getPasswordDictionary().getDictionary().get(randomNum) == null) {

                            // This makes sure it only chooses a password which its length exists.
                            // For example, no word with length of 26 exists in the dictionary.csv file.
                            // However, the max length of a word in the dictionary.csv is above 26.
                            while (Main.getPasswordDictionary().getDictionary().get(randomNum) == null) {
                                randomNum = random.nextInt((Main.getPasswordDictionary().getMaxLength())) + 1;
                            }
                        }

                        // Once it has found a length that works, choose a random length.
                        String randomWord = chooseRandomWord(randomNum);

                        // This makes sure it adds the letters of the word to the array.
                        // Not the entire word to the index.
                        for (int i = 0; i < randomNum; i++) {
                            passwordArray[count++] = String.valueOf(randomWord.charAt(i));
                        }
                    }
                }
                case 4 -> { // Add a random letter to the password.

                    passwordArray[count] = chooseRandomLetter();
                    count++;

                }
            }

        }

        // The password will be an array. It needs to be converted to a string.
        String password = convertToString(passwordArray);

        // Checks to see if all criteria is met.
        if (!checkPassword()) {
            password = generatePassword();
        }

        return password;
    }

    @NotNull
    private Boolean checkPassword() {

        if (includeSymbols) {
            if (!usedSymbol) {
                return false;
            }
        }

        if (includeNumbers) {
            if (!usedNumber) {
                return false;
            }
        }

        if (useDictionary) {
            return usedDictionary;
        }

        return true;

    }

    @NotNull
    private String convertToString(@NotNull String[] passwordArray) {
        StringBuilder password = new StringBuilder();

        // Goes through every character in the passwordArray
        // And appends it to the password StringBuilder.
        for (String string : passwordArray) {
            password.append(string);
        }

        return password.toString();
    }

    @NotNull
    private String chooseRandomWord(@NotNull Integer spotsLeft) {
        Random random = new Random();
        ArrayList<String> wordArrayList = Main.getPasswordDictionary().getDictionary().get(spotsLeft);

        usedDictionary = true;

        if (wordArrayList.size() == 1) {
            return randomUppercase(wordArrayList.get(0));
        } else {
            int randomNum = random.nextInt((wordArrayList.size() - 1)) + 1;

            return randomUppercase(wordArrayList.get(randomNum));
        }
    }

    @NotNull
    private String chooseRandomNumber() {
        Random random = new Random();

        usedNumber = true;

        return String.valueOf(numbers.charAt(random.nextInt(numbers.length() - 1) + 1));
    }

    @NotNull
    private String chooseRandomSymbol() {

        Random random = new Random();

        usedSymbol = true;

        return String.valueOf(symbols.charAt(random.nextInt(symbols.length() - 1) + 1));
    }

    @NotNull
    private String chooseRandomLetter() {
        Random random = new Random();
        int randomNum = random.nextInt(alphabet.length() - 1) + 1;

        return randomUppercase(String.valueOf(alphabet.charAt(randomNum)));
    }

    @NotNull
    private String randomUppercase(@NotNull String word) {
        Random random = new Random();
        String[] characterArray = word.split("");
        StringBuilder wordBuild = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (random.nextBoolean()) {
                characterArray[i] = characterArray[i].toUpperCase();
            }

            wordBuild.append(characterArray[i]);
        }

        return wordBuild.toString();
    }

}
