package screens;

import interfaces.ApplicationScreen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.Main;

/**
 * A {@link StartScreen} that implements the {@link ApplicationScreen} interface.
 */
public class StartScreen implements ApplicationScreen {

    private Scene startScene;

    /**
     * @return The name of the {@link ApplicationScreen}.
     */
    @Override
    public String getName() {
        return "StartScreen";
    }

    /**
     * The method to be run when the {@link ApplicationScreen} is to be displayed.
     * @return The {@link Scene} to be shown.
     */
    @Override
    public Scene display() {

        int buttonWidth = 100;

        // A label with the name "Start Screen"
        Label label1 = new Label("Start Screen");

        // START GAME BUTTON
        Button startButton = new Button("Game Screen");
        startButton.setOnAction(e -> {
            Main.setWindow(new GameScreen());
            System.out.println("Switched to Game Screen!");
        });
        startButton.setMaxWidth(buttonWidth);
        startButton.setMinWidth(buttonWidth);

        // CLOSE BUTTON
        Button closeButton = new Button("Save and Quit");
        closeButton.setOnAction(e -> Main.closeProgram()); // This runs when the "Close Button" is pressed.
        closeButton.setMaxWidth(buttonWidth);
        closeButton.setMinWidth(buttonWidth);

        // Layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(10);

        // Adds the label and buttons to the vbox.
        layout1.getChildren().addAll(label1, startButton, closeButton);
        layout1.setAlignment(Pos.CENTER);

        // Creates a new scene with the Vbox.
        startScene = new Scene(layout1, 700, 700);

        return startScene;
    }
}
