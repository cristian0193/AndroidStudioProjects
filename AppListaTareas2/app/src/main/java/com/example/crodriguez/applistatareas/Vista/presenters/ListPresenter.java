package com.example.crodriguez.applistatareas.Vista.presenters;

import com.example.crodriguez.applistatareas.Dominio.ILtarea;
import com.example.crodriguez.applistatareas.Dominio.LTarea;
import com.example.crodriguez.applistatareas.Modelo.Tarea;
import com.example.crodriguez.applistatareas.Vista.IListView;
import java.util.List;

public class ListPresenter implements IListPresenter{

    private IListView view;
    private ILtarea ltarea;

    public ListPresenter(IListView view){
        this.view = view;
        ltarea = new LTarea();
    }

    @Override
    public void addTarea(String descTarea, String fecha) {
        Tarea objTarea = new Tarea();
        objTarea.setNombre(descTarea);
        objTarea.setFecha(fecha);
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
