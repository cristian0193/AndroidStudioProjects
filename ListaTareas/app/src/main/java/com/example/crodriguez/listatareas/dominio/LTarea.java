package com.example.crodriguez.listatareas.dominio;

import com.example.crodriguez.listatareas.modelos.Tarea;
import java.util.ArrayList;
import java.util.List;

public class LTarea {

    private static List<Tarea> lstTarea = new ArrayList<>();

    public void adTarea (Tarea tarea){
        lstTarea.add(tarea);
    }

    public List<Tarea> getLstTarea(){
        return lstTarea;
    }
}
