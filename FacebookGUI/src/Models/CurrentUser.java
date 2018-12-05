package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CurrentUser {

    private StringProperty UserName;

    public CurrentUser() {

        this.UserName = new SimpleStringProperty();

    }



    ///////////// this is FirstName
    public String getCurrUserName() {
        return UserName.get();
    }
    // set
    public void setCurrUserName(String newUserName) {
        UserName.set(newUserName);
    }
    // FirstName
    public StringProperty currUserNameProperty() {
        return UserName;
    }

}
