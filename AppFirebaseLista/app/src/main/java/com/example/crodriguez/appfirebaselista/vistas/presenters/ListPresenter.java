package com.example.crodriguez.appfirebaselista.vistas.presenters;

import com.example.crodriguez.appfirebaselista.dominio.ILtarea;
import com.example.crodriguez.appfirebaselista.dominio.LTarea;
import com.example.crodriguez.appfirebaselista.modelos.*;
import com.example.crodriguez.appfirebaselista.vistas.IListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListPresenter implements IListPresenter{

    private IListView view;
    private ILtarea ltarea;
    private FirebaseAuth auth;
    private FirebaseUser user;
    DatabaseReference referencia;

    public ListPresenter(IListView view){
        this.view = view;
        ltarea = new LTarea();

        auth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference(FirebaseReferences.NODO_PADRE);
    }

    @Override
    public void addTarea(String descTarea,String fecha) {
        Tarea objTarea = new Tarea();

        objTarea.setNombre(descTarea);
        objTarea.setFecha(fecha);
        objTarea.setRealizada(false);

        String usuario = user.getEmail();
        usuario = usuario.replace(".", "");

        //ENVIO A FIREBASE
        referencia.child(FirebaseReferences.USER_HIJO_NODO_PADRE).child(usuario).child(FirebaseReferences.TAREA_NODO_USER_HIJO).push().setValue(objTarea);

        //ltarea.addTarea(objTarea);
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

    @Override
    public void obtenerTareasFirebase() {

        String usuario = user.getEmail();
        usuario = usuario.replace(".", "");

        referencia.child(FirebaseReferences.USER_HIJO_NODO_PADRE).child(usuario).child(FirebaseReferences.TAREA_NODO_USER_HIJO).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Tarea post = dataSnapshot.getValue(Tarea.class);

                String nombre = post.getNombre();
                String fecha = post.getFecha();
                boolean realizada = post.isRealizada();

                Tarea objTarea = new Tarea();

                objTarea.setNombre(nombre);
                objTarea.setFecha(fecha);
                objTarea.setRealizada(realizada);

                ltarea.addTarea(objTarea);
                view.refrescarListaTareas();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                         }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
