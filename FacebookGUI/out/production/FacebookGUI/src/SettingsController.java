import DAO.FriendsDao;
import DAO.PostsDao;
import DAO.ProfilesDao;
import DAO.SettingsDao;
import Models.Friends;
import Models.Posts;
import Models.Settings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.PasswordEncryption;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class SettingsController implements Initializable {

    private String myUserName;
    private Stage mainStage;

    @FXML
    private CheckBox  settings_AgeCB;

    @FXML
    private CheckBox settings_statusCD;

    @FXML
    private CheckBox settings_friendsListCB;

    @FXML
    private CheckBox settings_postsCB;

    @FXML
    private Button settings_defaultSettings;

    @FXML
    private Button settings_applySettings;

    @FXML

    private TextField settings_username;

    @FXML
    private PasswordField settings_newPassword;

    @FXML
    private PasswordField settings_confirmPassword;

    @FXML
    private Button settings_updatePassword;

    @FXML
    private Label settings_LabelSuccess;

    @FXML
    private Label settings_LabelFailure;

    @FXML
    private TextField settings_usernameToDelete;

    @FXML
    private TextField settings_confirmDelete;

    @FXML
    private Button settings_deleteProfileButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init(String userName){
        myUserName = userName;
        loadSettings();
    }

    private void loadSettings(){
        try{
            ObservableList<Settings> settingsList = SettingsDao.searchSettings(myUserName);
            //Friends List
            String friendsSetting = settingsList.get(0).getFriends();
            System.out.println(friendsSetting);
            if(friendsSetting.equals("Y")){
                settings_friendsListCB.setSelected(true);
            }else{
                settings_friendsListCB.setSelected(false);
            }
            //Age
            String ageSetting = settingsList.get(0).getAge();
            System.out.println(ageSetting);
            if(ageSetting.equals("Y")){
                settings_AgeCB.setSelected(true);
            }else{
                settings_AgeCB.setSelected(false);
            }
            //Posts
            String postSetting = settingsList.get(0).getPosts();
            System.out.println(postSetting);
            if(postSetting.equals("Y")){
                settings_postsCB.setSelected(true);
            }else{
                settings_postsCB.setSelected(false);
            }
            //Status
            String statusString = settingsList.get(0).getStatus();
            System.out.println(statusString);
            if(statusString.equals("Y")){
                settings_statusCD.setSelected(true);
            }else{
                settings_statusCD.setSelected(false);
            }
        }catch (Exception e){

        }
    }

    // Delete Profile
    @FXML
    private void deleteProfile() throws IOException{
        if (settings_usernameToDelete.getText().equals(myUserName) && settings_confirmDelete.getText().equals("CONFIRM")) {
            try{
                //Delete profileDB
                ProfilesDao.deleteProfiles(myUserName);
                //Delete from Friends DB
                ObservableList<Friends> friendsList = FriendsDao.searchFriends(myUserName);
                for(Friends friend : friendsList){
                    FriendsDao.deleteFriend(myUserName, friend.getFriendUserName());
                }
                //Delete from PostsDB
                ObservableList<Posts> posts = PostsDao.searchPosts(myUserName);
                for(Posts post: posts){
                    PostsDao.deletePosts(myUserName, post.getPostText());
                }
                //Delete from SettingsDB
                SettingsDao.deleteUserFromSettings(myUserName);
            }catch (Exception e){

            }
        }
        // Closes Settings Page
        Stage stage = (Stage)settings_deleteProfileButton.getScene().getWindow();
        stage.close();
        // Closes Main Page and opens login screen
        closeMainStage();

    }

    private void closeMainStage() throws IOException {
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = mainStage;

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 720) / 2);
        stage.setY((screenBounds.getHeight() - 720) / 2);

        stage.setResizable(false);
        stage.setScene(signUpPageScene);
        stage.setTitle("Login");
        stage.show();
    }

    // Check username field for deleting
    @FXML
    private void checkUsername(){
        //If field is empty
        if(settings_usernameToDelete.equals("")){
            settings_confirmDelete.setStyle("-fx-border-color: #DCDCDC;");
        }

        //If field does not match current user logged in, in order to prevent deleting other users
        if(!settings_usernameToDelete.getText().equals(myUserName)){
            settings_usernameToDelete.setStyle("-fx-border-color: red;");
        }else{
            settings_usernameToDelete.setStyle("-fx-border-color: green;");
        }
    }

    // Check confirm field for double verification of deleting
    @FXML
    private void checkConfirmField(){
        //If field is empty
        if(settings_confirmDelete.equals("")){
            settings_confirmDelete.setStyle("-fx-border-color: #DCDCDC;");
        }

        //If field does not match current user logged in, in order to prevent deleting other users
        if(!settings_confirmDelete.getText().equals("CONFIRM")){
            settings_confirmDelete.setStyle("-fx-border-color: red;");
        }else{
            settings_confirmDelete.setStyle("-fx-border-color: green;");
        }
    }

    @FXML
    private void applyDefaultSettings(){

        settings_AgeCB.setSelected(true);
        settings_statusCD.setSelected(true);
        settings_friendsListCB.setSelected(true);
        settings_postsCB.setSelected(true);

        // sets default to all N ie nothing is visible

        try{
            SettingsDao.updateAgeVisible(myUserName,"Y");
            SettingsDao.updateStatusVisible(myUserName,"Y");
            SettingsDao.updateFriendsVisible(myUserName,"Y");
            SettingsDao.updatePostsVisible(myUserName,"Y");
        } catch (Exception e){

        }
    }

    @FXML
    private void applyUserSettings() throws Exception{
        // Update DB based on new changes made by user
        try{
            SettingsDao.updateAgeVisible(myUserName, getAgeCB());
            SettingsDao.updateFriendsVisible(myUserName, getFriendsListCB());
            SettingsDao.updatePostsVisible(myUserName, getPostCB());
            SettingsDao.updateStatusVisible(myUserName, getStatusCB());

        }catch (Exception e){

        }

        //Closes settings window after values are stored
        Stage stage = (Stage)settings_applySettings.getScene().getWindow();
        stage.close();
    }


    public void getSettingsFromDB() throws Exception{

        String friendsSelected;
        String statusSelected;
        String postsSelected;
        String ageSelected;

        ObservableList<Settings> userSet = SettingsDao.searchSettings(myUserName);

        friendsSelected = userSet.get(0).getFriends();
        statusSelected = userSet.get(0).getStatus();
        postsSelected = userSet.get(0).getPosts();
        ageSelected = userSet.get(0).getAge();



        if(friendsSelected == "Y") {
            settings_friendsListCB.setSelected(true);
        } else {
            settings_friendsListCB.setSelected(false);
        }

        if(statusSelected == "Y") {
            settings_statusCD.setSelected(true);
        } else {
            settings_statusCD.setSelected(false);
        }

        if(postsSelected == "Y") {
            settings_postsCB.setSelected(true);
        } else {
            settings_postsCB.setSelected(false);
        }

        if(ageSelected == "Y") {
            settings_AgeCB.setSelected(true);
        } else {
            settings_AgeCB.setSelected(false);
        }

    }

    /** Start: Update password methods**/

    @FXML
    private void updatePassword(){
        String username = settings_username.getText();
        String password = settings_newPassword.getText();
        String confirmPassword = settings_confirmPassword.getText();

        if(password.equals(confirmPassword)){
            try{
                PasswordEncryption.resetPassword(username, confirmPassword);
                settings_LabelSuccess.setVisible(true);
            } catch (Exception e){
                settings_LabelFailure.setVisible(true);
            }
        }
    }

    @FXML
    private void matchPasswords(){
        if(settings_newPassword.getText().equals("") || settings_confirmPassword.getText().equals("")){
            settings_newPassword.setStyle("-fx-border-color: #DCDCDC;");
            settings_confirmPassword.setStyle("-fx-border-color: #DCDCDC;");
        }else if(settings_newPassword.getText().equals(settings_confirmPassword.getText())) {
            settings_newPassword.setStyle("-fx-border-color: #00FF3C;");
            settings_confirmPassword.setStyle("-fx-border-color: #00FF3C;");
        }else{
            settings_newPassword.setStyle("-fx-border-color: red;");
            settings_confirmPassword.setStyle("-fx-border-color: red;");
        }
    }

    /** End: Update Methods**/

    //Setters
    private void setAgeCB(Boolean check){
        settings_AgeCB.setSelected(check);
    }
    private void setStatusCB(Boolean check){
        settings_statusCD.setSelected(check);
    }
    private void setFriendsListCB(Boolean check){
        settings_friendsListCB.setSelected(check);
    }
    private void setPostCD(Boolean check){
        settings_postsCB.setSelected(check);
    }

    public void setMainStage(Stage stage){
        mainStage = stage;
    }

    //DB Getter
    private String getAgeCB(){
        if(settings_AgeCB.isSelected()){
            return "Y";
        }else{
            return "N";
        }
    }

    private String getStatusCB(){
        if(settings_statusCD.isSelected()){
            return "Y";
        }else{
            return "N";
        }
    }

    private String getFriendsListCB(){
        if(settings_friendsListCB.isSelected()){
            return "Y";
        }else{
            return "N";
        }
    }

    private String getPostCB(){
        if(settings_postsCB.isSelected()){
            return "Y";
        }else{
            return "N";
        }
    }


}
