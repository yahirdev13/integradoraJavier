package com.example.integradora_ian_adael.Repositorio;

import com.example.integradora_ian_adael.Modelo.libro;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class libroRepositorio {

    public ArrayList<libro> listaLibros;

    public libroRepositorio() {
        listaLibros = new ArrayList<>();
        cargarDesdeArchivo();
    }

    // Crud

    public void agregar(libro libro) {
        listaLibros.add(libro);
        guardarEnArchivo();
    }

    public void actualizar(libro libroEditado) {
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).id_libro.equals(libroEditado.id_libro)) {
                listaLibros.set(i, libroEditado);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void eliminar(libro libro) {
        listaLibros.remove(libro);
        guardarEnArchivo();
    }

    public boolean existeId(String id_libro) {
        for (libro libro : listaLibros) {
            if (libro.id_libro.equalsIgnoreCase(id_libro)) {
                return true;
            }
        }
        return false;
    }

    // Leer archivo

    private void cargarDesdeArchivo() {
        File archivo = new File("data/catalogo.csv");

        // Si el archivo no existe aún, inicia con lista vacia
        if (!archivo.exists()) {
            return;
        }

        try {
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();

                if (linea.isEmpty()) continue;

                String[] partes = linea.split("\\|");

                if (partes.length == 6) {
                    String id_libro    = partes[0].trim();
                    String titulo      = partes[1].trim();
                    String autor       = partes[2].trim();
                    int fecha_pub      = Integer.parseInt(partes[3].trim());
                    String genero      = partes[4].trim();
                    boolean disponible = Boolean.parseBoolean(partes[5].trim());

                    listaLibros.add(new libro(id_libro, titulo, autor, fecha_pub, genero, disponible));
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Dato invalido en el archivo: " + e.getMessage());
        }
    }

    // Escribir archivo

    public void guardarEnArchivo() {
        // Crea la carpeta data/ si no existe
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try {
            PrintWriter writer = new PrintWriter(new File("data/catalogo.csv"));

            for (libro libro : listaLibros) {
                writer.println(
                        libro.id_libro   + "|" +
                                libro.titulo     + "|" +
                                libro.autor      + "|" +
                                libro.fecha_pub  + "|" +
                                libro.genero     + "|" +
                                libro.disponible
                );
            }

            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Exportar reporte

    public boolean exportarReporte() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try {
            PrintWriter writer = new PrintWriter(new File("data/reporte_catalogo.csv"));

            // Encabezado
            writer.println("ID Libro|Titulo|Autor|Fecha Publicacion|Genero|Disponible");

            for (libro libro : listaLibros) {
                writer.println(
                        libro.id_libro  + "|" +
                                libro.titulo    + "|" +
                                libro.autor     + "|" +
                                libro.fecha_pub + "|" +
                                libro.genero    + "|" +
                                (libro.disponible ? "Si" : "No")
                );
            }

            writer.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Error al exportar: " + e.getMessage());
            return false;
        }
    }
}