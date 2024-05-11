package videoclub.videoclubapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Class that starts the application
 * @author irenevinaderantón
 * @version 1
 */
public class VideoclubApplication extends Application {
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(VideoclubApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(VideoclubApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("CariClub");
        //scene.getStylesheets().add(
                //getClass().getResource("design.css").toExternalForm());
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}