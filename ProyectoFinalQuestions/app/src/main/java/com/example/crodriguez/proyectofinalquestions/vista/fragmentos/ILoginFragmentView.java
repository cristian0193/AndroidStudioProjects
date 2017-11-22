package com.example.crodriguez.proyectofinalquestions.vista.fragmentos;

public interface ILoginFragmentView {

    void habilitarVistas();
    void deshabilitarVistas();
    void mostrarProgress();
    void ocultarProgress();
    void login();
    void irARegistro();
    void irARecuperar();
    void finalizarLogin();
    void mostrarError(String mensaje);

}
