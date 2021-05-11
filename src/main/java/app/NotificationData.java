package app;

import javafx.beans.property.SimpleStringProperty;

public class NotificationData {

    SimpleStringProperty notificationData;

    public NotificationData(String notificationData){
        this.notificationData = new SimpleStringProperty(notificationData);
    }
}
