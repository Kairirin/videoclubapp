module videoclub.videoclubapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens videoclub.videoclubapp to javafx.fxml;
    exports videoclub.videoclubapp;
}