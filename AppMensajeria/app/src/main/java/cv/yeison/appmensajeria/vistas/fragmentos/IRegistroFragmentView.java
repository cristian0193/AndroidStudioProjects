package cv.yeison.appmensajeria.vistas.fragmentos;

/**
 * Created by andres on 23/11/2017.
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
