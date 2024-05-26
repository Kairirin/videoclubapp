package videoclub.videoclubapp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;

import java.io.*;
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

import java.net.URL;
import java.time.format.DateTimeFormatter;
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
    private ListView<Rent> rents;
    @FXML
    private ListView<Material> listProds;
    private ObservableList<Member> memberList;
    private ObservableList<Rent> rentList;
    private ObservableList<Material> listOfProducts;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        memberList = FXCollections.observableList(getControllerMem().getMemberList());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Rent> rents = new ArrayList<>();
        boolean found = false;
        int matIndex, rentIndex;
        BufferedReader file = null;
        try{
            file = new BufferedReader(new FileReader(new File("src/main/resources/sample/rents.txt")));
            String line = file.readLine();
            while(line != null){
                String[] parts = line.split(";");
                found = false;

                Material mat = searchMaterialInventory(parts[1]);
                Member mem;

                if(mat != null){
                    if(rents.size() > 0){
                        for(Rent r: rents){
                            if(r.getMember().getId().equals(parts[0])){
                                rentIndex = rents.indexOf(r);
                                rents.get(rentIndex).addProduct(mat);
                                rents.get(rentIndex).setReturnData(LocalDate.parse(parts[2],formatter));
                                found = true;
                            }
                        }
                        if(!found){
                            mem = searchMember(parts[0]);
                            if(mem != null){
                                rentIndex = rents.size();
                                rents.add(new Rent(mem));
                                rents.get(rentIndex).addProduct(mat);
                                rents.get(rentIndex).setReturnData(LocalDate.parse(parts[2], formatter));
                            }
                        }
                    }
                    else {
                        mem = searchMember(parts[0]);
                        if(mem != null){
                            rentIndex = rents.size();
                            rents.add(new Rent(mem));
                            rents.get(rentIndex).addProduct(mat);
                            rents.get(rentIndex).setReturnData(LocalDate.parse(parts[2], formatter));
                        }
                    }
                }
                line = file.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }finally {
            try{
                file.close();
            }catch(Exception e) {}
        }
        Collections.sort(rents, new Comparator<Rent>() {
            @Override
            public int compare(Rent o1, Rent o2) {
                return o1.getMember().getId().compareTo(o2.getMember().getId());
            }
        });
        return rents;
    }
    private Material searchMaterialInventory(String code){
        List<Material> materials = getControllerInv().getMaterials().getInventory();

        return materials.stream()
                .filter(mat -> mat.getCode().trim().equals(code.trim()))
                .findFirst().orElse(null);
    }
    private Member searchMember(String id){
        return memberList.stream()
                .filter(member -> member.getId().trim().equals(id.trim()))
                .findFirst().orElse(null);
    }
    public void saveFile(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        PrintWriter pw = null;
        try{
            pw = new PrintWriter("src/main/resources/sample/rents.txt");
            for(Rent t: rentList){
                for(Material mat: t.getProducts()){
                    pw.println(t.getMember().getId() + ";" + mat.getCode() + ";" + formatter.format(t.getReturnData()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            if(pw != null){
                pw.close();
            }
        }
    }
    @FXML
    public void addRent(ActionEvent actionEvent) throws IOException {
        Navigate.modalDialog("addRent.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
        reset(actionEvent);
    }
    public void actualiseRents(String id, List<Material> products, LocalDate date){
        Member mem = searchMember(id);
        Rent aux = new Rent(mem);
        int index;

        if(rentList.contains(aux)){
            index = rentList.indexOf(aux);
        }
        else
        {
            rentList.add(aux);
            index = rentList.indexOf(aux);
        }
        for(Material m: products){
            rentList.get(index).addProduct(m);
        }
        rentList.get(index).setReturnData(date);
        saveFile();
    }
    @FXML
    public void removeRent(ActionEvent actionEvent) throws IOException { //Si me da tiempo intentar que borre solo x productos con el método interno de la clase Rent
        if(!txtId.getText().isEmpty()){
            Rent r = searchRent();
            rentList.remove(r);
        }
        saveFile();
        Navigate.goToView("rent.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
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
        saveFile();
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
                if(r.getMember().getId().contains(txt) || r.getMember().getName().contains(txt) || r.getMember().getEmail().contains(txt)){
                    search = r;
                }
            }
        }
        else if (!txtId.getText().isEmpty()){
            String txt = txtId.getText();
            for(Rent r: rentList){
                if(r.getMember().getId().equals(txt)){
                    search = r;
                }
            }
        }
        return search;
    }
    public ObservableList<Rent> getRents(){
        return rentList;
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
    public MembersController getControllerMem(){
        FXMLLoader loader = new FXMLLoader(MembersController.class.getResource("members.fxml"));
        try{
            Parent root = (Parent)loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MembersController controller = (MembersController)loader.getController();
        return controller;
    }
    public InventoryController getControllerInv(){
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
