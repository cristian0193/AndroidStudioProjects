package cv.yeison.appmensajeria.vistas.presentadores;

import cv.yeison.appmensajeria.dominio.CallBackInteractor;
import cv.yeison.appmensajeria.dominio.ILUsuario;
import cv.yeison.appmensajeria.dominio.LUsuario;
import cv.yeison.appmensajeria.vistas.fragmentos.IRecuperarPassFragmentView;
import cv.yeison.appmensajeria.vistas.fragmentos.RecuperarFragment;

/**
 * Created by andres on 23/11/2017.
 */

public class RecuperarPresenter implements IRecuperarPresenter{

    private IRecuperarPassFragmentView view;
    private ILUsuario lUsuario;

    public RecuperarPresenter(RecuperarFragment view) {
        this.view = view;
        lUsuario = new LUsuario();
    }
    @Override
    public void recuperarPass(String email) {

        try {
            lUsuario.RecuperarPassUsuario(email, new CallBackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.mostrarConfirmacion(data.toUpperCase());
                    view.finalizarRecuperacion();
                }

                @Override
                public void error(String error) {
                    view.mostrarConfirmacion(error.toUpperCase());
                }
            });
        }catch (Exception e){
            view.mostrarConfirmacion(e.getMessage());
        }

    }
}
