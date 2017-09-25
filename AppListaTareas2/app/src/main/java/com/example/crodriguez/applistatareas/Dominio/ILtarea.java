package com.example.crodriguez.applistatareas.Dominio;

import com.example.crodriguez.applistatareas.Modelo.Tarea;

import java.util.List;

public interface ILtarea {

    void addTarea(Tarea tarea);

    List<Tarea> getLstTarea();

}
