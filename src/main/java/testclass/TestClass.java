package testclass;

/**
 * A test class used to show an example of how the {@link interfaces.ApplicationScreen Application Screen}
 * should work.
 */
public class TestClass {

    private final String name;

    /**
     * Create a new {@link TestClass} object.
     * @param name The name of the {@link TestClass}
     */
    public TestClass(String name) {
        this.name = name;
    }

    /**
     * @return Gets the name of the {@link TestClass} object.
     */
    public String getName() {
        return name;
    }

}
