package com.example.ycuartas.listaandroiducc.dominio;

import com.example.ycuartas.listaandroiducc.modelos.Tarea;

import java.util.List;

public interface ILtarea {

    void addTarea(Tarea tarea);

    void removeTarea(Tarea tarea);

    List<Tarea> getLstTarea();

}
