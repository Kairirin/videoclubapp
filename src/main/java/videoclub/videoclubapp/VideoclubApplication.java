package videoclub.videoclubapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

/**
 * Class that starts the application
 * @author irenevinaderantón
 * @version 1
 */
public class VideoclubApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(VideoclubApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("CariClub");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(c -> {
            c.consume();
            Alert dialogExit = new Alert(Alert.AlertType.CONFIRMATION);
            dialogExit.setTitle("Are you leaving us?");
            dialogExit.setHeaderText("");
            dialogExit.setContentText("You are exiting the application");
            Optional<ButtonType> result = dialogExit.showAndWait();
            if (result.get() == ButtonType.OK){
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}