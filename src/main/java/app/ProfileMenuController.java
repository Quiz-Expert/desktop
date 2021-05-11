package app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenuController implements Initializable {

    @FXML public Label usernameLabel;
    private RequestSender requestSender;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            requestSender = new RequestSender("GET", "auth/user", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        usernameLabel.setText(requestSender.getStringResponseValue("$.data.name"));
    }
}
