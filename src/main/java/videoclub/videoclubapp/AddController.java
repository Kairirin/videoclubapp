package videoclub.videoclubapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import videoclub.videoclubapp.materials.*;
import videoclub.videoclubapp.users.Member;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    public Button btnAdd;
    @FXML
    public Button btnVal;
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
        //materials = getController().getMaterials().getInventory();
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
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(2))) + 1;
                txtCodigo.setText("BR" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case 1:
                for(Material m: materials){
                    if (m instanceof DVD)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(3))) + 1;
                txtCodigo.setText("DVD" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case 2:
                for(Material m: materials){
                    if (m instanceof VHS)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(3))) + 1;
                txtCodigo.setText("VHS" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case 3:
                for(Material m: materials){
                    if (m instanceof Playstation)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(2))) + 1;
                txtCodigo.setText("PS" + nextNumber);
                txtCodigo.setEditable(false);
                break;
            case 4:
                for(Material m: materials){
                    if (m instanceof Nintendo)
                        aux.add(m);
                }
                nextNumber = (Integer.parseInt(aux.get(aux.size() - 1).getCode().substring(3))) + 1;
                txtCodigo.setText("NIN" + nextNumber);
                txtCodigo.setEditable(false);
                break;
        }
    }
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
