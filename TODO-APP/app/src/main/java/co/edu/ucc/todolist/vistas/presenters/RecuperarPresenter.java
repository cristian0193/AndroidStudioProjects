package co.edu.ucc.todolist.vistas.presenters;

import co.edu.ucc.todolist.dominio.CallBackInteractor;
import co.edu.ucc.todolist.dominio.ILUsuario;
import co.edu.ucc.todolist.dominio.LUsuario;
import co.edu.ucc.todolist.vistas.fragmentos.ILoginFragmentView;
import co.edu.ucc.todolist.vistas.fragmentos.IRecuperarPassFragmentView;
import co.edu.ucc.todolist.vistas.fragmentos.RecuperarPassFragment;

/**
 * Created by CRODRIGUEZ on 25/10/2017.
 */

public class RecuperarPresenter implements IRecuperarPresenter {

    private IRecuperarPassFragmentView view;
    private ILUsuario lUsuario;

    public RecuperarPresenter(RecuperarPassFragment view) {
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
