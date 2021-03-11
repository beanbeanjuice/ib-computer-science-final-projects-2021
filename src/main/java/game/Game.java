package game;

import main.Main;
import org.jetbrains.annotations.NotNull;
import screens.GameScreen;
import timer.Timer;

public class Game {

    private int score;
    private int timeLeft;
    private int gameID;
    private Timer timer;
    private GameScreen gameScreen;

    public Game(@NotNull GameScreen gameScreen, @NotNull Integer gameID) {
        this.gameScreen = gameScreen;
        this.gameID = gameID;
        timer = new Timer(this);
    }

    public void setTimeLeft(@NotNull Integer timeLeft) {
        this.timeLeft = timeLeft;
        gameScreen.setTime(timeLeft);
    }

    @NotNull
    public Integer getTimeLeft() {
        return timeLeft;
    }

    public void addScore(@NotNull Integer score) {
        this.score += score;
    }

    @NotNull
    public Integer getScore() {
        return score;
    }

    @NotNull
    public Timer getTimer() {
        return timer;
    }

}
