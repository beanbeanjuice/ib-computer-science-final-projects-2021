package main;

import boxes.ConfirmationBox;
import game.Game;
import interfaces.ApplicationScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    private static double timeLimit = 0.1; // Time limit in minutes.
    private static boolean ignoreTimeLimit = false;

    private static Game game;

    public static void main(String[] args) {

        launch(args);
    }

    /**
     * This is used to start the JavaFX GUI.
     * By default, it should just run if you have "launch(args);" in the main method.
     * @param primaryStage This is entered by default when you run the main method.
     */
    @Override
    public void start(Stage primaryStage) {

        window = primaryStage; // Sets the window as the primary stage.
        window.setTitle("Test Game"); // Sets the title of the application.
        setWindow(new StartScreen()); // Sets the screen to the "StartScreen"
        window.show(); // Unhides the window.

        // Consumes the "X" button or Mac/Linux equivalent.
        // Stops the program from just closing.
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

    }

    /**
     * Sets the current window.
     * @param screen The {@link ApplicationScreen} that you are switching to.
     */
    public static void setWindow(ApplicationScreen screen) {
        currentScreen = screen;
        window.setScene(screen.display());
    }

    /**
     * Things that run when the program is closed.
     */
    public static void closeProgram() {
        // Creates a new ConfirmationBox object.
        boolean answer = new ConfirmationBox("Confirmation",
                "Are you sure you want to exit?").display();

        // If "YES" is clicked, then quit the game after saving.
        if (answer) {
            // Save everything
            if (currentScreen.getName().equals("GameScreen")) {
                // Save Game Stuff

                System.out.println(currentScreen.getGame().getScore());
                System.out.println("Successfully saved the game!");
            }
            System.out.println("Closed Program");
            window.close();
        }
    }

    /**
     * @return The current {@link ApplicationScreen}.
     */
    @NotNull
    public static ApplicationScreen getCurrentScreen() {
        return currentScreen;
    }

    /**
     * Things that run when the game is saved.
     */
    public static void saveGame() {
        currentScreen.getGame().getTimer().stop();
        game = currentScreen.getGame();
    }

    /**
     * @return The {@link Game} once it has finished.
     */
    @Nullable
    public static Game getGame() {
        return game;
    }

    /**
     * @return Whether or not to ignore the time limit.
     */
    @NotNull
    public static Boolean getIgnoreTimeLimit() {
        return ignoreTimeLimit;
    }

    /**
     * @return The time limit in minutes.
     */
    @NotNull
    public static Double getTimeLimit() {
        return timeLimit;
    }
}
