package app;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FriendsMenuController implements Initializable {

    @FXML private Pagination pagination;
    private static int total = 15;
    private TableView<FriendData> table;
    private List<FriendData> data;
    private RequestSender requestSender;
    private static List<String> friends;

    public void initialize(URL location, ResourceBundle resources) {

        try {
            requestSender = new RequestSender("GET", "friends", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        friends = requestSender.getStringListResponseValues("$.data[*].name");

        table = createTable();
        data = createData();

        pagination.setMaxPageIndicatorCount(1);
        pagination.setPageFactory(this::createPage);

    }

    private TableView<FriendData> createTable() {

        TableView<FriendData> table = new TableView<>();
        TableColumn<FriendData, String> contentColumn = new TableColumn<>();
        contentColumn.setCellValueFactory(param -> param.getValue().friendData);
        contentColumn.setPrefWidth(395);
        contentColumn.setResizable(false);
        table.getColumns().addAll(contentColumn);

        table.setFixedCellSize(40);

        return table;
    }

    private List<FriendData> createData(){

        if (friends.size() == 0){ total = 1; }
        else total = friends.size();

        List<FriendData> data = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            if (friends.isEmpty()) data.add(new FriendData("Nie masz jeszcze znajomych"));
            else {
                data.add(new FriendData(friends.get(i)));
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
}
