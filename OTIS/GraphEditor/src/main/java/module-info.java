module com.semenovegor.grapheditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.semenovegor.grapheditor to javafx.fxml;
    exports com.semenovegor.grapheditor;
}