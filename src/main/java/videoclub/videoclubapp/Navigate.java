package videoclub.videoclubapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import videoclub.videoclubapp.management.Inventory;
import videoclub.videoclubapp.materials.Material;

import java.io.IOException;
/**
 * Class that change between views
 * @author irenevinaderantón
 * @version 1
 */
public class Navigate {
    public static void goToView(String path, Stage newStage) throws IOException {
        Parent root = FXMLLoader.load(
                VideoclubApplication.class.getResource(path)); //Need to pass a String with de username, the view will be different if it is a Admin or a Worker
        Scene view = new Scene(root);
        newStage.setScene(view);
        newStage.show();
    }
    public static void modalDialog(String path, Stage newStage) throws IOException {
        Parent view = FXMLLoader.load(VideoclubApplication.class.getResource(path));
        Scene viewScene = new Scene(view);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(viewScene);
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(newStage);
        secondaryStage.setOnCloseRequest(e -> e.getEventType());
        secondaryStage.showAndWait();
        //Navigate.goToView("main.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());//Falta pasar el nombre de usuario como parámetro para las cosas que hacen diferente
    }
}
