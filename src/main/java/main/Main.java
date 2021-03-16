package main;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import javafx.application.Application;
import main.file.FileHandler;
import main.password.PasswordDictionary;
import main.password.PasswordGenerator;
=======
import file.FileHandler;
import password.PasswordDictionary;
import password.PasswordGenerator;
>>>>>>> Stashed changes
=======
import file.FileHandler;
import password.PasswordDictionary;
import password.PasswordGenerator;
>>>>>>> Stashed changes

public class Main {

    // Variables
    private static FileHandler fileHandler;
    private static PasswordDictionary passwordDictionary;
    private static PasswordGenerator passwordGenerator;

    public static void main(String[] args) {

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

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Test Game");
        setWindow(new StartScreen());
        window.show();

        // Consumes the "X" button or Mac/Linux equivalent.
        // Stops the program from just closing.
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

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
