package screens;

import interfaces.ApplicationScreen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.Main;
import testclass.TestClass;

/**
 * A GameScreen that implements the ApplicationScreen
 */
public class GameScreen implements ApplicationScreen {

    private Scene gameScreen;

    @Override
    public String getName() {
        return "GameScreen";
    }

    @Override
    public Scene display() {
        int buttonWidth = 100;

        Label label1 = new Label("Game Screen!");

        // START GAME BUTTON
        Button startButton = new Button("Start Screen");
        startButton.setOnAction(e -> {
            Main.setWindow(new StartScreen());
            System.out.println("Switched to the Start Screen!");
        });
        startButton.setMaxWidth(buttonWidth);
        startButton.setMinWidth(buttonWidth);

        // CLOSE BUTTON
        Button closeButton = new Button("Save and Quit");
        closeButton.setOnAction(e -> Main.closeProgram());
        closeButton.setMaxWidth(buttonWidth);
        closeButton.setMinWidth(buttonWidth);

        // Layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1, startButton, closeButton);
        layout1.setAlignment(Pos.CENTER);
        gameScreen = new Scene(layout1, 700, 700);

        return gameScreen;
    }

    @Override
    public TestClass getTestClass() {
        return new TestClass("This is an example of a test class!");
    }
}
