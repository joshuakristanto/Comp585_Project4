import DAO.ProfilesDao;
import Models.CurrentUser;
import Models.Profiles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserDashBoardController implements Initializable {

    public void initialize(URL location, ResourceBundle resources) {
        udb_PostsTableView = new TableView<>();
        udb_FriendsListView = new ListView();
    }

    @FXML
    private Button udb_logoutButton;

    @FXML
    private Button udb_settingsButton;

    @FXML
    private Label udb_firstNameLabel;

    @FXML
    private Label udb_lastNameLabel;

    @FXML
    private Label udb_AgeLabel;

    @FXML
    private Label udb_EmailLabel;

    @FXML
    private TextArea udb_statusArea;

    @FXML
    private Button udb_editStatusButton;

    @FXML
    private ListView udb_FriendsListView;

    @FXML
    private Label username_key;

    @FXML
    private Button udb_NewPostButton;

    @FXML
    private TextArea udb_postTextArea;

    @FXML
    private TableView<String> udb_PostsTableView;

    @FXML
    private Button udb_DeletePost;

    @FXML
    private TextField udb_friendsUsername;

    @FXML
    private Button udb_addFriend;


    @FXML
    private void logoutOfApplication(ActionEvent event) throws IOException {
        System.out.println("You have logged out.");
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 720) / 2);
        stage.setY((screenBounds.getHeight() - 720) / 2);

        stage.setResizable(false);
        stage.setScene(signUpPageScene);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    private void openSettings() throws IOException{
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("settings_page.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = new Stage();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 600) / 2);
        stage.setY((screenBounds.getHeight() - 400) / 2);

        stage.setResizable(false);
        stage.setScene(signUpPageScene);
        stage.setTitle("Settings");
        stage.show();
    }

    @FXML
    private void editStatus(){
        if(!udb_statusArea.isEditable()){ // Editable is false
            udb_statusArea.setEditable(true);
            udb_editStatusButton.setText("Save Status");
            udb_editStatusButton.setLayoutX(1381);
            udb_editStatusButton.setLayoutY(26);
        }else { // Editable is true
            udb_statusArea.setEditable(false);
            udb_editStatusButton.setText("Edit Status");
            udb_editStatusButton.setLayoutX(1389);
            udb_editStatusButton.setLayoutY(26);
        }
    }

    @FXML
    private void publishNewPost(){

    }

    @FXML
    private void deleteFriend(){

    }

    @FXML
    private void addFriend(ActionEvent event){
        ObservableList<String> friendsList = FXCollections.observableArrayList();
        System.out.println("Add Friend pressed.");
        if(!udb_friendsUsername.getText().equals("")){
            String username = udb_friendsUsername.getText();
            System.out.println(username);
            //TODO check if username exists in the DB
            //If yes, then add user
            //else, prompt user that username doesn't exist
            friendsList.add(username);
            udb_FriendsListView.setItems(friendsList);
        }
    }

    //Getters
    private String getFirstNameLabel(){
        return udb_firstNameLabel.getText();
    }

    private String getLastNameLabel(){
        return udb_lastNameLabel.getText();
    }

    private String getAgeLabel(){
        return udb_AgeLabel.getText();
    }

    private String getEmail(){
        return udb_EmailLabel.getText();
    }

    // Setter
    public void setUsername(String user){
        username_key.setText(user);
    }

    private void init(){

        try{

            ObservableList<CurrentUser> curr = ProfilesDao.searchCurrUser();

            String curruser = curr.get(0).getCurrUserName();



        } catch(Exception e) {

        }

    }
}
