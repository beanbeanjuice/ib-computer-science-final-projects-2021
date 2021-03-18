package screen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.Main;
import org.jetbrains.annotations.NotNull;
import password.PasswordGenerator;

/**
 * A StartScreen.
 */
public class StartScreen {

    private Scene startScene;

    private int length;
    private boolean includeSymbols = false;
    private boolean includeNumbers = false;
    private boolean useDictionary = false;

    private TextField lengthField;
    private TextField generatedPasswordField;

    private CheckBox symbolCheckBox;
    private CheckBox numberCheckBox;
    private CheckBox dictionaryCheckBox;

    private Label errorLabel;

    /**
     * @return The {@link Scene} to be displayed.
     */
    public Scene display() {

        int buttonWidth = 150;

        Label label1 = new Label("Password Length");

        lengthField = new TextField();
        lengthField.setEditable(true);
        lengthField.setPromptText("Password Length - Greater than 10");
        lengthField.setMaxWidth(100);

        symbolCheckBox = new CheckBox("Include Symbols");
        numberCheckBox = new CheckBox("Include Numbers");
        dictionaryCheckBox = new CheckBox("Use Dictionary");

        generatedPasswordField = new TextField();
        errorLabel = new Label();

        // Generate Password Button
        Button generatePasswordButton = new Button("Generate Password");

        // This runs when the button is clicked.
        generatePasswordButton.setOnAction(e -> {
            errorLabel.setText("");

            boolean isInt = isInteger(lengthField.getText());
            includeSymbols = symbolCheckBox.isSelected();
            includeNumbers = numberCheckBox.isSelected();
            useDictionary = dictionaryCheckBox.isSelected();

            String generatedPassword;

            // If it's not an integer, use the default length.
            if (isInt) {
                length = Integer.parseInt(lengthField.getText());
                generatedPassword = new PasswordGenerator(length, includeSymbols, includeNumbers, useDictionary).generatePassword();

            } else {
                errorLabel.setText(lengthField.getText() + " is not an integer. Using default number.");
                generatedPassword = new PasswordGenerator(includeSymbols, includeNumbers, useDictionary).generatePassword();
            }

            generatedPasswordField.setText(generatedPassword);

        });
        generatePasswordButton.setMaxWidth(buttonWidth);
        generatePasswordButton.setMinWidth(buttonWidth);

        // Layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1, lengthField, symbolCheckBox, numberCheckBox,
                dictionaryCheckBox, generatePasswordButton, generatedPasswordField, errorLabel);
        layout1.setAlignment(Pos.CENTER);
        startScene = new Scene(layout1, 700, 700);

        return startScene;
    }

    /**
     * Used for checking if a {@link String} is an {@link Integer}.
     * @param number The {@link String} to be checked.
     * @return Whether or not the specified {@link String} is an {@link Integer}.
     */
    @NotNull
    public Boolean isInteger(String number) {
        try {
            Integer.parseInt(number);

        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
