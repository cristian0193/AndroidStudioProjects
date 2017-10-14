package com.example.ycuartas.listaandroiducc.vistas.presenters;

import com.example.ycuartas.listaandroiducc.modelos.Tarea;

import java.util.List;

public interface IListPresenter {

    void addTarea(String nombreTarea, String Tarea);

    List<Tarea> obtenerTareas();

    void itemCambioEstado(int posicion, boolean estado);

    void obtenerTareasFirebase();
}
