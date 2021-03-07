package screens;

import interfaces.ApplicationScreen;
import javafx.scene.Scene;
import testclass.TestClass;

public class GameScreen implements ApplicationScreen {
    @Override
    public String getName() {
        return "GameScreen";
    }

    @Override
    public Scene display() {
        return null;
    }

    @Override
    public TestClass getTestClass() {
        return new TestClass("test");
    }
}
