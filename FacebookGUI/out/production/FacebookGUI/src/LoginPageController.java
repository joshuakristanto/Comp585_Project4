/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.PasswordEncryption;

/**
 *
 * @author Oscar
 */
public class LoginPageController {

    @FXML
    private TextField login_username;
    
    @FXML
    private PasswordField login_password;
    
    @FXML
    private Button login_button;
    
    @FXML
    private Button sign_up_button;

    @FXML
    private Label login_IncorrectInfo;
    
    @FXML
    private void loadSignUpPage(ActionEvent event) throws IOException{
        System.out.println("Sign up button pressed");
        Parent signUpPageParent = FXMLLoader.load(getClass().getResource("sign_up_page.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 1000) / 2);
        stage.setY((screenBounds.getHeight() - 1000) / 2);

        stage.setResizable(false);
        stage.setScene(signUpPageScene);
        stage.setTitle("Sign Up");
        stage.show();
    }
    
    @FXML
    private void checkCredentials(ActionEvent event) throws IOException{
        System.out.println("Login button pressed");
        // TODO Check password, call encryption method here
        Boolean checker = PasswordEncryption.checkPassword(getUsername(), getPassword());
        if(checker){
            //If password correct log in
            loadUserDashBoard(event);
        }
        else{
            login_IncorrectInfo.setVisible(true);
        }
    }

    private void loadUserDashBoard(ActionEvent event) throws IOException{
        try{
            System.out.println("You logged in!");
            Parent loader = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));

            Scene signUpPageScene = new Scene(loader);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();


            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - 1920) / 2);
            stage.setY((screenBounds.getHeight() - 1080) / 2);

            stage.setResizable(false);
            stage.setScene(signUpPageScene);
            stage.setTitle(getUsername() + " Dashboard");
            stage.show();
        }catch (Exception e){

        }
    }

    private String getUsername(){
        return login_username.getText();
    }

    private String getPassword(){
        return login_password.getText();
    }
}
