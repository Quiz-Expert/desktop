package app;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.io.IOException;

public class AuthSceneController {

    @FXML TextField emailTextField, nameTextField, passwordTextField, password_confirmationTextField;
    private RequestSender requestSender;

    public void switchToCreateAccountScene(ActionEvent event) throws IOException {
        new WindowSwitcher(event, "CreateAccountScene.fxml");
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        new WindowSwitcher(event, "AuthScene.fxml");
    }

    public void register(ActionEvent event) {
        RegisterData user = new RegisterData(emailTextField.getText(), nameTextField.getText(), passwordTextField.getText(), password_confirmationTextField.getText());
        try {
            requestSender = new RequestSender("POST", "auth/register", new Gson().toJson(user));
            new WindowSwitcher(event, "MenuScene.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(ActionEvent event) {
        LoginData user = new LoginData(emailTextField.getText(), passwordTextField.getText());
        try {
            requestSender = new RequestSender("POST", "auth/login", new Gson().toJson(user));
            new WindowSwitcher(event, "MenuScene.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}