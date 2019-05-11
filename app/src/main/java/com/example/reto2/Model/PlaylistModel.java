package com.example.reto2.Model;

import java.util.ArrayList;
import java.util.List;

public class PlaylistModel {

    private String imagen;
    private String nombre_lista;
    private String nombre_usuario;
    private String descripcion;
    private int num_canciones;
    private int num_fans;
    private List<CancionModel> lista_canciones;

    public PlaylistModel(){

    }

    public PlaylistModel(String imagen, String nombre_lista, String nombre_usuario, String descripcion, int num_canciones, int num_fans) {
        this.imagen = imagen;
        this.nombre_lista = nombre_lista;
        this.nombre_usuario = nombre_usuario;
        this.descripcion = descripcion;
        this.num_canciones = num_canciones;
        this.num_fans = num_fans;
        lista_canciones = new ArrayList<>();

    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre_lista() {
        return nombre_lista;
    }

    public void setNombre_lista(String nombre_lista) {
        this.nombre_lista = nombre_lista;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNum_canciones() {
        return num_canciones;
    }

    public void setNum_canciones(int num_canciones) {
        this.num_canciones = num_canciones;
    }

    public int getNum_fans() {
        return num_fans;
    }

    public void setNum_fans(int num_fans) {
        this.num_fans = num_fans;
    }

    public List<CancionModel> getLista_canciones() {
        return lista_canciones;
    }

    public void setLista_canciones(List<CancionModel> lista_canciones) {
        this.lista_canciones = lista_canciones;
    }
}
