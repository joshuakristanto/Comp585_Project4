package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Friends {

    private StringProperty UserName;
    private StringProperty FriendUserName;

    public Friends() {
        this.UserName = new SimpleStringProperty();
        this.FriendUserName = new SimpleStringProperty();
    }

    // this is UserName
    public String getUserName() {
        return UserName.get();
    }
    // set the UserName
    public void setUserName(String newUserName) {
        UserName.set(newUserName);
    }
    // UserName column
    public StringProperty UserNameProperty() {
        return UserName;
    }


    // this is FriendUserName
    public String getFriendUserName() {
        return FriendUserName.get();
    }
    // set the FriendUserName
    public void setFriendUserName(String newFriendUserName) {
        FriendUserName.set(newFriendUserName);
    }
    // FriendUserName column
    public StringProperty FriendUserNameProperty() {
        return FriendUserName;
    }

}
