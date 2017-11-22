package com.example.crodriguez.proyectofinalquestions.vista.presenters;

import com.example.crodriguez.proyectofinalquestions.dominio.CallBackInteractor;
import com.example.crodriguez.proyectofinalquestions.dominio.ILUsuario;
import com.example.crodriguez.proyectofinalquestions.dominio.LUsuario;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.ILoginFragmentView;

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
