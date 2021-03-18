package main;


import file.FileHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import password.PasswordDictionary;
import password.PasswordGenerator;
import screen.StartScreen;

import java.io.File;

// IMPORTANT
// Go to Tools -> Preferences -> Libraries
// Then add the jars from the "Required Jars" files into there.
// This will allow the program to run.

public class Main extends Application {

    // Variables
    private static FileHandler fileHandler;
    private static PasswordDictionary passwordDictionary;
    private static PasswordGenerator passwordGenerator;

    private static Stage window;

    public static void main(String[] args) {

        new File("test").getAbsolutePath();

        // Create a new file.FileHandler object.
        // This can be any file handler of your choice/creation.
        fileHandler = new FileHandler();

        // Create a new password dictionary object.
        // This is needed in order to read the dictionary.
        passwordDictionary = new PasswordDictionary();

        // Creates a new password generator object with length of 10, includes symbols, numbers, and the dictionary words.
        passwordGenerator = new PasswordGenerator(10, true, true, true);
        System.out.println(passwordGenerator.generatePassword());

        // Same as above but without symbols.
        passwordGenerator = new PasswordGenerator(10, false, true, true);
        System.out.println(passwordGenerator.generatePassword());

        // Same as above but without symbols and numbers.
        passwordGenerator = new PasswordGenerator(10, false, false, true);
        System.out.println(passwordGenerator.generatePassword());

        // Same as above but without symbols, numbers, or a dictionary. Only letters.
        passwordGenerator = new PasswordGenerator(10, false, false, false);
        System.out.println(passwordGenerator.generatePassword());

        // Only uses symbols and letters.
        passwordGenerator = new PasswordGenerator(10, true, false, false);
        System.out.println(passwordGenerator.generatePassword());

        // Only uses symbols, letters, and a dictionary.
        passwordGenerator = new PasswordGenerator(10, true, false, true);
        System.out.println(passwordGenerator.generatePassword());

        // A length of 30 but includes everything.
        passwordGenerator = new PasswordGenerator(30, true, true, true);
        System.out.println(passwordGenerator.generatePassword());
        System.out.println(passwordGenerator.generatePassword());

        // A length of 100 but only includes letters and a dictionary.
        passwordGenerator = new PasswordGenerator(100, false, false, true);
        System.out.println(passwordGenerator.generatePassword());
        System.out.println(passwordGenerator.generatePassword());

        // A length of 100 but includes everything.
        passwordGenerator = new PasswordGenerator(100, true, true, true);
        System.out.println(passwordGenerator.generatePassword());
        System.out.println(passwordGenerator.generatePassword());

        // A length of 1000 and includes everything.
        passwordGenerator = new PasswordGenerator(1000, true, true, true);
        System.out.println(passwordGenerator.generatePassword());

        // This is an example of how to use this in a GUI.
        launch(args);

    }

    /**
     * Starts the GUI.
     * @param primaryStage The primary window of the {@link Stage}.
     */
    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Password Generator");
        window.setScene(new StartScreen().display());
        window.show();

    }

    /**
     * @return Get the {@link PasswordDictionary} object.
     */
    public static PasswordDictionary getPasswordDictionary() {
        return passwordDictionary;
    }

    /**
     * @return Get the {@link FileHandler} object.
     */
    public static FileHandler getFileHandler() {
        return fileHandler;
    }

    /**
     * @return Get the {@link PasswordGenerator} object.
     */
    public static PasswordGenerator getPasswordGenerator() {
        return passwordGenerator;
    }

}
