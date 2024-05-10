package videoclub.videoclubapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigate {
    public static void goToView(String path, Stage newStage) throws IOException {
        Parent root = FXMLLoader.load(
                VideoclubApplication.class.getResource(path));
        Scene view = new Scene(root);
        newStage.setScene(view);
        newStage.show();
    }
}
