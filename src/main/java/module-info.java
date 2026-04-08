module com.example.integradora_ian_adael {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.integradora_ian_adael to javafx.fxml;
    exports com.example.integradora_ian_adael;
}