package cv.yeison.apptodolist.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cv.yeison.apptodolist.modelo.Tarea;

/**
 * Created by andres on 03/10/2017.
 */

@Dao
public interface TareaDAO {

    @Insert
    void insert(Tarea... tareas);

    @Update
    void update(Tarea... tareas);

    @Delete
    void delete(Tarea tarea);

    @Query("select * from tareas")
    List<Tarea> obtenerTodos();

    @Query("select * from tareas where id = :id")
    Tarea obtenerxID(int id);
}
