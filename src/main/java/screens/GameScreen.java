package screens;

import game.Game;
import interfaces.ApplicationScreen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.Main;
import org.jetbrains.annotations.NotNull;
import testclass.TestClass;

/**
 * A GameScreen that implements the ApplicationScreen
 */
public class GameScreen implements ApplicationScreen {

    private Scene gameScreen;
    private Game game;

    TextField score;
    TextField time;

    @Override
    public String getName() {
        return "GameScreen";
    }

    @Override
    public Scene display() {

        game = new Game(this, 0);

        int buttonWidth = 100;

        score = new TextField("Score: 0");
        time = new TextField("");
        time.setEditable(false);

        // START GAME BUTTON
        Button addScoreButton = new Button("Add Score");
        addScoreButton.setOnAction(e -> {
            game.addScore(1);
            setScore(game.getScore());
        });
        addScoreButton.setMaxWidth(buttonWidth);
        addScoreButton.setMinWidth(buttonWidth);

        // CLOSE BUTTON
        Button goHome = new Button("Save and Go Home");
        goHome.setOnAction(e -> {
            Main.saveGame();
            Main.setWindow(new StartScreen());
        });
        goHome.setMaxWidth(buttonWidth);
        goHome.setMinWidth(buttonWidth);

        // Layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(score, time, addScoreButton, goHome);
        layout1.setAlignment(Pos.CENTER);
        gameScreen = new Scene(layout1, 700, 700);

        game.getTimer().start();

        return gameScreen;
    }

    @Override
    public Game getGame() {
        return game;
    }

    public void setScore(@NotNull Integer score) {
        this.score.setText("Score: " + score);
    }

    public void setTime(@NotNull Integer timeLeft) {
        time.setText("Time Left: " + timeLeft);
    }
}
