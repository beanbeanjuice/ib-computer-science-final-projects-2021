package main.password;

import main.Main;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A class used for PasswordDictionary
 */
public class PasswordDictionary {

    private ArrayList<String> initialDictionary;

    private HashMap<Integer, ArrayList<String>> dictionary;
    private File dictionaryFile;

    private int maxLength = 0;

    public PasswordDictionary() {
        dictionaryFile = Main.getFileHandler().getResourceAsFile("dictionary.csv");

        initialDictionary = new ArrayList<>();
        dictionary = new HashMap<>();

        generateInitialDictionary();
        generateDictionary();

    }

    /**
     * Get the dictionary. The HashMap key is the amount of letters in that word.
     * @return The dictionary HashMap.
     */
    @NotNull
    public HashMap<Integer, ArrayList<String>> getDictionary() {
        return dictionary;
    }

    private void generateDictionary() {

        for (String word : initialDictionary) {

            word = word.replace("\"", "");
            int wordLength = word.length();

            if (dictionary.get(wordLength) == null) {
                dictionary.put(wordLength, new ArrayList<>());
            }

            dictionary.get(wordLength).add(word);

        }
    }

    private void generateInitialDictionary() {

        try (Scanner scanner = new Scanner(dictionaryFile)) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();

                if (word.length() > maxLength) {
                    maxLength = word.length();
                }

                initialDictionary.add(word);
            }
        } catch (Exception e) {
            System.out.println("ERROR: No words found.");
        }
    }

    @NotNull
    public Integer getMaxLength() {
        return maxLength;
    }

}
