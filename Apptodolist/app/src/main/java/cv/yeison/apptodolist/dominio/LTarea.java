package cv.yeison.apptodolist.dominio;

import java.util.ArrayList;
import java.util.List;

import cv.yeison.apptodolist.modelo.Tarea;
import cv.yeison.apptodolist.repository.AppDB;

/**
 * Created by andres on 01/10/2017.
 */

public class LTarea implements ILtarea{

    private AppDB database;

    public LTarea(){
        database = AppDB.getInstacia();
    }

    @Override
    public void addTarea(Tarea tarea) {
        database.getTareaDAO().insert(tarea);
    }

    @Override
    public List<Tarea> getLstTarea() {
        return database.getTareaDAO().obtenerTodos();
    }

    @Override
    public void actualizar(Tarea... tareas) {
        database.getTareaDAO().update(tareas);
    }

    @Override
    public Tarea obtenerxID(int id) {
        return database.getTareaDAO().obtenerxID(id);
    }
}
