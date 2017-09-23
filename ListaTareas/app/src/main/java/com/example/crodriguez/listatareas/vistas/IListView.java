package com.example.crodriguez.listatareas.vistas;

import com.example.crodriguez.listatareas.modelos.Tarea;
import java.util.List;

public interface IListView {

    void clickAddTarea();

    void refrescarListaTareas();

    void refrescarTarea(int posicion);


}
