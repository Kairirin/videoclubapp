package videoclub.videoclubapp;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import videoclub.videoclubapp.management.Rent;
import videoclub.videoclubapp.materials.Material;
import videoclub.videoclubapp.users.Member;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AddRentController implements Initializable {
    @FXML
    private TextField txtMember;
    @FXML
    private Button btnMember;
    @FXML
    private TextField txtMaterial;
    @FXML
    private Button btnMat;
    @FXML
    private ListView membersList;
    @FXML
    private ListView materialsList;
    @FXML
    private ListView selectedList;
    @FXML
    private DatePicker returnDate;
    @FXML
    private Button btnDone;
    private ObservableList<Member> memberList;
    private ObservableList<Material> listOfProducts;
    private ObservableList<Material> selectedProducts;
    private List<Material> auxProducts;
    private String memberId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberList = FXCollections.observableList(getController().getControllerMem().getMemberList());
        listOfProducts = FXCollections.observableList(getController().getControllerInv().getMaterials().getInventory());
        auxProducts = new ArrayList<Material>();

        membersList.setItems(memberList);
        materialsList.setItems(listOfProducts);

        selectMember();
        selectItem();
    }
    public List<Member> searchMember(String text){
        return memberList.stream()
                .filter(member -> member.getId().trim().contains(text.trim()) || member.getName().trim().contains(text.trim()) || member.getEmail().trim().contains(text.trim()))
                .toList();
    }
    @FXML
    public void showMember(ActionEvent actionEvent){
        List<Member> search = searchMember(txtMember.getText());
        if(search!=null){
            ObservableList<Member> auxMemberList = FXCollections.observableList(search);
            membersList.setItems(auxMemberList);
        }
    }
    public void selectMember(){
        membersList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Member>() {
                    @Override
                    public void changed(ObservableValue<? extends Member> observableValue, Member mem, Member newMem) {
                        if (newMem != null) {
                            memberId = newMem.getId();
                        }
                    }
                }
        );
    }
    public List<Material> searchMaterial(String text){
        return listOfProducts.stream()
                .filter(mat -> mat.getCode().trim().contains(text.trim()) || mat.getTitle().trim().contains(text.trim()))
                .toList();
    }
    @FXML
    public void showMaterial(ActionEvent actionEvent){
        List<Material> search = searchMaterial(txtMaterial.getText());
        if(search!=null){
            ObservableList<Material> auxListOfProducts = FXCollections.observableList(search);
            materialsList.setItems(auxListOfProducts);
        }
    }
    public void selectItem(){
        materialsList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Material>() {
                    @Override
                    public void changed(ObservableValue<? extends Material> observableValue, Material ma, Material newMat) {
                        if (newMat != null) {
                            if(auxProducts.size() < 5){
                                auxProducts.add(newMat);
                                selectedProducts = FXCollections.observableList(auxProducts);
                                selectedList.setItems(selectedProducts);
                            }
                        }
                    }
                }
        );
    }
    @FXML
    public void addRent(ActionEvent actionEvent) {
        if(memberId !=  null && !selectedProducts.isEmpty() && returnDate.getValue() != null){
            getController().actualiseRents(memberId, auxProducts, returnDate.getValue());

            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Success!");
            dialog.setHeaderText("New rent added");
            dialog.showAndWait();
        }
        else{
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Oops!");
            dialog.setHeaderText("Some information is missing");
            dialog.showAndWait();
        }
    }
    private RentController getController(){
        FXMLLoader loader = new FXMLLoader(RentController.class.getResource("rent.fxml"));
        try{
            Parent root = (Parent)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RentController controller = (RentController)loader.getController();
        return controller;
    }
}
