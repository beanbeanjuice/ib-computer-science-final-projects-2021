package main;

import main.password.PasswordGenerator;

public class Main {

    private static PasswordGenerator passwordGenerator;

    public static void main(String[] args) {

        passwordGenerator = new PasswordGenerator(30, true);

        System.out.println(passwordGenerator.generatePassword());

    }

}
