package com.example.crodriguez.listatareas.vistas.presenters;

import com.example.crodriguez.listatareas.modelos.Tarea;

import java.util.List;

public interface IListPresenter {

    void addTarea(String nombreTarea);

    List<Tarea> obtenerTareas();

    void itemCambioEstado(int posicion, boolean estado);
}
