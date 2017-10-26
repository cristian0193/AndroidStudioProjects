package com.example.ycuartas.listaandroiducc.dominio;

import com.example.ycuartas.listaandroiducc.modelos.Tarea;

import java.util.ArrayList;
import java.util.List;

public class LTarea implements ILtarea {


    public static List<Tarea> lstTarea = new ArrayList<>();

    @Override
    public void addTarea (Tarea tarea){
        lstTarea.add(tarea);
    }

    @Override
    public void removeTarea(Tarea tarea) {
        lstTarea.remove(tarea);
    }

    @Override
    public List<Tarea> getLstTarea(){
        return lstTarea;
    }
}
