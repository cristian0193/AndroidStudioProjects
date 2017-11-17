package com.example.crodriguez.questionandroidproject.dominio;

/**
 * Created by CRODRIGUEZ on 8/11/2017.
 */

public interface CallBackInteractor<T> {

    void success(T data);

    void error(String error);

}
