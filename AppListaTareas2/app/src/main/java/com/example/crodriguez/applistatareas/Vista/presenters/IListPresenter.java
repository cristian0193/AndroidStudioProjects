package com.example.crodriguez.applistatareas.Vista.presenters;

import com.example.crodriguez.applistatareas.Modelo.Tarea;

import java.util.List;

public interface IListPresenter {

    void addTarea(String nombreTarea, String fecha);

    List<Tarea> obtenerTareas();

    void itemCambioEstado(int posicion, boolean estado);
}
