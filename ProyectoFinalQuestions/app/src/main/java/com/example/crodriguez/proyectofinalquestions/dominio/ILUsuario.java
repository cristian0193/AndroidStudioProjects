package com.example.crodriguez.proyectofinalquestions.dominio;

import com.example.crodriguez.proyectofinalquestions.modelo.Usuario;

/**
 * Created by CRODRIGUEZ on 8/11/2017.
 */

public interface ILUsuario {

    void crearUsuario(String password, Usuario usuario, CallBackInteractor<String> callBackInteractor);

    void authUsuario(String email, String password, CallBackInteractor<String> callBackInteractor);

    void RecuperarPassUsuario(String email, CallBackInteractor<String> callBackInteractor);

}
