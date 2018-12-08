package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Settings {

    private StringProperty UserName;
    private StringProperty Friends;
    private StringProperty Status;
    private StringProperty Posts;
    private StringProperty Age;


    public Settings() {

        this.UserName = new SimpleStringProperty();
        this.Friends = new SimpleStringProperty();
        this.Status = new SimpleStringProperty();
        this.Posts = new SimpleStringProperty();
        this.Age = new SimpleStringProperty();

    }



    // this is UserName
    public String getUserName() {
        return UserName.get();
    }
    // set
    public void setUserName(String newUserName) {
        UserName.set(newUserName);
    }
    //  column
    public StringProperty UserNameProperty() {
        return UserName;
    }


    //////////// this is Friends
    public String getFriends() {
        return Friends.get();
    }
    // set
    public void setFriends(String newFriends) {
        Friends.set(newFriends);
    }
    //  column
    public StringProperty FriendsProperty() {
        return Friends;
    }


    ////////// this is Status
    public String getStatus() {
        return Status.get();
    }
    // set
    public void setStatus(String newStatus) {
        Status.set(newStatus);
    }
    // column
    public StringProperty StatusProperty() {
        return Status;
    }

    //////// this is Posts
    public String getPosts() {
        return Posts.get();
    }
    // set
    public void setPosts(String newPosts) {
        Posts.set(newPosts);
    }
    // column
    public StringProperty PostsProperty() {
        return Posts;
    }


    ///////// Age
    public String getAge() {
        return Age.get();
    }
    // set
    public void setAge(String newAge) {
        Age.set(newAge);
    }
    // column
    public StringProperty AgeProperty() {
        return Age;
    }













































}
