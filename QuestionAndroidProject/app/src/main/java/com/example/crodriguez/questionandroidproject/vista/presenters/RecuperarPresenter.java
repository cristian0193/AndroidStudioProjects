package com.example.crodriguez.questionandroidproject.vista.presenters;

import com.example.crodriguez.questionandroidproject.dominio.CallBackInteractor;
import com.example.crodriguez.questionandroidproject.dominio.ILUsuario;
import com.example.crodriguez.questionandroidproject.dominio.LUsuario;
import com.example.crodriguez.questionandroidproject.vista.fragmentos.IRecuperarFragmentView;
import com.example.crodriguez.questionandroidproject.vista.fragmentos.RecuperarFragment;

/**
 * Created by CRODRIGUEZ on 17/11/2017.
 */

public class RecuperarPresenter implements IRecuperarPresenter{

    private IRecuperarFragmentView view;
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
