module com.example.programm_8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.programm_8 to javafx.fxml;
    exports com.example.programm_8;
    exports com.example.programm_8.Controllers;
    opens com.example.programm_8.Controllers to javafx.fxml;
}