module com.converter {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.converter to javafx.fxml;
    exports com.converter;
}
