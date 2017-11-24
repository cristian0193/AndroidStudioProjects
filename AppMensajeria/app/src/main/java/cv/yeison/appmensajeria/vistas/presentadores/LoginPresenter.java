package cv.yeison.appmensajeria.vistas.presentadores;
import cv.yeison.appmensajeria.dominio.CallBackInteractor;
import cv.yeison.appmensajeria.dominio.ILUsuario;
import cv.yeison.appmensajeria.dominio.LUsuario;
import cv.yeison.appmensajeria.vistas.fragmentos.ILoginFragmentView;

/**
 * Created by andres on 23/11/2017.
 */

public class LoginPresenter implements ILoginPresenter{

    private ILoginFragmentView view;
    private ILUsuario lUsuario;

    public LoginPresenter(ILoginFragmentView view) {
        this.view = view;
        lUsuario = new LUsuario();
    }

    @Override
    public void login(String email, String password) {
        view.deshabilitarVistas();
        view.mostrarProgress();

        try {
            lUsuario.authUsuario(email, password, new CallBackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.habilitarVistas();
                    view.ocultarProgress();
                    view.finalizarLogin();
                }

                @Override
                public void error(String error) {
                    view.habilitarVistas();
                    view.ocultarProgress();
                    view.mostrarError(error);
                }
            });
        } catch (Exception e) {
            view.habilitarVistas();
            view.ocultarProgress();
            view.mostrarError(e.getMessage());
        }
    }
}
