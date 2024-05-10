module videoclub.videoclubapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens videoclub.videoclubapp to javafx.fxml;
    exports videoclub.videoclubapp;
    exports videoclub.videoclubapp.materials;
    opens videoclub.videoclubapp.materials to javafx.fxml;
}