package main;

import boxes.ConfirmationBox;
import interfaces.ApplicationScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import screens.StartScreen;

public class Main extends Application {

    // ATTENTION - To run a JavaFX application,
    // it must first be installed into the build.gradle OR the pom.xml
    // which are gradle and maven projects respectively.
    // Maven Link - https://openjfx.io/openjfx-docs/#maven
    // Gradle Link - https://openjfx.io/openjfx-docs/#gradle
    // Additionally, the module-info.java file is needed

    private static ApplicationScreen currentScreen;
    private static Stage window;

    public static void main(String[] args) {

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

    // TODO: Move to Dialogue box
    // TODO: Find a way to make the switching more obvious like background pictures/colours

    // Sets the current window. Custom interface created to house all of the screens.
    public static void setWindow(ApplicationScreen screen) {
        currentScreen = screen;
        window.setScene(screen.display());
    }

    public static void closeProgram() {
        boolean answer = new ConfirmationBox("Confirmation",
                "Are you sure you want to exit?").display();
        if (answer) {
            // Save everything
            if (currentScreen.getName().equals("GameScreen")) {
                // Save Game Stuff

                System.out.println(currentScreen.getTestClass().getName());
                System.out.println("Successfully saved the game!");
            }
            System.out.println("Closed Program");
            window.close();
        }
    }
}
