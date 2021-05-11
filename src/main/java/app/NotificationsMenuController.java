package app;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationsMenuController implements Initializable {

    @FXML private Pagination pagination;
    private static int total = 15;
    private TableView<NotificationData> table;
    private List<NotificationData> data;
    private RequestSender requestSender;
    private static List<String> notifications;

    public void initialize(URL location, ResourceBundle resources) {

        try {
            requestSender = new RequestSender("GET", "notifications", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        notifications = requestSender.getStringListResponseValues("$.data[*].type");
        table = createTable();
        data = createData();
        pagination.setMaxPageIndicatorCount(1);
        pagination.setPageFactory(this::createPage);
    }

    private TableView<NotificationData> createTable() {

        TableView<NotificationData> table = new TableView<>();
        TableColumn<NotificationData, String> contentColumn = new TableColumn<>();
        contentColumn.setCellValueFactory(param -> param.getValue().notificationData);
        contentColumn.setPrefWidth(538);
        contentColumn.setResizable(false);
        table.getColumns().addAll(contentColumn);

        table.setFixedCellSize(40);

        return table;
    }

    private List<NotificationData> createData(){

        if (notifications.size() == 0){ total = 1; }
        else total = notifications.size();

        List<NotificationData> data = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            if (notifications.isEmpty()) data.add(new NotificationData("Brak powiadomien"));
            else {
                data.add(new NotificationData(setMessage(notifications.get(i))));
            }
        }

        return data;
    }

    private Node createPage(int pageIndex) {

        int fromIndex = pageIndex * total;
        int toIndex = Math.min(fromIndex + total, total);
        table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return table;
    }

    private String setMessage(String type){
        return switch (type) {
            case "incoming_friend_request" -> "Otrzymales zaproszenie do znajomych";
            case "accepted_friend_request" -> "Propozycja znajomosci zostala zaakceptowana";
            case "accepted_suggestion" -> "Twoja sugestia zostala zaakceptowana";
            case "rejected_suggestion" -> "Twoja sugestia zostala odrzucona";
            case "closed_mistake_report" -> "Zgloszony przez Ciebie blad zostal poprawiony";
            case "incoming_game_request" -> "Dostales zaproszenie do gry";
            default -> null;
        };
    }
}
