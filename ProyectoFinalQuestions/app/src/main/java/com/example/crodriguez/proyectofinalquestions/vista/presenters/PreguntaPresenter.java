package com.example.crodriguez.proyectofinalquestions.vista.presenters;

import android.widget.Toast;

import com.example.crodriguez.proyectofinalquestions.modelo.FirebaseReferences;
import com.example.crodriguez.proyectofinalquestions.modelo.Pregunta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class PreguntaPresenter implements IPreguntaPresenter{

    private FirebaseAuth auth;
    private FirebaseUser user;
    DatabaseReference referencia;

    public PreguntaPresenter() {
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference(FirebaseReferences.BASE_DATOS);
    }

    @Override
    public void addPregunta( String pregunta, String fecha, String categoria, boolean respuesta, String desc_respuesta) {

        String KEY = referencia.child(FirebaseReferences.TODAS_PREGUNTAS).push().getKey();
        Pregunta objPregunta = new Pregunta();

        objPregunta.setDescripcion_pregunta(pregunta);
        objPregunta.setFecha(fecha);
        objPregunta.setCategoria(categoria);
        objPregunta.setRespuestas(respuesta);
        objPregunta.setDescripcion_respuestas(desc_respuesta);
        objPregunta.setKEY(KEY);

        String usuario = user.getEmail();
        usuario = usuario.replace(".", "");

        referencia.child(FirebaseReferences.PREGUNTA).child(usuario).push().setValue(objPregunta);

    }

    @Override
    public void addTodasPregunta(String pregunta, String fecha, String categoria, boolean respuesta, String desc_respuesta) {

        Pregunta objPregunta = new Pregunta();

        objPregunta.setDescripcion_pregunta(pregunta);
        objPregunta.setFecha(fecha);
        objPregunta.setCategoria(categoria);
        objPregunta.setRespuestas(respuesta);
        objPregunta.setDescripcion_respuestas(desc_respuesta);

        String KEY = referencia.child(FirebaseReferences.TODAS_PREGUNTAS).push().getKey();

        objPregunta.setKEY(KEY);

        String usuario = user.getEmail();
        usuario = usuario.replace(".", "");

        referencia.child(FirebaseReferences.TODAS_PREGUNTAS).push().setValue(objPregunta);
    }

    @Override
    public List<Pregunta> obtenerPregunta() {
        return null;
    }

    @Override
    public void itemCambioEstado(int posicion, boolean estado) {
    }

    @Override
    public void obtenerPreguntaFirebase() {
    }
}
