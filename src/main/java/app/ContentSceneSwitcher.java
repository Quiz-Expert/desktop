package app;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.Objects;

public class ContentSceneSwitcher {
    public ContentSceneSwitcher(Pane pane, String sceneName){
        pane.getChildren().remove(0);
        try {
            pane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(sceneName + ".fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
