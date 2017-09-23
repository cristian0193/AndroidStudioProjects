package com.example.crodriguez.listatareas.dominio;

import com.example.crodriguez.listatareas.modelos.Tarea;
import java.util.ArrayList;
import java.util.List;

public class LTarea implements ILtarea {

    public static List<Tarea> lstTarea = new ArrayList<>();

    @Override
    public void addTarea (Tarea tarea){
        lstTarea.add(tarea);
    }

    @Override
    public List<Tarea> getLstTarea(){
        return lstTarea;
    }
}
