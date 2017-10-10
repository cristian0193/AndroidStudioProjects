package cv.yeison.apptodolist.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by andres on 01/10/2017.
 */


@Entity(tableName = "tareas")
public class Tarea {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String nombre;

    @ColumnInfo
    private String fecha;

    @ColumnInfo
    private boolean realizada;

    public Tarea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
}

