package com.example.crodriguez.proyectofinalquestions.modelo;


public class Pregunta {

    private String usuario;
    private String descripcion;
    private String fecha;
    private String categoria;
    private boolean respuestas;

    public Pregunta() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isRespuestas() {
        return respuestas;
    }

    public void setRespuestas(boolean respuestas) {
        this.respuestas = respuestas;
    }
}
