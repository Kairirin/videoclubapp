package videoclub.videoclubapp;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import videoclub.videoclubapp.materials.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the Add new material view. Associated to addMaterial.fxml
 * @author irenevinaderantón
 * @version 2
 */
public class AddController implements Initializable {
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnVal;
    @FXML
    private ChoiceBox<String> cboxTipos;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAnyo;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtOtros;
    private List<Material> materials;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materials = getController().getMaterials().getInventory();
        cboxTipos.setItems(FXCollections.observableArrayList(
                "BluRay", "DVD", "VHS", "PlayStation", "Nintendo")
        );
    }

    /**
     * Method to get the InventoryController
     * @return InventoryController
     */
    private InventoryController getController(){
        FXMLLoader loader = new FXMLLoader(InventoryController.class.getResource("inventory.fxml"));
        try{
            Parent root = (Parent)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InventoryController controller = (InventoryController)loader.getController();
        return controller;
    }

    /**
     * Method to set the right code to our next Material depending on its type
     * @param actionEvent This method activates once we press de "Validate" button
     */
    @FXML
    public void setCategory(ActionEvent actionEvent){
        List<Material> aux = new ArrayList<>();
        int nextNumber;

        switch (cboxTipos.getSelectionModel().getSelectedIndex()){
            case 0:
                for(Material m: materials){
                    if (m instanceof BluRay)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.getLast().getCode().substring(2))) + 1;
                txtCodigo.setText("BR" + nextNumber);
                break;
            case 1:
                for(Material m: materials){
                    if (m instanceof DVD)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.getLast().getCode().substring(3))) + 1;
                txtCodigo.setText("DVD" + nextNumber);
                break;
            case 2:
                for(Material m: materials){
                    if (m instanceof VHS)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.getLast().getCode().substring(3))) + 1;
                txtCodigo.setText("VHS" + nextNumber);
                break;
            case 3:
                for(Material m: materials){
                    if (m instanceof Playstation)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.getLast().getCode().substring(2))) + 1;
                txtCodigo.setText("PS" + nextNumber);
                break;
            case 4:
                for(Material m: materials){
                    if (m instanceof Nintendo)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.getLast().getCode().substring(3))) + 1;
                txtCodigo.setText("NIN" + nextNumber);
                break;
        }
    }

    /**
     * This method creates the new material from the data written in the textfields
     * @param actionEvent This method activates once we press the Add new button
     */
    @FXML
    public void addMaterial(ActionEvent actionEvent) {
        Material mat = null;

        if (!txtTitulo.getText().isEmpty() && !txtAnyo.getText().isEmpty() && !txtGenero.getText().isEmpty() && !txtOtros.getText().isEmpty()) {
            switch (cboxTipos.getSelectionModel().getSelectedIndex()){
                case 0:
                    mat = new BluRay(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case 1:
                    mat = new DVD(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case 2:
                    mat = new VHS(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case 3:
                    mat = new Playstation(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case 4:
                    mat = new Nintendo(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
            }
            getController().getMaterials().addMaterial(mat);

            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Success!");
            dialog.setHeaderText("Inventory actualized. You can close this window");
            dialog.showAndWait();
        }
        else{
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Missing information");
            dialog.setContentText("Please, fill all the fields");
            dialog.showAndWait();
        }
    }
}
