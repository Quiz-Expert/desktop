package app;

import java.util.Objects;
import javafx.application.Application;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AuthScene.fxml")));
        stage.getIcons().add(new Image("app/images/icon.png"));
        stage.setTitle("Quiz Expert");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
