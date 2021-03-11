package main;

import main.file.FileHandler;
import main.password.PasswordDictionary;
import main.password.PasswordGenerator;

public class Main {

    private static FileHandler fileHandler;
    private static PasswordDictionary passwordDictionary;
    private static PasswordGenerator passwordGenerator;

    public static void main(String[] args) {

        fileHandler = new FileHandler();
        passwordDictionary = new PasswordDictionary();

        passwordGenerator = new PasswordGenerator(10, true, true, true);
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(10, false, true, true);
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(10, false, false, true);
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(10, false, false, false);
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(10, true, false, false);
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(10, true, false, true);
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(30, true, true, true);
        System.out.println(passwordGenerator.generatePassword());
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(100, false, false, true);
        System.out.println(passwordGenerator.generatePassword());
        System.out.println(passwordGenerator.generatePassword());

        passwordGenerator = new PasswordGenerator(100, true, true, true);
        System.out.println(passwordGenerator.generatePassword());
        System.out.println(passwordGenerator.generatePassword());

    }

    public static PasswordDictionary getPasswordDictionary() {
        return passwordDictionary;
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }

}
