/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Oscar
 */
public class LoginPageController implements Initializable {
    
    @FXML
    private TextField login_username;
    
    @FXML
    private PasswordField login_password;
    
    @FXML
    private Button login_button;
    
    @FXML
    private Button sign_up_button;
    
    @FXML
    private void loadSignUpPage(ActionEvent event) throws IOException{
        System.out.println("Sign up button pressed");
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("sign_up_page.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(signUpPageScene);
        stage.show();
    }
    
    @FXML
    private void checkCredentials(ActionEvent event) throws IOException{
        System.out.println("Login button pressed");
        // TODO Check password, call encryption method here
        //If password correct log in
        //Else warn user incorrect password
        loadUserDashBoard(event);
    }

    private void loadUserDashBoard(ActionEvent event) throws IOException{
        System.out.println("You logged in!");
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(signUpPageScene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
