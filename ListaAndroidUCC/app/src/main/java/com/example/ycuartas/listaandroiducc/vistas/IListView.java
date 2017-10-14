package com.example.ycuartas.listaandroiducc.vistas;

public interface IListView {

    void clickAddTareaModal();

    void refrescarListaTareas();

    void refrescarTarea(int posicion);

    void refrescarTareaEliminada(int posicion);

    int getContadorItem();

}
