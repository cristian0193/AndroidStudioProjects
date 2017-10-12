package cv.yeison.apptodolist.vista;

import java.util.List;

import cv.yeison.apptodolist.modelo.Tarea;

/**
 * Created by andres on 01/10/2017.
 */

public interface IListView {

    void clickAddTarea();

    void refrescarListaTareas(List<Tarea> lstTarea);

    void refrescarTarea(Tarea tarea, int posicion);

}
