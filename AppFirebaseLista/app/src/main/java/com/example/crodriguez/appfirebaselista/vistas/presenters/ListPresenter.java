package com.example.crodriguez.appfirebaselista.vistas.presenters;

import com.example.crodriguez.appfirebaselista.dominio.ILtarea;
import com.example.crodriguez.appfirebaselista.dominio.LTarea;
import com.example.crodriguez.appfirebaselista.modelos.*;
import com.example.crodriguez.appfirebaselista.vistas.IListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListPresenter implements IListPresenter{

    private IListView view;
    private ILtarea ltarea;
    private FirebaseAuth auth;
    DatabaseReference referencia;

    public ListPresenter(IListView view){
        this.view = view;
        ltarea = new LTarea();

        auth = FirebaseAuth.getInstance();

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference(FirebaseReferences.NODO_PADRE);
    }

    @Override
    public void addTarea(String descTarea,String fecha) {
        Tarea objTarea = new Tarea();
        objTarea.setNombre(descTarea);
        objTarea.setFecha(fecha);
        objTarea.setRealizada(false);

        String correo = "cristian010193";

        referencia.child(FirebaseReferences.USER_HIJO_NODO_PADRE).child(correo).child(FirebaseReferences.TAREA_NODO_USER_HIJO).push().setValue(objTarea);
        ltarea.addTarea(objTarea);

        ltarea.addTarea(objTarea);
        view.refrescarListaTareas();
    }

    @Override
    public List<Tarea> obtenerTareas() {
        return ltarea.getLstTarea();
    }

    @Override
    public void itemCambioEstado(int posicion, boolean estado) {
        ltarea.getLstTarea().get(posicion).setRealizada(estado);
        view.refrescarTarea(posicion);
    }
}
