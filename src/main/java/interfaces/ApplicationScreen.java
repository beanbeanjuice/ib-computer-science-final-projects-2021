package interfaces;

import game.Game;
import javafx.scene.Scene;
import testclass.TestClass;

// Interfaces are basically like templates.
// This means, every class that implements the interface
// will BE an "interfaces.ApplicationScreen".

/**
 * An interface to be used for screens.
 */
public interface ApplicationScreen {

    /**
     * @return Returns the name of the {@link ApplicationScreen}.
     */
    String getName();

    /**
     * @return The code that is run from the {@link ApplicationScreen}.
     */
    Scene display();

    // By default, getTestClass will return null
    // UNLESS specified otherwise.

    /**
     * A default method that will be return null unless specified otherwise.
     * @return Returns the {@link TestClass} object that may be needed.
     */
    default Game getGame() {
        return null;
    }

}
