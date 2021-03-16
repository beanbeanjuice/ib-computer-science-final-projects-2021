package timer;

import boxes.ConfirmationBox;
import game.Game;
import javafx.application.Platform;
import main.Main;
import org.jetbrains.annotations.NotNull;
import screens.EndScreen;
import screens.StartScreen;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A class used for implementing a {@link Timer}.
 */
public class Timer implements Runnable {

    private final long startTime;
    private final Game game;
    private int timeElapsed = 0;

    // An AtomicBoolean is a boolean that is updated automatically.
    // Needed when implementing a runnable in JavaFX.
    private final AtomicBoolean running = new AtomicBoolean(false);

    /**
     * Creates a new {@link Timer} object associated with a {@link Game}.
     * @param game The {@link Game} the {@link Timer} is associated with.
     */
    public Timer(@NotNull Game game) {
        this.startTime = System.currentTimeMillis(); // Sets the start time to the current system time in milliseconds.
        this.game = game;
    }

    /**
     * Starts the {@link Timer}.
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the {@link Timer}.
     */
    public void stop() {
        running.set(false);
    }

    /**
     * @param currentTime The current system time in milliseconds.
     * @return THe amount of time left in seconds.
     */
    public int getTimeLeft(long currentTime) {
        timeElapsed = (int) ((currentTime - startTime)/1000);
        int gameTime = (int) (Main.getTimeLimit() * 60);
        return gameTime - timeElapsed;
    }

    /**
     * @return The elapsed time in seconds.
     */
    public int getTimeElapsed() {
        return timeElapsed;
    }

    /**
     * The {@link Runnable} that is run continuously once the timer has started.
     */
    @Override
    public void run() {

        // Sets the AtomicBoolean to true.
        running.set(true);

        // Runs while the AtomicBoolean is true.
        while (running.get()) {

            /*
            Since this is on another Thread, using Thread.sleep(1000); should not be an issue.
            This will make the Thread that the Timer is on sleep, not the actual application.
            If this was running on the Main application, this will make the entire program sleep for
            1000 milliseconds.
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, failed to complete operation.");
            }

            // Sets the time left for the actual game.
            // Within the game, settings the time left also sets the time left for the GameScreen.
            game.setTimeLeft(getTimeLeft(System.currentTimeMillis()));

            // Once the timer hits 0, it STOPS itself.
            // Then if ignoreTimeLimit is enabled, the game keeps running.
            // Otherwise, it asks the user if they want to keep playing.
            // If not, then it saves the game and goes to the EndScreen.
            if (game.getTimeLeft() <= 0) {
                stop();
                if (!Main.getIgnoreTimeLimit()) {

                    /*
                    This is needed so that
                    the Thread can move to the
                    JavaFX Thread
                     */
                    Platform.runLater(() -> {
                        boolean answer = new ConfirmationBox("Out of time...", "Do you want to keep playing?").display();
                        if (!answer) {
                            Main.saveGame();
                            Main.setWindow(new EndScreen());
                        }
                    });
                }
            }
        }
    }

}
