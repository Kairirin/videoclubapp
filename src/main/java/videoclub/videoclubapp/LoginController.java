/**
 * Controller associated to login.fxml
 * @author irenevinaderantón
 * @version 1
 */
package videoclub.videoclubapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import videoclub.videoclubapp.users.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label labelUser;
    @FXML
    private TextField txtUser;
    @FXML
    private Label labelPass;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnLogin;
    List<User> users;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image("file:src/main/resources/sample/logo.png");
        imgLogo.setImage(img);
        users = readUsers();
    }
    private List<User> readUsers(){
        List<User> users = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/sample/users.txt"));
            for(String l: lines){
                if (l.split(";")[0].equals("a"))
                    users.add(new Admin(l.split(";")[1],l.split(";")[2]));
                else
                    users.add(new Worker(l.split(";")[1],l.split(";")[2]));
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return users;
    }
    @FXML
    public void login(ActionEvent actionEvent) throws IOException {
        String name = txtUser.getText();
        String passwd = txtPass.getText();
        boolean found = false;

        for(User u: users){
            if(u.getName().equals(name) && u.getPassword().equals(passwd)){
                found = true;
                Navigate.goToView("main.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());//Falta pasar el nombre de usuario como parámetro para las cosas que hacen diferente
                // admin y workers
            }
        }
        if(!found){
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setHeaderText("Invalid username or password");
            dialog.showAndWait();
        }
    }
}
