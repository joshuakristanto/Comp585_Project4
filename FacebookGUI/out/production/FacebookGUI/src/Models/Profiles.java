package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Profiles {

    private StringProperty FirstName;
    private StringProperty LastName;
    private StringProperty Age;
    private StringProperty UserName;
    private StringProperty Email;
    private StringProperty Password;
    private StringProperty Status;
    private StringProperty SettingsVisible;

    public Profiles() {

        this.FirstName = new SimpleStringProperty();
        this.LastName = new SimpleStringProperty();
        this.Age = new SimpleStringProperty();
        this.UserName = new SimpleStringProperty();
        this.Email = new SimpleStringProperty();
        this.Password = new SimpleStringProperty();
        this.Status = new SimpleStringProperty();
        this.SettingsVisible = new SimpleStringProperty();
    }

    ///////////// this is FirstName
    public String getFirstName() {
        return FirstName.get();
    }
    // set
    public void setFirstName(String newFirstName) {
        FirstName.set(newFirstName);
    }
    // FirstName
    public StringProperty FirstNameProperty() {
        return FirstName;
    }


    /////////////// this is LastName
    public String getLastName() {
        return LastName.get();
    }
    // set
    public void setLastName(String newLastName) {
        LastName.set(newLastName);
    }
    // LastName
    public StringProperty LastNameProperty() {
        return LastName;
    }


    ///////////// this is Age
    public String getAge() {
        return Age.get();
    }
    // set
    public void setAge(String newAge) {
        Age.set(newAge);
    }
    // Age
    public StringProperty AgeProperty() {
        return Age;
    }

    /////////////// this is UserName
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



    //////////// this is Email
    public String getEmail() {
        return Email.get();
    }
    // set
    public void setEmail(String newEmail) {
        Email.set(newEmail);
    }
    // UserName
    public StringProperty EmailProperty() {
        return Email;
    }



    //////////////// this is Password
    public String getPassword() {
        return Password.get();
    }
    // set
    public void setPassword(String newPassword) {
        Password.set(newPassword);
    }
    // UserName
    public StringProperty PasswordProperty() {
        return Password;
    }

    ////////////////// this is Status
    public String getStatus() {
        return Status.get();
    }
    // set
    public void setStatus(String newStatus) {
        Status.set(newStatus);
    }
    // UserName
    public StringProperty StatusProperty() {
        return Status;
    }


    /////////////// this is SettingsVisible
    public String getSettingsVisible() {
        return SettingsVisible.get();
    }
    // set
    public void setSettingsVisible(String newSettingsVisible) {
        SettingsVisible.set(newSettingsVisible);
    }
    // UserName
    public StringProperty SettingsVisibleProperty() {
        return SettingsVisible;
    }






}
