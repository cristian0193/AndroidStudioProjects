package com.example.crodriguez.applistatareas.Modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class Tarea {

    @ColumnInfo(name="nombre")
    private String nombre;

    @ColumnInfo(name="fecha")
    private String fecha;

    @ColumnInfo(name="realizada")
    private boolean realizada;

    public Tarea() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }





}
