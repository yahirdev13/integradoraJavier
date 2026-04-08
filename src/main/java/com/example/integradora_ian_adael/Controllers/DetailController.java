package com.example.integradora_ian_adael.Controllers;

import com.example.integradora_ian_adael.Modelo.libro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailController {

    @FXML private Label lblId;
    @FXML private Label lblTitulo;
    @FXML private Label lblAutor;
    @FXML private Label lblAnio;
    @FXML private Label lblGenero;
    @FXML private Label lblDisponible;

    public void setLibro(libro l) {
        lblId.setText(l.getId_libro());
        lblTitulo.setText(l.getTitulo());
        lblAutor.setText(l.getAutor());
        lblAnio.setText(String.valueOf(l.getFecha_pub()));
        lblGenero.setText(l.getGenero());
        lblDisponible.setText(l.isDisponible() ? "Sí" : "No");
    }

    @FXML
    private void onRegresar() {
        ((Stage) lblId.getScene().getWindow()).close();
    }
}
