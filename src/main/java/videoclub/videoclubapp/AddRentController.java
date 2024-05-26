package videoclub.videoclubapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import videoclub.videoclubapp.materials.Material;
import videoclub.videoclubapp.users.Member;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Controller for the Add new rent view. Associated to addRent.fxml
 * @author irenevinaderantón
 * @version 1.2
 */
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
    private ListView<Member> membersList;
    @FXML
    private ListView<Material> materialsList;
    @FXML
    private ListView<Material> selectedList;
    @FXML
    private DatePicker returnDate;
    @FXML
    private Button btnDone;
    private ObservableList<Member> memberList;
    private ObservableList<Material> listOfProducts;
    private ObservableList<Material> selectedProducts;
    private List<Material> auxProducts;
    private String memberId;

    /**
     * The initialize method will charge all the data of members and products, and fill the tableviews
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberList = FXCollections.observableList(getController().getControllerMem().getMemberList());
        listOfProducts = FXCollections.observableList(findAvailable());
        auxProducts = new ArrayList<>();

        membersList.setItems(memberList);
        materialsList.setItems(listOfProducts);

        selectMember();
        selectItem();
    }
    private List<Material> findAvailable(){
        List<Material> aux = getController().getControllerInv().getMaterials().getInventory();
        List<Material> availables = new ArrayList<>();

        for(Material m: aux){
            if (m.isAvailable())
                availables.add(m);
        }

        return availables;
    }

    /**
     * Method to search certain member in the list
     * @param text The String that will be compared to the id, name or email of all the members
     * @return A List with the Member objects which match the condition.
     */
    public List<Member> searchMember(String text){
        return memberList.stream()
                .filter(member -> member.getId().trim().contains(text.trim()) || member.getName().trim().contains(text.trim()) || member.getEmail().trim().contains(text.trim()))
                .toList();
    }
    /**
     * Method who shows only the members who match the text typed at the textfield
     * @param actionEvent This method activates when the Search member button is pressed
     */
    @FXML
    public void showMember(ActionEvent actionEvent){
        List<Member> search = searchMember(txtMember.getText());
        if(search!=null){
            ObservableList<Member> auxMemberList = FXCollections.observableList(search);
            membersList.setItems(auxMemberList);
        }
    }

    /**
     * This method permits store the member for the new rent by selecting it
     */
    public void selectMember(){
        membersList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Member> observableValue, Member mem, Member newMem) {
                        if (newMem != null) {
                            memberId = newMem.getId();
                        }
                    }
                }
        );
    }
    /**
     * Method to search a certain material from the list
     * @param text The String that will be compared to the code or title of all the materials of the list
     * @return a List with all the materials that match the condition
     */
    public List<Material> searchMaterial(String text){
        return listOfProducts.stream()
                .filter(mat -> mat.getCode().trim().contains(text.trim()) || mat.getTitle().trim().contains(text.trim()))
                .toList();
    }
    /**
     * Method who shows only the materials which match the text typed at the textfield
     * @param actionEvent This method activates when the Search material button is pressed
     */
    @FXML
    public void showMaterial(ActionEvent actionEvent){
        List<Material> search = searchMaterial(txtMaterial.getText());
        if(search!=null){
            ObservableList<Material> auxListOfProducts = FXCollections.observableList(search);
            materialsList.setItems(auxListOfProducts);
        }
    }
    /**
     * This method permits store the materials for the new rent in an auxiliar list by selecting they.
     */
    public void selectItem(){
        materialsList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<>() {
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

    /**
     * This method send the member, list of products and return date to the RentController, which will manage and add the new rent.
     * @param actionEvent This method activates when pressing the Add button
     */
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
