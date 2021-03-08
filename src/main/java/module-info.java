module Final.Computer.Science.Project {


    // This is now required in order to get JavaFX to run when
    // using a higher JavaSDK than Java 11.
    requires javafx.controls;

    // This is the package that your main class is in.
    opens main;
}