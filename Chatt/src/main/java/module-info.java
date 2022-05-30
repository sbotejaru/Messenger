module com.example.chatt {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.lang3;
    requires java.sql;
    requires java.desktop;


    opens com.example.chatt to javafx.fxml;
    exports com.example.chatt;
}