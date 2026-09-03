package bot67.gui;

import java.util.Objects;

import bot67.Bot67;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controls Bot67's main chat window. */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private final Image userImage = loadImage("/images/User.png");
    private final Image botImage = loadImage("/images/Bot67.png");
    private Bot67 bot;

    /** Keeps the newest messages visible as the conversation grows. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the application logic after FXML creates this controller. */
    public void setBot(Bot67 bot) {
        this.bot = bot;
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog("Hello! I'm Bot67. What can I do for you?", botImage));
    }

    /** Sends one command and displays the user and Bot67 messages. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }
        String response = bot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage));
        userInput.clear();
        if (bot.isExitRequested()) {
            Platform.runLater(Platform::exit);
        }
    }

    /** Loads a required image resource and reports a clear error if it is missing. */
    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(
                MainWindow.class.getResourceAsStream(path), "Missing image resource: " + path));
    }
}
