/**
 * Main view of the program when user has successfully logued in
 * @author irenevinaderantón
 * @version 1
 */
package videoclub.videoclubapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label lblWelcome;
    @FXML
    private Button btnInv;
    @FXML
    private Button btnMem;
    @FXML
    private Button btnRent;
    @FXML
    private Button btnlogout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void viewInventory(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    @FXML
    public void viewMembers(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("members.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    @FXML
    public void viewRent(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("rent.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    @FXML
    public void exitProgram(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
}