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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller of Inventory, associated to the Inventory class, that stores all the materials that integrated the videoclub. Associated to inventory.fxml
 * @author irenevinaderantón
 * @version 2
 */
public class InventoryController implements Initializable {
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtOtros;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtAnyo;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnRemove;
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

    /**
     * The initialize method sets all the data in the tableviews
     * @param url
     * @param resourceBundle
     */
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
        selectItem();
    }
    public Inventory getMaterials(){
        return materials;
    }

    /**
     * This method put all the data about a certain material in the fields of below. This will serve to modify that information.
     */
    public void selectItem(){
        tableInv.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Material> observableValue, Material material, Material newMaterial) {
                        if (newMaterial != null) {
                            txtCodigo.setText(newMaterial.getCode());
                            txtTitulo.setText(newMaterial.getTitle());
                            txtAnyo.setText("" + newMaterial.getYear());
                            txtGenero.setText(newMaterial.getGenre());
                            txtOtros.setText(newMaterial.getExtra());

                            btnModify.setVisible(true);
                            btnRemove.setVisible(true);
                        }
                    }
                }
        );
    }

    /**
     * This method permits change to the Add new material view
     * @param actionEvent Activates once the Add new button is pressed.
     * @throws IOException
     */
    @FXML
    private void addMaterial(ActionEvent actionEvent) throws IOException {
        Navigate.modalDialog("addMaterial.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
        reset(actionEvent);
    }

    /**
     * This method shows a new List with only the materials that matches the fragment of text typed in the search textfield
     * @param actionEvent Activates when the Search button is pressed
     */
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

    /**
     * This method serves to filter out materials depending on their type (subclasses of material (DVD, Nintendo...)
     * @param actionEvent The method activates in according to the radio button selected.
     */
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

    /**
     * Method which search a certain material according to its code
     * @param code Code that has to match
     * @return A Material with that code
     */
    private Material searchMaterialInventory(String code){
        return inventory.stream()
                .filter(mat -> mat.getCode().trim().equals(code.trim()))
                .findFirst().orElse(null);
    }

    /**
     * This method takes all the new information of a material and change it in the Inventory class
     * @param actionEvent Activates when the Modify button is pressed
     * @throws IOException
     */
    @FXML
    public void modifyMaterial(ActionEvent actionEvent) throws IOException {
        Material mat = searchMaterialInventory(txtCodigo.getText());
        int index = inventory.indexOf(mat);
        materials.modifyMaterial(index, txtTitulo.getText(), txtGenero.getText(), Integer.parseInt(txtAnyo.getText()), txtOtros.getText());
        reset(actionEvent);
    }
    /**
     * This method removes a material in the Inventory class
     * @param actionEvent Activates when the Remove button is pressed
     * @throws IOException
     */
    @FXML
    public void removeMaterial(ActionEvent actionEvent) throws IOException {
        Material mat = searchMaterialInventory(txtCodigo.getText());
        int index = inventory.indexOf(mat);

        Alert dialogRemove = new Alert(Alert.AlertType.CONFIRMATION);
        dialogRemove.setTitle("You are going to remove");
        dialogRemove.setHeaderText("");
        dialogRemove.setContentText("Are you sure you want to remove the item?");
        Optional<ButtonType> result = dialogRemove.showAndWait();
        if (result.get() == ButtonType.OK){
            materials.removeMaterial(inventory.get(index));
            reset(actionEvent);

            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Success!");
            dialog.setHeaderText("Inventory actualized");
            dialog.showAndWait();
        }
        else {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("You cancelled");
            dialog.setHeaderText("Operation cancelled");
            dialog.showAndWait();
        }
    }
    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
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
        Navigate.exitWarning();
    }
}
