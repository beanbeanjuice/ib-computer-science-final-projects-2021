package game;

import org.jetbrains.annotations.NotNull;
import screens.GameScreen;
import timer.Timer;

/**
 * Example game class used.
 */
public class Game {

    private int score;
    private int timeLeft;
    private int gameID;
    private Timer timer;
    private GameScreen gameScreen;

    /**
     * Creates a new {@link Game} object.
     * @param gameScreen The {@link GameScreen} that will contain the {@link Game}.
     * @param gameID The ID of the {@link Game}.
     */
    public Game(@NotNull GameScreen gameScreen, @NotNull Integer gameID) {
        this.gameScreen = gameScreen;
        this.gameID = gameID;
        timer = new Timer(this);
    }

    /**
     * Sets the amount of time left for the {@link Game}.
     * @param timeLeft The time left in seconds.
     */
    public void setTimeLeft(@NotNull Integer timeLeft) {
        this.timeLeft = timeLeft;
        gameScreen.setTime(timeLeft);
    }

    /**
     * Gets the amount of time left for the {@link Game}.
     * @return The time left in seconds.
     */
    @NotNull
    public Integer getTimeLeft() {
        return timeLeft;
    }

    /**
     * Adds the score for the {@link Game} using a specified value.
     * @param score The amount to add the score by.
     */
    public void addScore(@NotNull Integer score) {
        this.score += score;
    }

    /**
     * Gets the current score for the {@link Game}.
     * @return The current score.
     */
    @NotNull
    public Integer getScore() {
        return score;
    }

    /**
     * Gets the {@link Timer} currently running for the {@link Game}.
     * @return The current {@link Timer}.
     */
    @NotNull
    public Timer getTimer() {
        return timer;
    }

}
