package com.example.crodriguez.appfirebaselista.vistas.presenters;

import com.example.crodriguez.appfirebaselista.modelos.Tarea;

import java.util.List;

public interface IListPresenter {

    void addTarea(String nombreTarea, String Tarea);

    List<Tarea> obtenerTareas();

    void itemCambioEstado(int posicion, boolean estado);
}
