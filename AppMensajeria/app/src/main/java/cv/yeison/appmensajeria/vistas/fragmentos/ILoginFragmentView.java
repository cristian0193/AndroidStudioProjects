package cv.yeison.appmensajeria.vistas.fragmentos;

/**
 * Created by andres on 23/11/2017.
 */

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
