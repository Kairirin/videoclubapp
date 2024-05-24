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
import videoclub.videoclubapp.users.Member;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Class of our program that stores all the members
 * @author irenevinaderantón
 * @version 1.5
 */
public class MembersController implements Initializable {
    @FXML
    private Label lblId;
    @FXML
    private TextField txtId;
    @FXML
    private Label lblName;
    @FXML
    private TextField txtName;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblPhone;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnApply;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnSearch;
    @FXML
    private MenuItem btnMain;
    @FXML
    private MenuItem btnMaterials;
    @FXML
    private MenuItem btnRent;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private TableView<Member> tableMem;
    @FXML
    private TableColumn<Member, String> colId;
    @FXML
    private TableColumn<Member, String> colName;
    @FXML
    private TableColumn<Member, String> colEmail;
    @FXML
    private TableColumn<Member, Integer> colPhoneNumber;
    @FXML
    private Button btnReset;
    private ObservableList<Member> listOfMembers;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setVisible(true);
        btnApply.setVisible(false);
        btnRemove.setVisible(false);
        setTable();
        listOfMembers = FXCollections.observableArrayList(readFile());
        tableMem.setItems(listOfMembers);

        tableMem.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Member>() {
                    @Override
                    public void changed(ObservableValue<? extends Member> observableValue, Member member, Member newMember) {
                        if (newMember != null) {
                            txtId.setText(newMember.getId());
                            txtName.setText(newMember.getName());
                            txtEmail.setText(newMember.getEmail());
                            txtPhone.setText("" + newMember.getPhoneNumber());

                            btnApply.setVisible(true);
                            btnRemove.setVisible(true);
                            btnAdd.setVisible(false);
                        }
                    }
                }
        );
    }

    /**
     * Method that set de configuration of the TableView displayed in this part of the application
     */
    private void setTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }
    /**
     * Method that read the text file where is all the data about members
     */
    private List<Member> readFile(){
        List<Member> members = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/sample/members.txt"));
            return lines.stream()
                    .map(line -> new Member(line.split(";")[0],
                            line.split(";")[1],
                            line.split(";")[2],
                            Integer.parseInt(line.split(";")[3])))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    /**
     * Method that saves all the data about members in a txt file
     */
    private void saveMembers(){
        try (PrintWriter pw = new PrintWriter("src/main/resources/sample/members.txt")) {
            listOfMembers.stream()
                    .forEach(member -> {
                        pw.println(member.getId() + ";" +
                                member.getName() + ";" +
                                member.getEmail() + ";" +
                                member.getPhoneNumber());
                    });
        } catch (Exception e) {}
    }
    /**
     * Method that permits add a new member to the list by fill all the necessary fields
     */
    public void addNewMember(ActionEvent actionEvent){
        if(!txtId.getText().isEmpty() && !txtName.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtPhone.getText().isEmpty()){
            Member m = new Member(txtId.getText(), txtName.getText(), txtEmail.getText(), Integer.parseInt(txtPhone.getText()));
            listOfMembers.add(m);
            saveMembers();
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Success!");
            dialog.setHeaderText("The member has been added");
            dialog.showAndWait();
        }
        else{
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Missing information");
            dialog.setContentText("Please, fill all the fields");
            dialog.showAndWait();
        }
    }
    /**
     * Method that permits search a member from a character or word contained in their id or name
     */
    @FXML
    public void searchMember(ActionEvent actionEvent){
        List<Member> auxiliarList = readFile();
        List<Member> search = new ArrayList<>();

        if(!txtSearch.getText().isEmpty()){
            String txt = txtSearch.getText();
            for(Member m: auxiliarList){
                if(m.getId().contains(txt) || m.getName().contains(txt)){
                    search.add(m);
                }
            }
        }
        ObservableList<Member> searched = FXCollections.observableArrayList(search);
        tableMem.setItems(searched);
    }
    /**
     * Method that permits reset all the configuration of the view
     */
    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("members.fxml",(Stage)((Node) actionEvent.getSource()).getScene().getWindow());
    }
    /**
     * Method that permits save all changes made in certain member's data
     */
    @FXML
    public void modifyMember(ActionEvent actionEvent) throws IOException {
        int position = tableMem.getSelectionModel().getSelectedIndex();
        if (position >= 0) {
            listOfMembers.get(position).setId(txtId.getText());
            listOfMembers.get(position).setName(txtName.getText());
            listOfMembers.get(position).setEmail(txtEmail.getText());
            listOfMembers.get(position).setPhoneNumber(Integer.parseInt(txtPhone.getText()));

            saveMembers();
            reset(actionEvent);
        }
    }
    /**
     * Method that removes certain selected member from our data base
     */
    public void removeMember(ActionEvent actionEvent){
        int position = tableMem.getSelectionModel().getSelectedIndex();
        if (position >= 0) {
            listOfMembers.remove(position);
            saveMembers();
        }
    }
    /**
     * Methods that allow the navigation to other parts of the application
     */
    @FXML
    public void viewMain(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("main.fxml",(Stage)(btnMain.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void viewMaterials(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("inventory.fxml",(Stage)(btnMaterials.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void viewRent(ActionEvent actionEvent) throws IOException {
        Navigate.goToView("rent.fxml",(Stage)(btnRent.getParentPopup().getOwnerWindow()).getScene().getWindow());
    }
    @FXML
    public void exitProgram(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
    public List<Member> getMemberList(){
        return listOfMembers;
    }
}
