package videoclub.videoclubapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import videoclub.videoclubapp.materials.*;
import videoclub.videoclubapp.users.Member;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Class of our program that stores all the members
 * @author irenevinaderantón
 * @version 1.1
 */
public class MembersController implements Initializable {
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnSearch;
    @FXML
    private MenuItem btnMain;
    @FXML
    private MenuItem btnMaterials;
    @FXML
    private MenuItem btnRent;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private TableView<Member> tableMem;
    @FXML
    private TableColumn<Material, String> colId;
    @FXML
    private TableColumn<Material, String> colName;
    @FXML
    private TableColumn<Material, String> colEmail;
    @FXML
    private TableColumn<Material, Integer> colPhoneNumber;
    @FXML
    private Button btnReset;
    private ObservableList<Member> listOfMembers;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfMembers = FXCollections.observableArrayList(readFile());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        tableMem.setItems(listOfMembers);
    }
    private static List<Member> readFile(){
        List<Member> materials = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/sample/members.txt"));
            return lines.stream()
                    .map(line -> new Member(line.split(";")[0],
                            line.split(";")[1],
                            line.split(";")[2],
                            Integer.parseInt(line.split(";")[3])))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public void addNewMember(ActionEvent actionEvent){
        //Método que añade nuevo material

    }
    @FXML
    public void searchMember(ActionEvent actionEvent){
        List<Member> auxiliarList = readFile();
        List<Member> search = new ArrayList<>();

        if(!txtSearch.getText().isEmpty()){
            String txt = txtSearch.getText();
            for(Member m: auxiliarList){
                if(m.getId().contains(txt) || m.getName().contains(txt)){
                    search.add(m);
                }
            }
        }
        ObservableList<Member> searched = FXCollections.observableArrayList(search);
        tableMem.setItems(searched);
    }
    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    public void modifyMember(){
        //Método que modifica un material
    }
    public void removeMember(){
        //Método que borra el material
    }
    @FXML
    public void viewMain(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("main.fxml",(Stage)(btnMain.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void viewMaterials(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)(btnMaterials.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void viewRent(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("rent.fxml",(Stage)(btnRent.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void exitProgram(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
}
