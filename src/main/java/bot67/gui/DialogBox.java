package bot67.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/** Represents one chat message and its speaker's avatar. */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load the dialog box layout", e);
        }
        dialog.setText(text);
        displayPicture.setImage(image);
        cropToSquare(image);
    }

    /** Creates a right-aligned user message. */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /** Creates a left-aligned Bot67 response. */
    public static DialogBox getBotDialog(String text, Image image) {
        DialogBox box = new DialogBox(text, image);
        box.flip();
        return box;
    }

    /** Places the avatar on the left and applies the bot bubble style. */
    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        Collections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /** Crops a wide or tall source image around its center for an avatar-shaped view. */
    private void cropToSquare(Image image) {
        double side = Math.min(image.getWidth(), image.getHeight());
        double x = (image.getWidth() - side) / 2;
        double y = (image.getHeight() - side) / 2;
        displayPicture.setViewport(new Rectangle2D(x, y, side, side));
    }
}
