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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.PasswordEncryption;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetPageController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField resetPage_username;

    @FXML
    private PasswordField resetPage_newPassword;

    @FXML
    private PasswordField resetPage_confirmPassword;

    @FXML
    private Button resetPage_changePassword;

    @FXML
    private Button resetPage_returnButton;

    @FXML
    private Label resetPage_SuccessLabel;

    @FXML
    private Label resetPage_LabelFailure;

    @FXML
    private void resetPassword(){
        String username = resetPage_username.getText();
        String password = resetPage_newPassword.getText();
        String confirmPassword = resetPage_confirmPassword.getText();

        if(password.equals(confirmPassword)){
            try{
                PasswordEncryption.resetPassword(username, confirmPassword);
                resetPage_SuccessLabel.setVisible(true);
            } catch (Exception e){
                resetPage_LabelFailure.setVisible(true);
            }
        }
    }

    @FXML
    private void matchPasswords(){
        if(resetPage_newPassword.getText().equals("") || resetPage_confirmPassword.getText().equals("")){
            resetPage_newPassword.setStyle("-fx-border-color: #DCDCDC;");
            resetPage_confirmPassword.setStyle("-fx-border-color: #DCDCDC;");
        }else if(resetPage_newPassword.getText().equals(resetPage_confirmPassword.getText())) {
            resetPage_newPassword.setStyle("-fx-border-color: #00FF3C;");
            resetPage_confirmPassword.setStyle("-fx-border-color: #00FF3C;");
        }else{
            resetPage_newPassword.setStyle("-fx-border-color: red;");
            resetPage_confirmPassword.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    private void loadLoginPage(ActionEvent event) throws IOException {
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

}
