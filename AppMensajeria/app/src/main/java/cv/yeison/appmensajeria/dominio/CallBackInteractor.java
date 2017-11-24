package cv.yeison.appmensajeria.dominio;

/**
 * Created by andres on 23/11/2017.
 */

public interface CallBackInteractor<T> {

    void success(T data);
    void error(String error);

}
