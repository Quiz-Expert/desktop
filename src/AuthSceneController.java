import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class AuthSceneController {

    public void switchToPasswordReminderScene(ActionEvent event) throws IOException {
        switchScene(event, "scenes/passwordReminderScene.fxml");
    }

    public void switchToCreateAccountScene(ActionEvent event) throws IOException {
        switchScene(event, "scenes/createAccountScene.fxml");
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        switchScene(event, "scenes/authScene.fxml");
    }

    private void switchScene(ActionEvent event, String name) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name)));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
