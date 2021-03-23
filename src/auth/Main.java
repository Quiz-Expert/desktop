package auth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        stage.setTitle("Quiz Expert");
        stage.setScene(new Scene(root));
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
