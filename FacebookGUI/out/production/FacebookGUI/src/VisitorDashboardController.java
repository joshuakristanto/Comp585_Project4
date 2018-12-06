import DAO.FriendsDao;
import DAO.ProfilesDao;
import Models.Friends;
import Models.Profiles;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisitorDashboardController implements Initializable {

    private String visitorName;
    private String myUserName;


    @FXML
    private Button vdb_logoutButton;

    @FXML
    private Button vdb_settingsButton;

    @FXML
    private Label vdb_firstNameLabel;

    @FXML
    private Label vdb_lastNameLabel;

    @FXML
    private Label vdb_AgeLabel;

    @FXML
    private Label vdb_EmailLabel;

    @FXML
    private Button vdb_HomeButton;

    @FXML
    private ListView vdb_FriendsListView;

    @FXML
    private TextArea vdb_statusArea;

    @FXML
    private TableView vdb_PostsTableView;

    @FXML
    private void loadMyDashBoard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user_dashboard.fxml"));
        Parent parent = loader.load();

        UserDashBoardController controller = loader.getController();
        controller.init(myUserName);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 1920) / 2);
        stage.setY((screenBounds.getHeight() - 1080) / 2);

        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.setTitle(myUserName + " Dashboard");
        stage.show();
    }

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
        stage.show();
    }

    // Accesses DB to load information of User
    public void init(String userName){

        this.visitorName = userName;
        vdb_FriendsListView.setStyle("-fx-font: 18pt 'Arial'");
        vdb_PostsTableView.setStyle("-fx-font-size: 18pt");
        try{

            ObservableList<Profiles> prof = ProfilesDao.searchProfiles(userName);
            System.out.println("Running");
            System.out.println(prof.get(0).getFirstName());
            setFirstNameLabel(prof.get(0).getFirstName().toUpperCase());
            setLastNameLabel(prof.get(0).getLastName().toUpperCase());
            setAge(prof.get(0).getAge());
            setEmail(prof.get(0).getEmail().toUpperCase());
            loadFriends();
            loadStatus();

        } catch(Exception e) {

        }
    }

    private void setFirstNameLabel(String firstName){
        vdb_firstNameLabel.setText(firstName);
    }

    private void setLastNameLabel(String lastName){
        vdb_lastNameLabel.setText(lastName);
    }

    private void setAge(String age){
        vdb_AgeLabel.setText(age);
    }

    private void setEmail(String email){
        vdb_EmailLabel.setText(email);
    }

    public void setMyUserName(String userName){
        myUserName = userName;
    }

    private void loadFriends(){
        try{
            //Get FriendsList from DB
            ObservableList<Friends> friendsList = FriendsDao.searchFriends(visitorName);
            //Insert List into ListView
            for(int i = 0; i < friendsList.size(); i++){
                System.out.println(friendsList.get(i).getFriendUserName());
                if(!vdb_FriendsListView.getItems().contains(friendsList.get(i).getFriendUserName())){
                    vdb_FriendsListView.getItems().add(friendsList.get(i).getFriendUserName());
                }

            }
        }catch (Exception e){

        }
    }

    private void loadStatus(){
        try{
            String statusMessage = ProfilesDao.searchProfiles(visitorName).get(0).getStatus();
            vdb_statusArea.setText(statusMessage);
        }catch (Exception e){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
