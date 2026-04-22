module com.finalproject.universitymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.base;
    requires org.apache.poi.ooxml;
    requires java.desktop;

    opens com.finalproject.universitymanagementsystem to javafx.fxml;
    exports com.finalproject.universitymanagementsystem;
    opens com.finalproject.identification to javafx.fxml;
    exports com.finalproject.identification;
    exports com.finalproject.primary;
    opens com.finalproject.primary to javafx.fxml;
    exports com.finalproject.universitymanagementsystem.ListCells;
    opens com.finalproject.universitymanagementsystem.ListCells to javafx.fxml;
    exports com.finalproject.universitymanagementsystem.CellControllers;
    opens com.finalproject.universitymanagementsystem.CellControllers to javafx.fxml;
}