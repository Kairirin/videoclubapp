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
import videoclub.videoclubapp.users.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class of our program that stores all the materials that integrated the videoclub
 * @author irenevinaderantón
 * @version 1.1
 */
public class Inventory implements Initializable {
    @FXML
    private TextField txtSearch;
    @FXML
    private Menu btnAdd;
    @FXML
    private Menu btnFilter;
    @FXML
    private MenuItem btnDVD;
    @FXML
    private MenuItem btnBR;
    @FXML
    private MenuItem btnVHS;
    @FXML
    private MenuItem btnPS;
    @FXML
    private MenuItem btnNIN;
    @FXML
    private Button btnSearch;
    @FXML
    private MenuItem btnMain;
    @FXML
    private MenuItem btnMembers;
    @FXML
    private MenuItem btnRent;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private TableView<Material> tableInv;
    @FXML
    private TableColumn<Material, String> colCode;
    @FXML
    private TableColumn<Material, String> colTitle;
    @FXML
    private TableColumn<Material, Integer> colYear;
    @FXML
    private TableColumn<Material, String> colGenre;
    @FXML
    private TableColumn<Material, String> colExtra;
    @FXML
    private Button btnReset;
    private ObservableList<Material> inventory;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventory = FXCollections.observableArrayList(readFile());
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colExtra.setCellValueFactory(new PropertyValueFactory<>("extra"));

        tableInv.setItems(inventory);
    }
    private static List<Material> readFile(){
        List<Material> materials = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/sample/materials.txt"));
            for(String l: lines){
                if (l.split(";")[0].contains("BR"))
                    materials.add(new BluRay(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("DVD"))
                    materials.add(new DVD(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("VHS"))
                    materials.add(new VHS(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("NIN"))
                    materials.add(new Nintendo(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("PS"))
                    materials.add(new Playstation(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return materials;
    }
    public void addMaterial(ActionEvent actionEvent){
        //Método que añade nuevo material

    }
    @FXML
    public void searchMaterial(ActionEvent actionEvent){
        List<Material> auxiliarList = readFile();
        List<Material> search = new ArrayList<>();

        if(!txtSearch.getText().isEmpty()){
            String txt = txtSearch.getText();
            for(Material m: auxiliarList){
                if(m.getCode().contains(txt) || m.getTitle().contains(txt)){
                    search.add(m);
                }
            }
        }
        ObservableList<Material> searched = FXCollections.observableArrayList(search);
        tableInv.setItems(searched);
    }
    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    public void modifyMaterial(){
        //Método que modifica un material
    }
    public void removeMaterial(){
        //Método que borra el material
    }
    @FXML
    public void viewMain(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("main.fxml",(Stage)(btnMain.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void viewMembers(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("members.fxml",(Stage)(btnMembers.getParentPopup().getOwnerWindow()).getScene().getWindow());
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
