package bot67;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Displays the JavaFX interface for Bot67.
 */
public class Main extends Application {
    /** Creates and displays the primary application window. */
    @Override
    public void start(Stage stage) {
        Label greeting = new Label("Hello from Bot67!");
        Scene scene = new Scene(greeting, 400, 600);
        stage.setTitle("Bot67");
        stage.setScene(scene);
        stage.show();
    }
}
