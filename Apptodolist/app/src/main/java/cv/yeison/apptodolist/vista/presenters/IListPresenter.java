package cv.yeison.apptodolist.vista.presenters;

import java.util.List;

import cv.yeison.apptodolist.modelo.Tarea;

/**
 * Created by andres on 01/10/2017.
 */

public interface IListPresenter {

    void addTarea(String nombreTarea, String Tarea);

    List<Tarea> obtenerTareas();

    void itemCambioEstado(int posicion, boolean estado);
}
