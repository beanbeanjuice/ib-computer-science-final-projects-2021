package password;

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

    // Symbols that can be used in passwords. Fully customisable.
    private String symbols = "!@#$%&*()";

    // The alphabet in lowercase. Fully customisable.
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";

    // Numbers that can be used. Fully customisable.
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

        // Minimum Password Length. Can be changed to your liking.
        if (length < 10) {
            this.length = 10;
        }

        this.includeSymbols = includeSymbols;
        this.includeNumbers = includeNumbers;
        this.useDictionary = useDictionary;
    }

    /**
     * Generate a new {@link PasswordGenerator} object.
     * Symbols take precedence over numbers.
     * Numbers take precedence over the dictionary.
     * If a length is not chosen, a default length of 10 is used.
     * @param includeSymbols Whether or not to randomly include symbols.
     * @param useDictionary Choose whether or not to include dictionary words.
     */
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
                case 1: { // If the password should include a symbol, add one.
                    if (includeSymbols) {
                        passwordArray[count] = chooseRandomSymbol();
                        count++;
                    }
                    break;
                }
                case 2: { // If the password should include a number, add one.
                    if (includeNumbers) {
                        passwordArray[count] = chooseRandomNumber();
                        count++;
                    }
                    break;
                }
                case 3: {
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
                    break;
                }
                case 4: { // Add a random letter to the password.

                    passwordArray[count] = chooseRandomLetter();
                    count++;
                    break;
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

    /**
     * @return Whether or not the password generated follows all of the specifications.
     */
    @NotNull
    private Boolean checkPassword() {

        // Checks if the password should, and does include symbols.
        if (includeSymbols) {
            if (!usedSymbol) {
                return false;
            }
        }

        // Checks of the password should, and does include numbers.
        if (includeNumbers) {
            if (!usedNumber) {
                return false;
            }
        }

        // Checks if the password should, and does include words from the specified dictionary file.
        if (useDictionary) {
            return usedDictionary;
        }

        return true;

    }

    /**
     * Converts the password array to a {@link String}.
     * @param passwordArray The {@link String[]} needed to convert to a {@link String}.
     * @return The converted {@link String}.
     */
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

    /**
     * Chooses a random word that is less than the amount of spots left.
     * @param spotsLeft The amount of spots left in the password {@link String[]}.
     * @return The word in the dictionary {@link java.io.File File} that was randomly chosen.
     */
    @NotNull
    private String chooseRandomWord(@NotNull Integer spotsLeft) {
        Random random = new Random();

        // Retrieves the ArrayList of words that have that many spots left.
        ArrayList<String> wordArrayList = Main.getPasswordDictionary().getDictionary().get(spotsLeft);

        usedDictionary = true;

        // Base case. If the array list size is 1 word, choose that 1 word.
        if (wordArrayList.size() == 1) {
            return randomUppercase(wordArrayList.get(0));
        } else {
            int randomNum = random.nextInt((wordArrayList.size() - 1)) + 1;

            return randomUppercase(wordArrayList.get(randomNum));
        }
    }

    /**
     * Chooses a random number between 0-9.
     * @return A {@link String} version of a random number.
     */
    @NotNull
    private String chooseRandomNumber() {
        Random random = new Random();

        usedNumber = true;

        // Returns a random number from the numbers String.
        return String.valueOf(numbers.charAt(random.nextInt(numbers.length() - 1) + 1));
    }

    /**
     * Chooses a random symbol specified in the variables.
     * @return A {@link String} version of a random symbol.
     */
    @NotNull
    private String chooseRandomSymbol() {

        Random random = new Random();

        usedSymbol = true;

        // Returns a random symbol from the symbols String.
        return String.valueOf(symbols.charAt(random.nextInt(symbols.length() - 1) + 1));
    }

    /**
     * Chooses a random letter in the alphabet.
     * @return A {@link String} version of a random letter.
     */
    @NotNull
    private String chooseRandomLetter() {
        Random random = new Random();

        // Chooses a random letter.
        int randomNum = random.nextInt(alphabet.length() - 1) + 1;

        return randomUppercase(String.valueOf(alphabet.charAt(randomNum)));
    }

    /**
     * Goes through a {@link String} and randomly uppercases some letters.
     * @param word The {@link String} to randomly uppercase.
     * @return The randomly uppercased {@link String}.
     */
    @NotNull
    private String randomUppercase(@NotNull String word) {
        Random random = new Random();

        // Splits the character array into individual characters.
        String[] characterArray = word.split("");

        // Creates a new StringBuilder.
        StringBuilder wordBuild = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (random.nextBoolean()) {
                characterArray[i] = characterArray[i].toUpperCase();
            }

            // Appends a character to the StringBuilder.
            wordBuild.append(characterArray[i]);
        }

        // Returns the randomly uppercased String.
        return wordBuild.toString();
    }

}
