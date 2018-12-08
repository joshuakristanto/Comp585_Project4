import DAO.SettingsDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class SettingsController {

    // placeholder until I can get the actual username
    String UserName;

    public String getUserName() {
        return UserName;
    }

    @FXML
    private CheckBox  settings_AgeCB;

    @FXML
    private CheckBox settings_emailCB;

    @FXML
    private CheckBox settings_friendsListCB;

    @FXML
    private CheckBox settings_postsCB;

    @FXML
    private Button settings_defaultSettings;

    @FXML
    private Button settings_applySettings;

    @FXML
    private void applyDefaultSettings() throws Exception {
        //TODO Changes data in DB to default settings
        settings_AgeCB.setSelected(false);
        settings_emailCB.setSelected(false);
        settings_friendsListCB.setSelected(false);
        settings_postsCB.setSelected(false);

        // sets default to all N ie nothing is visible

        SettingsDao.updateAgeVisible(getUserName(),"N");
        SettingsDao.updateStatusVisible(getUserName(),"N");
        SettingsDao.updateFriendsVisible(getUserName(),"N");
        SettingsDao.updatePostsVisible(getUserName(),"N");

    }

    @FXML
    private void applyUserSettings() throws Exception{
        //TODO Store the values of the check boxes

        Boolean friendsSelected = settings_friendsListCB.isSelected();
        Boolean statusSelected = settings_emailCB.isSelected();
        Boolean postsSelected = settings_postsCB.isSelected();
        Boolean ageSelected = settings_AgeCB.isSelected();



        if(friendsSelected) {
            SettingsDao.updateFriendsVisible(getUserName(), "Y");
        } else if(!friendsSelected) {
            //if its not write N
            SettingsDao.updateFriendsVisible(getUserName(), "N");
        }

        if(statusSelected) {
            SettingsDao.updateStatusVisible(getUserName(),"Y");
        } else if(!statusSelected) {
            SettingsDao.updateStatusVisible(getUserName(),"N");
        }

        if(postsSelected) {
            SettingsDao.updatePostsVisible(getUserName(),"Y");
        } else if(!postsSelected) {
            SettingsDao.updatePostsVisible(getUserName(),"N");
        }

        if(ageSelected) {
            SettingsDao.updateAgeVisible(getUserName(),"Y");
        } else if(!ageSelected) {
            SettingsDao.updateAgeVisible(getUserName(),"N");
        }


        //Closes settings window after values are stored
        Stage stage = (Stage)settings_applySettings.getScene().getWindow();
        stage.close();
    }

}
