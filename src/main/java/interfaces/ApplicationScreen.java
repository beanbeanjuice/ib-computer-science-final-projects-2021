package interfaces;

import javafx.scene.Scene;
import testclass.TestClass;

// Interfaces are basically like templates.
// This means, every class that implements the interface
// will BE an "interfaces.ApplicationScreen".

public interface ApplicationScreen {

    String getName();
    Scene display();

    // By default, getTestClass will return null
    // UNLESS specified otherwise.
    default TestClass getTestClass() {
        return null;
    }

}
