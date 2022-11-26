module de.hvolk.hvsuppliers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires json.simple;

    opens de.hvolk.hvsupplier to javafx.fxml;
    exports de.hvolk.hvsupplier;
}