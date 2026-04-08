package com.example.integradora_ian_adael.Modelo;

public class libro {

    public String id_libro;
    public String titulo;
    public String autor;
    public int fecha_pub;
    public String genero;
    public boolean disponible;

    public libro(String id_libro, String titulo, String autor, int fecha_pub, String genero, boolean disponible) {
        this.id_libro   = id_libro;
        this.titulo     = titulo;
        this.autor      = autor;
        this.fecha_pub  = fecha_pub;
        this.genero     = genero;
        this.disponible = disponible;
    }

    public String getId_libro()  { return id_libro; }
    public String getTitulo()    { return titulo; }
    public String getAutor()     { return autor; }
    public int    getFecha_pub() { return fecha_pub; }
    public String getGenero()    { return genero; }
    public boolean isDisponible()  { return disponible; }
    public boolean getDisponible() { return disponible; }
}