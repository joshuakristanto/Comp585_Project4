/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oscar
 */
public class SignUpPageController {
    
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
    private Label username_avail;

    @FXML
    private Label passwordMatches;

    @FXML
    private Label passwordDoesntMatch;

    
    @FXML
    private void registerUser (ActionEvent event) throws Exception{
        //TODO Using DAO objects pass data to DB
        //TODO Let user know if account creation was successful
        //Returns back to login after account creation
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
    private void cancelRegistration (ActionEvent event) throws Exception{

        //TODO Prompt user if they are sure they want to cancel registration

        System.out.println("Cancel button pressed");
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
    private void matchPasswords(){
        if(sign_up_password.getText().equals("") || sign_up_confirmPassword.getText().equals("")){
            passwordMatches.setVisible(false);
            passwordDoesntMatch.setVisible(false);
        }else if(sign_up_password.getText().equals(sign_up_confirmPassword.getText())) {
            passwordDoesntMatch.setVisible(false);
            passwordMatches.setVisible(true);
        }else{
            passwordMatches.setVisible(false);
            passwordDoesntMatch.setVisible(true);
        }
    }

    @FXML
    private void checkUsername(){
        // TODO Check DB if username already exits if so warn user.
        if(!sign_up_username.getText().equals("username")){ // Username is taken
            sign_up_username.setStyle("-fx-border-color: red; -fx-text-inner-color: red;");
            username_avail.setVisible(true);

        }else if(sign_up_username.getText().equals("")){
            sign_up_username.setStyle("-fx-border-color: #DCDCDC; -fx-text-inner-color: black;");
            username_avail.setVisible(false);
        }else{
            sign_up_username.setStyle("-fx-border-color: green; -fx-text-inner-color: green;");
            username_avail.setVisible(false);
        }
    }

}
