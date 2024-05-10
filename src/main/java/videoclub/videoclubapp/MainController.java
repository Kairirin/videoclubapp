package videoclub.videoclubapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    private Button btnLend;
    @FXML
    private Button btnlogout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void pressButton(ActionEvent actionEvent) throws IOException {
        if(btnInv.isPressed()){
            Navigate.goToView("inventory.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
        }
        else if (btnMem.isPressed()){
            Navigate.goToView("members.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
        }
        else if (btnLend.isPressed()){
            Navigate.goToView("lending.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
        }
        else if (btnlogout.isPressed()){
            System.exit(0);
        }
    }
}