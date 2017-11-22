package com.example.crodriguez.proyectofinalquestions.vista.fragmentos;

/**
 * Created by CRODRIGUEZ on 16/11/2017.
 */

public interface IRecuperarFragmentView {

    void mostrarConfirmacion(String mensaje);
    void recuperar();
    void mostrarProgress();
    void ocultarProgress();
    void irALogin();
    void finalizarRecuperacion();
    void mostrarError(String error);

}
