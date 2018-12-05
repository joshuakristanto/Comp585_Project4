import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class VisitorDashboardController {

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
    private void loadMyDashBoard(ActionEvent event) throws IOException {
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 1920) / 2);
        stage.setY((screenBounds.getHeight() - 1080) / 2);

        stage.setResizable(false);
        stage.setScene(signUpPageScene);
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
}
