package cv.yeison.appmensajeria.dominio;

import cv.yeison.appmensajeria.modelo.Usuario;

/**
 * Created by andres on 23/11/2017.
 */

public interface ILUsuario {

    void crearUsuario(String password, Usuario usuario,
                      CallBackInteractor<String> callBackInteractor);

    void authUsuario(String email, String password,
                     CallBackInteractor<String> callBackInteractor);

    void RecuperarPassUsuario(String email,
                              CallBackInteractor<String> callBackInteractor);
}
