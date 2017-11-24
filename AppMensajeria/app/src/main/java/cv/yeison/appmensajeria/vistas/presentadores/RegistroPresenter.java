package cv.yeison.appmensajeria.vistas.presentadores;

import cv.yeison.appmensajeria.dominio.CallBackInteractor;
import cv.yeison.appmensajeria.dominio.ILUsuario;
import cv.yeison.appmensajeria.dominio.LUsuario;
import cv.yeison.appmensajeria.modelo.Usuario;
import cv.yeison.appmensajeria.vistas.fragmentos.IRegistroFragmentView;

/**
 * Created by andres on 23/11/2017.
 */

public class RegistroPresenter implements IRegistroPresenter {

    private IRegistroFragmentView view;
    private ILUsuario lUsuario;

    public RegistroPresenter(IRegistroFragmentView view) {
        this.view = view;
        lUsuario = new LUsuario();
    }

    @Override
    public void registrar(String nombres, String email, String password, String edad, String telefono) {
        view.deshabilitarControles();
        view.mostrarProgress();

        try {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNombres(nombres);
            usuario.setEdad(edad);
            usuario.setTelefono(telefono);

            lUsuario.crearUsuario(password, usuario, new CallBackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.habilitarControles();
                    view.ocultarProgress();
                    view.finalizarRegistro();
                }

                @Override
                public void error(String error) {
                    view.habilitarControles();
                    view.ocultarProgress();
                    view.mostrarError(error);
                }
            });


        } catch (Exception e) {
            view.habilitarControles();
            view.ocultarProgress();
            view.mostrarError(e.getMessage());
        }
    }
}
