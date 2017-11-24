package cv.yeison.appmensajeria.vistas.fragmentos;

/**
 * Created by andres on 23/11/2017.
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
