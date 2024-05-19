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

public class AddController implements Initializable {

    @FXML
    public Button btnAdd;
    @FXML
    private Label lblTipo;
    @FXML
    private ChoiceBox<String> cboxTipos;
    @FXML
    private Label lblCodigo;
    @FXML
    private TextField txtTitulo;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Label lblAnyo;
    @FXML
    private TextField txtOtros;
    @FXML
    private Label lblGenero;
    @FXML
    private TextField txtGenero;
    @FXML
    private Label lblOtros;
    @FXML
    private TextField txtAnyo;
    private List<Material> materials;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materials = getController().getMaterials().getInventory();
        cboxTipos.setItems(FXCollections.observableArrayList(
                "BluRay", "DVD", "VHS", "PlayStation", "Nintendo")
        );
    }
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
    public void setCategory(){
        List<Material> aux = new ArrayList<>();
        int nextNumber;

        switch (cboxTipos.getSelectionModel().getSelectedItem()){
            case "BluRay":
                for(Material m: materials){
                    if (m instanceof BluRay)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(2))) + 1;
                txtCodigo.setText("BR" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case "DVD":
                for(Material m: materials){
                    if (m instanceof DVD)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(2))) + 1;
                txtCodigo.setText("DVD" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case "VHS":
                for(Material m: materials){
                    if (m instanceof VHS)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(2))) + 1;
                txtCodigo.setText("VHS" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case "PlayStation":
                for(Material m: materials){
                    if (m instanceof Playstation)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(2))) + 1;
                txtCodigo.setText("PS" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case "Nintendo":
                for(Material m: materials){
                    if (m instanceof Nintendo)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(2))) + 1;
                txtCodigo.setText("NIN" + nextNumber);
                txtCodigo.setEditable(false);
                break;
        }
    }
    @FXML
    public void addMaterial(ActionEvent actionEvent) {
        Material mat = null;
        setCategory();
        if (!txtTitulo.getText().isEmpty() && !txtAnyo.getText().isEmpty() && !txtGenero.getText().isEmpty() && !txtOtros.getText().isEmpty()) {
            switch (cboxTipos.getSelectionModel().getSelectedItem()){
                case "BluRay":
                    mat = new BluRay(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case "DVD":
                    mat = new DVD(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case "VHS":
                    mat = new VHS(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case "PlayStation":
                    mat = new Playstation(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
                case "Nintendo":
                    mat = new Nintendo(txtCodigo.getText(), txtTitulo.getText(), Integer.parseInt(txtAnyo.getText()), txtGenero.getText(),txtOtros.getText());
                    break;
            }
            getController().getMaterials().addMaterial(mat);

            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Success!");
            dialog.setHeaderText("Inventory actualized");
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
