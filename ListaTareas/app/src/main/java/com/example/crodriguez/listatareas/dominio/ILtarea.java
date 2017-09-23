package com.example.crodriguez.listatareas.dominio;

import com.example.crodriguez.listatareas.modelos.Tarea;

import java.util.List;

public interface ILtarea {

    void addTarea (Tarea tarea);

    List<Tarea> getLstTarea();

}
