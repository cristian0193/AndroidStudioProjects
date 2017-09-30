package com.example.crodriguez.appfirebaselista.dominio;

import com.example.crodriguez.appfirebaselista.modelos.Tarea;

import java.util.List;

public interface ILtarea {

    void addTarea(Tarea tarea);

    List<Tarea> getLstTarea();

}
