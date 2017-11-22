package com.example.crodriguez.proyectofinalquestions.modelo;


public class Pregunta {

    private String descripcion_pregunta;
    private String fecha;
    private String categoria;
    private boolean respuestas;
    private String descripcion_respuestas;

    public Pregunta() {
    }

    public String getDescripcion_pregunta() {
        return descripcion_pregunta;
    }

    public void setDescripcion_pregunta(String descripcion_pregunta) {
        this.descripcion_pregunta = descripcion_pregunta;
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

    public String getDescripcion_respuestas() {
        return descripcion_respuestas;
    }

    public void setDescripcion_respuestas(String descripcion_respuestas) {
        this.descripcion_respuestas = descripcion_respuestas;
    }
}
