package videoclub.videoclubapp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import videoclub.videoclubapp.management.Rent;
import videoclub.videoclubapp.materials.*;
import videoclub.videoclubapp.users.Member;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Class of our program that stores all the information about renting
 * @author irenevinaderantón
 * @version 1.2
 */
public class RentController implements Initializable {
    @FXML
    private Button btnReset;
    @FXML
    private Label lblId;
    @FXML
    private TextField txtId;
    @FXML
    private Label lblProducts;
    @FXML
    private Button btnApply;
    @FXML
    private Button btnRemove;
    @FXML
    private DatePicker dateRent;
    @FXML
    private MenuItem btnMain;
    @FXML
    private MenuItem btnMembers;
    @FXML
    private MenuItem btnInventory;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnSearch;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private ListView rents;
    @FXML
    private ListView listProds;
    private ObservableList<Rent> rentList;
    private ObservableList<Material> listOfProducts;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rentList = FXCollections.observableList(readRents());
        rents.setItems(rentList);

        rents.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Rent>() {
                    @Override
                    public void changed(ObservableValue observableValue, Rent oldValue, Rent newValue) {
                        if (newValue != null) {
                            listOfProducts = FXCollections.observableList(newValue.getProducts());
                            txtId.setText(newValue.getMember().getId());
                            dateRent.setValue(newValue.getReturnData());
                            listProds.setItems(listOfProducts);
                        }
                    }
                }
        );
    }
    private List<Rent> readRents(){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Rent> rents = new ArrayList<>();
        List<Member> mems = getControllerMem().getMemberList();
        List<Material> materials = getControllerInv().getMaterials().getInventory();
        int memberIndex, matIndex, rentIndex;
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/sample/rents.txt"));
            for(int i = 0; i < lines.size(); i++){
                String[] parts = lines.get(i).split(";");

                for(Material m: materials){
                    if(m.getCode().equals(parts[1])){
                        matIndex = materials.indexOf(m);

                        if(rents.size() > 0){
                            for(Rent r: rents){
                                if(r.getMember().getId().equals(parts[0])){
                                    rentIndex = rents.indexOf(r);
                                    rents.get(rentIndex).addProduct(materials.get(matIndex));
                                    rents.get(rentIndex).setReturnData(LocalDate.parse(parts[2]));
                                }
                            }
                        }
                        else {
                            for (Member mem : mems) {
                                if (mem.getId().equals(parts[0])) {
                                    memberIndex = mems.indexOf(mem);

                                    rentIndex = rents.size();
                                    rents.add(new Rent(mems.get(memberIndex)));
                                    rents.get(rentIndex).addProduct(materials.get(matIndex));
                                    rents.get(rentIndex).setReturnData(LocalDate.parse(parts[2]));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return rents;
    }
    @FXML
    public void addRent(ActionEvent actionEvent){
        //Añadir material a prestar
    }
    @FXML
    public void removeRent(ActionEvent actionEvent){ //Si me da tiempo intentar que borre solo x productos con el método interno de la clase Rent
        if(!txtId.getText().isEmpty()){
            rentList.remove(new Rent(new Member(txtId.getText())));
        }
    }
    @FXML
    public void extendDate(ActionEvent actionEvent){
        LocalDate newDate = dateRent.getValue();
        Rent aux = searchRent();

        for(Rent r: rentList){
            if (r.equals(aux)){
                r.setReturnData(newDate);
            }
        }
    }
    @FXML
    public void showRent(ActionEvent actionEvent){
        Rent search = searchRent();
        if(search!=null){
            txtId.setText(search.getMember().getId());

            listOfProducts = FXCollections.observableList(search.getProducts());
            dateRent.setValue(search.getReturnData());
            listProds.setItems(listOfProducts);
        }
    }
    private Rent searchRent(){
        List<Rent> auxiliarList = rentList;
        Rent search = null;

        if(!txtSearch.getText().isEmpty()){
            String txt = txtSearch.getText();
            for(Rent r: rentList){
                if(r.getMember().getId().contains(txt) || r.getMember().getName().contains(txt) || r.getMember().getEmail().contains(txt) || r.getMember().getPhoneNumber() == (Integer.parseInt(txt))){
                    search = r;
                }
            }
        }
        return search;
    }
    @FXML
    public void viewMain(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("main.fxml",(Stage)(btnMain.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void viewInventory(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)(btnInventory.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void viewMembers(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("members.fxml",(Stage)(btnMembers.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void exitProgram(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("rent.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    private MembersController getControllerMem(){
        FXMLLoader loader = new FXMLLoader(MembersController.class.getResource("members.fxml"));
        try{
            Parent root = (Parent)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MembersController controller = (MembersController)loader.getController();
        return controller;
    }
    private InventoryController getControllerInv(){
        FXMLLoader loader = new FXMLLoader(InventoryController.class.getResource("inventory.fxml"));
        try{
            Parent root = (Parent)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InventoryController controller = (InventoryController)loader.getController();
        return controller;
    }
}
