package com.example.crodriguez.proyectofinalquestions.vista.fragmentos;

/**
 * Created by CRODRIGUEZ on 16/11/2017.
 */

public interface IRegistroFragmentView {

    void habilitarControles();
    void deshabilitarControles();
    void mostrarProgress();
    void ocultarProgress();
    void registrar();
    void irALogin();
    void finalizarRegistro();
    void mostrarError(String error);

}
