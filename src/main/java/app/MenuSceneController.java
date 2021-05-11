package app;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuSceneController implements Initializable {

    @FXML public AnchorPane contentPane;

    public void initialize(URL location, ResourceBundle resources) {

        try {
            contentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameModeMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void switchToGameMenuScene(){ new ContentSceneSwitcher(contentPane, "GameModeMenu"); }

    public void switchToProfileScene(){ new ContentSceneSwitcher(contentPane, "ProfileMenu"); }

    public void switchToFriendsScene(){ new ContentSceneSwitcher(contentPane, "FriendsMenu"); }

    public void switchToNotificationsScene(){ new ContentSceneSwitcher(contentPane, "NotificationsMenu"); }

    public void switchToSugestionsScene(){ new ContentSceneSwitcher(contentPane, "SugestionsMenu"); }

    public void logout(ActionEvent event) throws IOException {
        new RequestSender("POST", "auth/logout", "");
        new WindowSwitcher(event, "AuthScene.fxml");
    }

}