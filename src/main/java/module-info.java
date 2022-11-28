module com.example.psp2223 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.psp2223 to javafx.fxml;
    exports com.example.psp2223;
}