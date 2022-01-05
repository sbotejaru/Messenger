module com.example.chatt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatt to javafx.fxml;
    exports com.example.chatt;
}