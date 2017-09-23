package com.example.crodriguez.listatareas.vistas.presenters;

import com.example.crodriguez.listatareas.dominio.ILtarea;
import com.example.crodriguez.listatareas.dominio.LTarea;
import com.example.crodriguez.listatareas.vistas.IListView;
import com.example.crodriguez.listatareas.modelos.*;

import java.util.List;

public class ListPresenter implements IListPresenter{

    private IListView view;
    private ILtarea ltarea;

    public ListPresenter(IListView view){
        this.view = view;
        ltarea = new LTarea();
    }

    @Override
    public void addTarea(String descTarea) {
        Tarea objTarea = new Tarea();
        objTarea.setNombre(descTarea);
        objTarea.setRealizada(false);

        ltarea.addTarea(objTarea);
        view.refrescarListaTareas();
    }

    @Override
    public List<Tarea> obtenerTareas() {
        return ltarea.getLstTarea();
    }

    @Override
    public void itemCambioEstado(int posicion, boolean estado) {
        ltarea.getLstTarea().get(posicion).setRealizada(estado);
        view.refrescarTarea(posicion);
    }
}
