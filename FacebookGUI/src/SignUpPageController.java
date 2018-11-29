/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oscar
 */
public class SignUpPageController implements Initializable {
    
    @FXML
    private TextField sign_up_firstName;
    
    @FXML
    private TextField sign_up_lastName;
    
    @FXML
    private DatePicker sign_up_birthday;
    
    @FXML
    private TextField sign_up_email;
    
    @FXML
    private TextField sign_up_username;
    
    @FXML
    private PasswordField sign_up_password;
    
    @FXML
    private PasswordField sign_up_confirmPassword;
    
    @FXML
    private Button sign_up_registerButton;
    
    @FXML
    private Button sign_up_cancelButton;
    
    @FXML
    private void registerUser (ActionEvent event) throws Exception{
        //TODO Using DAO objects pass data to DB
        //TODO Let user know if account creation was successful

        //Returns back to login after account creation
        System.out.println("Cancel button pressed");
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(signUpPageScene);
        stage.show();
    }
    
    @FXML
    private void cancelRegistration (ActionEvent event) throws Exception{

        //TODO Prompt user if they are sure they want to cancel registration

        System.out.println("Cancel button pressed");
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(signUpPageScene);
        stage.show();
    }

    @FXML
    private void checkUsername(){
        // Once the user clicks somewhere else i.e. another field, this method will execute.
        // Check DB if username already exits if so warn user.
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
