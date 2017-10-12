package cv.yeison.apptodolist.dominio;

import java.util.List;

import cv.yeison.apptodolist.modelo.Tarea;

/**
 * Created by andres on 01/10/2017.
 */

public interface ILtarea {

    void addTarea(Tarea tarea);

    List<Tarea> getLstTarea();

    void actualizar(Tarea... tareas);

    Tarea obtenerxID(int id);
}
