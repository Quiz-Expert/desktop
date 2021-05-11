package app;

import javafx.beans.property.SimpleStringProperty;

public class FriendData {

    SimpleStringProperty friendData;

    public FriendData(String friendData){
        this.friendData = new SimpleStringProperty(friendData);
    }
}

