package videoclub.videoclubapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

/**
 * Class that change between views
 * @author irenevinaderantón
 * @version 2
 */
public class Navigate {
    /**
     * Method to change between normal views
     * @param path path to the new fxml file
     * @param newStage
     * @throws IOException
     */
    public static void goToView(String path, Stage newStage) throws IOException {
        Parent root = FXMLLoader.load(
                VideoclubApplication.class.getResource(path));
        Scene view = new Scene(root);
        newStage.setScene(view);
        newStage.show();
    }

    /**
     * Method to change to a modal dialog view
     * @param path Path to the new fxml file
     * @param newStage
     * @throws IOException
     */
    public static void modalDialog(String path, Stage newStage) throws IOException {
        Parent view = FXMLLoader.load(VideoclubApplication.class.getResource(path));
        Scene viewScene = new Scene(view);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(viewScene);
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(newStage);
        secondaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeRequest(secondaryStage);
        });
        secondaryStage.showAndWait();
    }

    /**
     * Method that displays the close request alert everytime we attempt to log out
     * @param stage
     */
    private static void closeRequest(Stage stage){
        Alert confirm = new Alert(Alert.AlertType.INFORMATION);
        confirm.initStyle(StageStyle.DECORATED);
        confirm.initModality(Modality.APPLICATION_MODAL);
        confirm.initOwner(stage);
        confirm.getDialogPane().setContentText("Returning to previous page");
        confirm.getDialogPane().setHeaderText(null);
        confirm.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> { stage.close(); });

    }
    public static void exitWarning(){
        Alert dialogExit = new Alert(Alert.AlertType.CONFIRMATION);
        dialogExit.setTitle("Are you leaving us?");
        dialogExit.setHeaderText("");
        dialogExit.setContentText("You are exiting the application");
        Optional<ButtonType> result = dialogExit.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        }
    }
}
