/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.Profiles;
import javafx.collections.ObservableList;
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
import DAO.*;
import util.PasswordEncryption;

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

    public void init(){
        sign_up_birthday.setStyle("-fx-font-size: 20px;");
    }

    
    @FXML
    private void registerUser (ActionEvent event) throws Exception{
        String encryptedPassword = PasswordEncryption.generatePassword(getPassword());
        ProfilesDao.addProfile(getFirstName(), getLastName(), getBirthday(), getUserName(), getEmail(), encryptedPassword, "", "Y");
        // user will be added to the settings table
        SettingsDao.addUserToSettings(getUserName());

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
            sign_up_password.setStyle("-fx-border-color: #DCDCDC;");
            sign_up_confirmPassword.setStyle("-fx-border-color: #DCDCDC;");
        }else if(sign_up_password.getText().equals(sign_up_confirmPassword.getText())) {
            passwordDoesntMatch.setVisible(false);
            passwordMatches.setVisible(true);
            sign_up_password.setStyle("-fx-border-color: #00FF3C;");
            sign_up_confirmPassword.setStyle("-fx-border-color: #00FF3C;");
        }else{
            passwordMatches.setVisible(false);
            passwordDoesntMatch.setVisible(true);
            sign_up_password.setStyle("-fx-border-color: red;");
            sign_up_confirmPassword.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    private void checkUsername() {
        try{
            // Checks if username is in the DB
            ObservableList<Profiles> prof = ProfilesDao.searchProfiles(getUserName());

            if(prof.size() > 0){ // Username is taken
                sign_up_username.setStyle("-fx-border-color: red; -fx-text-inner-color: red;");
                username_avail.setVisible(true);

            }else if(sign_up_username.getText().equals("")){
                sign_up_username.setStyle("-fx-border-color: #DCDCDC; -fx-text-inner-color: black;");
                username_avail.setVisible(false);
            }else{
                sign_up_username.setStyle("-fx-border-color: #00FF3C; -fx-text-inner-color: #00FF3C;");
                username_avail.setVisible(false);
            }
        } catch (Exception e){

        }
    }

    //Getters
    private String getFirstName(){
        return sign_up_firstName.getText();
    }

    private String getLastName(){
        return sign_up_lastName.getText();
    }

    private String getBirthday(){
        return sign_up_birthday.getValue().toString();
    }

    private String getEmail(){
        return sign_up_email.getText();
    }

    private String getPassword(){
        return sign_up_confirmPassword.getText();
    }

    private String getUserName(){
        return sign_up_username.getText();
    }
}
