package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PopupManager {

    private static Popup createPopup(final String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        Label label = new Label(message);
        label.getStylesheets().add("app/css/PopupStyle.css");
        label.getStyleClass().add("popup");
        popup.getContent().add(label);
        return popup;
    }

    public static void show(final String message, final Control control) {
        Stage stage = (Stage) control.getScene().getWindow();
        final Popup popup = createPopup(message);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getY() + 330 - popup.getHeight() / 2);
        });
        popup.show(stage);

        new Timeline(new KeyFrame(
                Duration.millis(2000),
                actionEvent -> popup.hide())).play();
    }
}
