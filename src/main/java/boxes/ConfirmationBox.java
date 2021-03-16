package boxes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * A class used for bringing up a confirmation box.
 */
public class ConfirmationBox {

    private String title;
    private String message;
    private boolean answer;

    /**
     * Creates a new {@link ConfirmationBox} {@link interfaces.ApplicationScreen Screen}.
     * @param title The title of the {@link ConfirmationBox}.
     * @param message The yes or no message to be shown.
     */
    public ConfirmationBox(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * The code that is run when the {@link ConfirmationBox} is shown.
     * @return Whether or not the user clicked "Yes" or "No."
     */
    @NotNull
    public Boolean display() {
        Stage window = new Stage();

        // Prevents input from any other spots on the application aside from this window.
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message); // Sets the label as the message.

        // Button Size Constraints
        int buttonWidth = 100;
        int buttonTextSize = 10;

        // Create two buttons
        Button yesButton = new Button("Yes");
        yesButton.setMaxWidth(buttonWidth);
        yesButton.setMinWidth(buttonWidth);
        yesButton.setFont(new Font(buttonTextSize));

        Button noButton = new Button("No");
        noButton.setMaxWidth(buttonWidth);
        noButton.setMinWidth(buttonWidth);
        noButton.setFont(new Font(buttonTextSize));

        // What to do when the "Yes" button is pressed.
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        // What to do when the "No" button is pressed.
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        // A new VBOX
        VBox layout = new VBox(10);

        // Adds all the buttons and labels (in order).
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER); // Centers everything
        layout.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(layout);

        window.setScene(scene);

        window.showAndWait(); // Display it, but needs to be closed before it can be returned to the original window.

        return answer;
    }

}
