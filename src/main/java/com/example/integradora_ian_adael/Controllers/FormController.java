package com.example.integradora_ian_adael.Controllers;

import com.example.integradora_ian_adael.Modelo.libro;
import com.example.integradora_ian_adael.Services.libroServicio;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormController {

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtAnio;
    @FXML private TextField txtGenero;
    @FXML private CheckBox  chkDisponible;
    @FXML private Label     lblError;

    private libroServicio servicio;
    private libro libroEditar;

    public void setServicio(libroServicio servicio) {
        this.servicio = servicio;
    }

    public void setLibroEditar(libro l) {
        this.libroEditar = l;
        txtId.setText(l.getId_libro());
        txtId.setDisable(true);
        txtTitulo.setText(l.getTitulo());
        txtAutor.setText(l.getAutor());
        txtAnio.setText(String.valueOf(l.getFecha_pub()));
        txtGenero.setText(l.getGenero());
        chkDisponible.setSelected(l.isDisponible());
    }

    @FXML
    private void onGuardar() {
        lblError.setText("");
        try {
            if (libroEditar == null) {
                servicio.agregar(
                        txtId.getText().trim(),
                        txtTitulo.getText().trim(),
                        txtAutor.getText().trim(),
                        txtAnio.getText().trim(),
                        txtGenero.getText().trim(),
                        chkDisponible.isSelected()
                );
            } else {
                servicio.actualizar(
                        libroEditar.getId_libro(),
                        txtTitulo.getText().trim(),
                        txtAutor.getText().trim(),
                        txtAnio.getText().trim(),
                        txtGenero.getText().trim(),
                        chkDisponible.isSelected()
                );
            }
            cerrar();
        } catch (Exception e) {
            lblError.setText(e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        cerrar();
    }

    private void cerrar() {
        ((Stage) txtId.getScene().getWindow()).close();
    }
}
