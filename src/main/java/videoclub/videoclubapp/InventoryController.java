package videoclub.videoclubapp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import videoclub.videoclubapp.management.Inventory;
import videoclub.videoclubapp.materials.*;
import videoclub.videoclubapp.users.Member;

import java.io.IOException;
import java.io.PrintWriter;
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
public class InventoryController implements Initializable {
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnRemove;
    @FXML
    private Menu btnFilter;
    @FXML
    private MenuItem btnDVD;
    @FXML
    private RadioButton filterBR;
    @FXML
    private RadioButton filterDVD;
    @FXML
    private RadioButton filterVHS;
    @FXML
    private RadioButton filterPS;
    @FXML
    private RadioButton filterNIN;
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
    private static ObservableList<Material> inventory;
    private Inventory materials;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materials = new Inventory();
        inventory = FXCollections.observableArrayList(materials.getInventory());
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colExtra.setCellValueFactory(new PropertyValueFactory<>("extra"));

        tableInv.setItems(inventory);
    }
    public Inventory getMaterials(){
        return materials;
    }
    @FXML
    private void addMaterial(ActionEvent actionEvent) throws IOException {
        Navigate.modalDialog("add_ModalDialog.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    @FXML
    public void searchMaterial(ActionEvent actionEvent){
        List<Material> auxiliarList = materials.getInventory();
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
    public void filterMaterial(ActionEvent actionEvent){
        List<Material> auxiliarList = materials.getInventory();
        List<Material> filtered = new ArrayList<>();

        if(filterBR.isSelected()){
            for(Material m: auxiliarList){
                if(m instanceof BluRay){
                    filtered.add(m);
                }
            }
            filterNIN.setSelected(false);
            filterPS.setSelected(false);
            filterDVD.setSelected(false);
            filterVHS.setSelected(false);
        } else if (filterDVD.isSelected()){
            for(Material m: auxiliarList){
                if(m instanceof DVD){
                    filtered.add(m);
                }
            }
            filterNIN.setSelected(false);
            filterPS.setSelected(false);
            filterBR.setSelected(false);
            filterVHS.setSelected(false);
        } else if (filterVHS.isSelected()){
            for(Material m: auxiliarList){
                if(m instanceof VHS){
                    filtered.add(m);
                }
            }
            filterNIN.setSelected(false);
            filterPS.setSelected(false);
            filterDVD.setSelected(false);
            filterBR.setSelected(false);
        } else if (filterPS.isSelected()){
            for(Material m: auxiliarList){
                if(m instanceof Playstation){
                    filtered.add(m);
                }
            }
            filterNIN.setSelected(false);
            filterBR.setSelected(false);
            filterDVD.setSelected(false);
            filterVHS.setSelected(false);
        } else if (filterNIN.isSelected()){
            for(Material m: auxiliarList){
                if(m instanceof Nintendo){
                    filtered.add(m);
                }
            }
            filterBR.setSelected(false);
            filterPS.setSelected(false);
            filterDVD.setSelected(false);
            filterVHS.setSelected(false);
        }
        ObservableList<Material> filters = FXCollections.observableArrayList(filtered);
        tableInv.setItems(filters);
    }
    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    @FXML
    public void modifyMaterial(ActionEvent actionEvent){
        //Implementar método
    }
    @FXML
    public void removeMaterial(ActionEvent actionEvent){
        int position = tableInv.getSelectionModel().getSelectedIndex();
        if (position >= 0) {
            materials.removeMaterial(inventory.get(position));

            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION); //Comprobar bien
            dialog.setTitle("Success!");
            dialog.setHeaderText("Inventory actualized");
            dialog.showAndWait();
        }
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
