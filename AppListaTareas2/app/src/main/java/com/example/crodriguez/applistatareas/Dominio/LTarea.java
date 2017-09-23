package com.example.crodriguez.applistatareas.Dominio;

import com.example.crodriguez.applistatareas.Modelo.Tarea;

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
