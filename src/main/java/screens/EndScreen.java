package screens;

import interfaces.ApplicationScreen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.Main;

/**
 * An {@link EndScreen} that implements the {@link ApplicationScreen} interface.
 */
public class EndScreen implements ApplicationScreen {

    private Scene endScreen;
    private Label timeLeftLabel;
    private Label scoreLabel;

    /**
     * @return The name of the {@link ApplicationScreen}.
     */
    @Override
    public String getName() {
        return "EndScreen";
    }

    /**
     * The method to be run when the {@link ApplicationScreen} is to be displayed.
     * @return The {@link Scene} to be shown.
     */
    @Override
    public Scene display() {
        int buttonWidth = 100;

        // A label with the name "Ending Screen"
        Label label1 = new Label("Ending Screen");

        // These should not be null, assuming that you got to this screen FROM the GameScreen.
        timeLeftLabel = new Label("Time Left: " + Main.getGame().getTimeLeft());
        scoreLabel = new Label("Score: " + Main.getGame().getScore());

        // GO HOME BUTTON
        Button goHome = new Button("Go Home");
        goHome.setOnAction(e -> {
            // This runs when the "Go Home" button is pressed.
            Main.setWindow(new StartScreen());
        });
        goHome.setMaxWidth(buttonWidth);
        goHome.setMinWidth(buttonWidth);

        // CLOSE BUTTON
        Button closeButton = new Button("Save and Quit");
        closeButton.setOnAction(e -> Main.closeProgram()); // This runs when the "Close Button" is pressed.
        closeButton.setMaxWidth(buttonWidth);
        closeButton.setMinWidth(buttonWidth);

        // Layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(10);

        // Adds the label and buttons to the vbox.
        layout1.getChildren().addAll(label1, scoreLabel, timeLeftLabel, goHome, closeButton);
        layout1.setAlignment(Pos.CENTER);

        // Creates a new scene with the Vbox.
        endScreen = new Scene(layout1, 700, 700);

        return endScreen;
    }
}
