package com.example.crodriguez.proyectofinalquestions.vista.presenters;

import com.example.crodriguez.proyectofinalquestions.dominio.CallBackInteractor;
import com.example.crodriguez.proyectofinalquestions.dominio.ILUsuario;
import com.example.crodriguez.proyectofinalquestions.dominio.LUsuario;
import com.example.crodriguez.proyectofinalquestions.modelo.Usuario;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.IRegistroFragmentView;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.RegistroFragment;

public class RegistroPresenter implements IRegistroPresenter {

    private IRegistroFragmentView view;
    private ILUsuario lUsuario;

    public RegistroPresenter(RegistroFragment view) {
        this.view = view;
        lUsuario = new LUsuario();
    }

    @Override
    public void registrar(String nombres, String email, String password) {

        view.deshabilitarControles();
        view.mostrarProgress();

        try {
            Usuario usuario = new Usuario();
            usuario.setCorreo(email);
            usuario.setNombre(nombres);

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
