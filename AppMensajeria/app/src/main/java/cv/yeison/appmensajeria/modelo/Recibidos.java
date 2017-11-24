package cv.yeison.appmensajeria.modelo;

/**
 * Created by andres on 23/11/2017.
 */

public class Recibidos {

    private String fecha;
    private String mensaje;
    private String Receptor;

    public Recibidos(){
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getReceptor() {
        return Receptor;
    }

    public void setReceptor(String receptor) {
        Receptor = receptor;
    }

}
