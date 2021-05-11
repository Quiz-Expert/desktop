package app;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SuggestionsMenuController implements Initializable {

    @FXML public AnchorPane sugestionsMenuPane;
    @FXML public Button sendSugestionButton;
    @FXML private TextField text, answerA, answerB, answerC, answerD;
    @FXML private ChoiceBox<String> correctAnswerChoiceBox, categoryChoiceBox;
    private final String[] answers = {"a", "b", "c", "d"};
    private List<String> categoryNames;
    public RequestSender requestSender;

    public void initialize(URL location, ResourceBundle resources) {
        correctAnswerChoiceBox.getItems().addAll(answers);
        try {
            requestSender = new RequestSender("GET", "categories", "");
            categoryNames = JsonPath.parse(requestSender.response.parseAsString()).read("$.data[*].name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoryChoiceBox.getItems().addAll(categoryNames);
    }

    public void sendSugestion() throws IOException {
        SugestionData sugestionData = new SugestionData(text.getText(), answerA.getText(), answerB.getText(), answerC.getText(), answerD.getText(), correctAnswerChoiceBox.getValue(), categoryNames.indexOf(categoryChoiceBox.getValue())+1);
        new RequestSender("POST", "suggestions", new Gson().toJson(sugestionData));
        PopupManager.show("Twoja sugestia zostanie rozpatrzona przez administratora", sendSugestionButton);
    }
}