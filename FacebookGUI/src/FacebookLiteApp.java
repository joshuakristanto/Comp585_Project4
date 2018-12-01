import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FacebookLiteApp extends Application {

    public void start(final Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
