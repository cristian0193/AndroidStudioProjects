package com.example.crodriguez.questionandroidproject.dominio;

import java.util.List;
import com.example.crodriguez.questionandroidproject.modelo.Pregunta;

/**
 * Created by CRODRIGUEZ on 8/11/2017.
 */

public interface ILpregunta {

    void addPregunta(Pregunta pregunta);

    List<Pregunta> getPregunta();

    void actualizar(Pregunta... tareas);

    Pregunta obtenerXID(int id);

}
