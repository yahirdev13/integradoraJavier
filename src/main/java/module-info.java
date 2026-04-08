module com.example.integradora_ian_adael {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.integradora_ian_adael to javafx.fxml;
    opens com.example.integradora_ian_adael.Controllers to javafx.fxml;
    opens com.example.integradora_ian_adael.Modelo to javafx.base;
    exports com.example.integradora_ian_adael;
}