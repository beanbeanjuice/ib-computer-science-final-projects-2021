package screens;

import game.Game;
import interfaces.ApplicationScreen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.Main;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link GameScreen} that implements the {@link ApplicationScreen} interface.
 */
public class GameScreen implements ApplicationScreen {

    private Scene gameScreen;
    private Game game;

    private TextField score;
    private TextField time;

    /**
     * @return The name of the {@link ApplicationScreen}.
     */
    @Override
    public String getName() {
        return "GameScreen";
    }

    /**
     * The method to be run when the {@link ApplicationScreen} is to be displayed.
     * @return The {@link Scene} to be shown.
     */
    @Override
    public Scene display() {

        // Creates a new Game object.
        game = new Game(this, 0);

        // Button Constraints
        int buttonWidth = 100;

        // Instantiates variables
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
        Button endGame = new Button("Save");
        endGame.setOnAction(e -> {
            // Things to do when the "Go Home" button is pressed.
            Main.saveGame();
            Main.setWindow(new EndScreen());
        });
        endGame.setMaxWidth(buttonWidth);
        endGame.setMinWidth(buttonWidth);

        // Layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(10);
        // Adds all of the score, time, buttons to the Vbox
        layout1.getChildren().addAll(score, time, addScoreButton, endGame);
        layout1.setAlignment(Pos.CENTER);

        // Creates a new screen with the VBOX.
        gameScreen = new Scene(layout1, 700, 700);

        // Starts the timer once the game has started.
        game.getTimer().start();

        return gameScreen;
    }

    /**
     * Gets the current game. This can be used when saving.
     * @return The current {@link Game}.
     */
    @Override
    public Game getGame() {
        return game;
    }

    /**
     * Sets the score of the Score {@link TextField}.
     * @param score The score to be set.
     */
    public void setScore(@NotNull Integer score) {
        this.score.setText("Score: " + score);
    }

    /**
     * Sets the time of the Time {@link TextField}.
     * @param timeLeft The time to be set.
     */
    public void setTime(@NotNull Integer timeLeft) {
        time.setText("Time Left: " + timeLeft);
    }
}
