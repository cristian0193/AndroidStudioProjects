package cv.yeison.apptodolist.vista.presenters;

import java.util.List;

import cv.yeison.apptodolist.dominio.ILtarea;
import cv.yeison.apptodolist.dominio.LTarea;
import cv.yeison.apptodolist.modelo.Tarea;
import cv.yeison.apptodolist.vista.IListView;

/**
 * Created by andres on 01/10/2017.
 */

public class ListPresenter implements IListPresenter {

    private IListView view;
    private ILtarea ltarea;

    public ListPresenter(IListView view){
        this.view = view;
        ltarea = new LTarea();
    }


    @Override
    public void addTarea(String nombreTarea, String fecha) {

        Tarea objTarea = new Tarea();
        objTarea.setNombre(nombreTarea);
        objTarea.setFecha(fecha);
        objTarea.setRealizada(false);

        ltarea.addTarea(objTarea);
        view.refrescarListaTareas(ltarea.getLstTarea());

    }

    @Override
    public List<Tarea> obtenerTareas() {
        return ltarea.getLstTarea();
    }

    @Override
    public void itemCambioEstado(int posicion, boolean estado) {
        Tarea tarea = ltarea.obtenerxID(posicion+1);
        tarea.setRealizada(estado);
        ltarea.actualizar(tarea);
        view.refrescarTarea(tarea, posicion);
    }
}
