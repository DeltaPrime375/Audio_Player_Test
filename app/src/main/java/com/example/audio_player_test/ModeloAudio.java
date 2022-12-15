package com.example.audio_player_test;

import java.io.Serializable;

public class ModeloAudio implements Serializable {
    String direccion, titulo, duracion;

    public ModeloAudio(String direccion, String titulo, String duracion) {
        this.direccion = direccion;
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }


}
