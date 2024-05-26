/**
 * Controller associated to login.fxml
 * @author irenevinaderantón
 * @version 1.1
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
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnLogin;
    private List<User> users;
    private User currentUser;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image("file:src/main/resources/sample/logo.png");
        imgLogo.setImage(img);
        users = readUsers();
    }

    /**
     * This method reads the file with the authorized users
     * @return a List with usernames and passwords
     */
    private List<User> readUsers(){
        List<User> users = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/sample/users.txt"));
            for(String l: lines){
                if (l.split(";")[0].equals("admin")) {
                    users.add(new Admin(l.split(";")[1],l.split(";")[2]));
                } else
                    users.add(new Worker(l.split(";")[1],l.split(";")[2]));
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return users;
    }

    /**
     * Method that check that the username and password exist in our system
     * @param actionEvent Activated when pressed de Login button
     * @throws IOException
     */
    @FXML
    public void login(ActionEvent actionEvent) throws IOException {
        String name = txtUser.getText();
        String passwd = txtPass.getText();
        boolean found = false;

        for(User u: users){
            if(u.getName().equals(name) && u.getPassword().equals(passwd)){
                currentUser = searchUser(name);
                found = true;
                Navigate.goToView("main.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
            }
        }
        if(!found){
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setHeaderText("Invalid username or password");
            dialog.showAndWait();
        }
    }
    private User searchUser(String name){
        return users.stream()
                .filter(u -> u.getName().trim().equals(name.trim()))
                .findFirst().orElse(null);
    }

    /**
     * Method to pass the information about our current user to the main menu view
     * @return A User
     */
    public User getCurrentUser(){ return currentUser; }
}
