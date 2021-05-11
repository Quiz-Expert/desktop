package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class WindowSwitcher {

    public WindowSwitcher(ActionEvent event, String name) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name)));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("app/images/icon.png"));
        stage.setTitle("Quiz Expert");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnCloseRequest(event1 -> {
            try {
                new RequestSender("POST", "auth/logout", "");
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}