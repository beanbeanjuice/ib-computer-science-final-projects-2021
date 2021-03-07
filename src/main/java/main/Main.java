package main;

import boxes.ConfirmationBox;
import interfaces.ApplicationScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import screens.StartScreen;

public class Main extends Application {

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
            }
            System.out.println("Closed Program");
            window.close();
        }
    }
}
