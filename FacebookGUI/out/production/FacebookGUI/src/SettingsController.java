import DAO.SettingsDao;
import Models.Settings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.PasswordEncryption;

import javax.xml.soap.Text;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;

public class SettingsController {

    private String myUserName;

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
    private TextField settings_newPassword;

    @FXML
    private TextField settings_confirmPassword;

    @FXML
    private Button settings_updatePassword;

    @FXML
    private Label settings_LabelSuccess;

    @FXML
    private Label settings_LabelFailure;

    @FXML
    private void applyDefaultSettings(){
        //TODO Changes data in DB to default settings
        settings_AgeCB.setSelected(false);
        settings_statusCD.setSelected(false);
        settings_friendsListCB.setSelected(false);
        settings_postsCB.setSelected(false);
    }

    @FXML
    private void applyUserSettings(){
        //TODO Store the values of the check boxes
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


    public void init(String userName){
        myUserName = userName;
        try{
            ObservableList<Settings> settings = SettingsDao.searchSettings(myUserName);
            if(settings.get(0).getAge().equals('Y')){
                settings_AgeCB.setSelected(true);
            }else{
                settings_AgeCB.setSelected(false);
            }
            if(settings.get(0).getFriends().equals('Y')){
                settings_friendsListCB.setSelected(true);
            }else{
                settings_friendsListCB.setSelected(false);
            }
            if(settings.get(0).getPosts().equals('Y')){
                settings_postsCB.setSelected(true);
            }else{
                settings_postsCB.setSelected(false);
            }
            if(settings.get(0).getStatus().equals('Y')){
                settings_statusCD.setSelected(true);
            }else{
                settings_statusCD.setSelected(false);
            }
        }catch (Exception e){

        }
    }

}
