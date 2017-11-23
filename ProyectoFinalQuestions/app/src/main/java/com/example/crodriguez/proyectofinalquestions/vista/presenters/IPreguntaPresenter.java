package com.example.crodriguez.proyectofinalquestions.vista.presenters;

import com.example.crodriguez.proyectofinalquestions.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CRODRIGUEZ on 22/11/2017.
 */

public interface IPreguntaPresenter {

    void addPregunta( String pregunta, String fecha, String Categoria, boolean respuesta, String desc_respuesta);

    void addTodasPregunta( String pregunta, String fecha, String Categoria, boolean respuesta, String desc_respuesta);

    List<Pregunta> obtenerPregunta();

    void itemCambioEstado(int posicion, boolean estado);

    void obtenerPreguntaFirebase();

}
