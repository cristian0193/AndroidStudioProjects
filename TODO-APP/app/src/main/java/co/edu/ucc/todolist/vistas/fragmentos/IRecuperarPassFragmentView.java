package co.edu.ucc.todolist.vistas.fragmentos;

/**
 * Created by CRODRIGUEZ on 26/10/2017.
 */

public interface IRecuperarPassFragmentView {

    void mostrarConfirmacion(String mensaje);

    void recuperar();
    void mostrarProgress();
    void ocultarProgress();
    void irALogin();
    void finalizarRecuperacion();
    void mostrarError(String error);
}
