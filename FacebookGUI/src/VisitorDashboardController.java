import DAO.FriendsDao;
import DAO.PostsDao;
import DAO.ProfilesDao;
import DAO.SettingsDao;
import Models.Friends;
import Models.Posts;
import Models.Profiles;
import Models.Settings;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private TableColumn<Posts, String> vdb_table_userName;

    @FXML
    private TableColumn<Posts, String> vdb_table_Posts;

    @FXML
    private TableColumn<Posts, String> vdb_table_Date;

    @FXML
    private Label vdb_postsLabel;

    @FXML
    private Label vdb_friendsListLabel;

    @FXML
    private Label vdb_statusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // Page Loaders

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
    private void openSettings(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("settings_page.fxml"));
        Parent parent = loader.load();

        SettingsController controller = loader.getController();
        controller.init(myUserName);
        controller.setMainStage((Stage) ((Node)event.getSource()).getScene().getWindow());

        Stage stage = new Stage();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 600) / 2);
        stage.setY((screenBounds.getHeight() - 1150) / 2);

        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.setTitle("Settings");
        stage.show();
    }

    // End of Page Loaders


    // Data Loaders
    private void loadPosts(){

        try{
            System.out.println("Loading Posts");
            ObservableList<Posts> postList = PostsDao.searchPosts(visitorName);
            ObservableList<Posts> reverseOrder = FXCollections.observableArrayList();
            for(int i = postList.size(); i > 0; i-- ){
                reverseOrder.add(postList.get(i-1));
            }
            vdb_PostsTableView.setItems(reverseOrder);

        }catch (Exception e){

        }
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

    private void loadSettings(){
        try{
            ObservableList<Settings> settingsList = SettingsDao.searchSettings(visitorName);
            // Visitor Settings
            // Age
            String ageSetting = settingsList.get(0).getAge();
            if(ageSetting.equals("Y")){
                vdb_AgeLabel.setVisible(true);
            }else{
                vdb_AgeLabel.setVisible(false);
            }
            // Friends List
            String friendsListSetting = settingsList.get(0).getFriends();
            if(friendsListSetting.equals("Y")){
                vdb_FriendsListView.setVisible(true);
                loadFriends();
            }else{
                vdb_FriendsListView.setVisible(false);
            }
            // Posts
            String postsSetting = settingsList.get(0).getPosts();
            if(postsSetting.equals("Y")){
                vdb_PostsTableView.setVisible(true);
                initTable();
                loadPosts();
            }else{
                vdb_PostsTableView.setVisible(false);
            }
            // Status
            String statusSetting = settingsList.get(0).getStatus();
            if(statusSetting.equals("Y")){
                vdb_statusArea.setVisible(true);
                loadStatus();
            }else{
                vdb_statusArea.setVisible(false);
            }
        }catch (Exception e){

        }
    }

    // End of Data Loaders

    private void initTable(){
        vdb_table_userName = new TableColumn<>("Username");
        vdb_table_Posts = new TableColumn<>("Posts");
        vdb_table_Date = new TableColumn<>("Date");

        vdb_table_userName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        vdb_table_userName.setMinWidth(140);
        vdb_table_Posts.setCellValueFactory(new PropertyValueFactory<>("PostText"));
        vdb_table_Posts.setMinWidth(910);
        vdb_table_Date.setCellValueFactory(new PropertyValueFactory<>("PostTime"));
        vdb_table_Date.setMinWidth(235);

        vdb_PostsTableView.getColumns().addAll(vdb_table_userName, vdb_table_Posts, vdb_table_Date);
        vdb_PostsTableView.setEditable(false);
    }

    // Accesses DB to load information of User
    public void init(String userName){

        this.visitorName = userName;
        vdb_FriendsListView.setStyle("-fx-font: 18pt 'Arial'");
        vdb_PostsTableView.setStyle("-fx-font-size: 18pt");

        try{
            ObservableList<Profiles> prof = ProfilesDao.searchProfiles(visitorName);

            String firstName = prof.get(0).getFirstName().toLowerCase();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);

            System.out.println("Running");
            System.out.println(prof.get(0).getAge());
            System.out.println(prof.get(0).getFirstName());
            setFirstNameLabel(prof.get(0).getFirstName().toUpperCase());
            setLastNameLabel(prof.get(0).getLastName().toUpperCase());
            setAge(calculateAge(prof.get(0).getAge()));
            setEmail(prof.get(0).getEmail().toUpperCase());
            vdb_postsLabel.setText(firstName + "'s Posts");
            vdb_friendsListLabel.setText(firstName + "'s Friends");
            vdb_statusLabel.setText(firstName + "'s Status");
            loadSettings();

        } catch(Exception e) {

        }
    }

    private String calculateAge(String birthday){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String currentDate = formatter.format(date);

        int age = 0;

        int currentYear = Integer.parseInt(currentDate.substring(0,4));
        int currentMonth = Integer.parseInt(currentDate.substring(5,7));
        int currentDay = Integer.parseInt(currentDate.substring(8));

        int year = Integer.parseInt(birthday.substring(0,4));
        int month = Integer.parseInt(birthday.substring(5,7));
        int day = Integer.parseInt(birthday.substring(8));

        if(currentMonth <= month && currentDay < day){
            age = currentYear - year - 1;
        }else{
            age = currentYear - year;
        }

        return age + "";
    }

    // Setter Methods

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

}
