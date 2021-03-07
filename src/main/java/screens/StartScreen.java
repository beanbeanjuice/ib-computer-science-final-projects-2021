package screens;

import interfaces.ApplicationScreen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.Main;

public class StartScreen implements ApplicationScreen {

    private Scene startScene;

    @Override
    public String getName() {
        return "StartScreen";
    }

    @Override
    public Scene display() {

        int buttonWidth = 100;

        Label label1 = new Label("Boggle: The Game");
        //label1.setFont(fontHandler.getFont(Main.getTitleFont(), 50));
        label1.setId("title");

        // START GAME BUTTON
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> Main.setWindow(new GameScreen()));
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
        startScene = new Scene(layout1, 700, 700);

        return startScene;
    }
}
