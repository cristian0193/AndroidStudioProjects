package com.example.crodriguez.proyectofinalquestions.vista.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crodriguez.proyectofinalquestions.R;
import com.example.crodriguez.proyectofinalquestions.modelo.FirebaseReferences;
import com.example.crodriguez.proyectofinalquestions.modelo.PersonajeVo;
import com.example.crodriguez.proyectofinalquestions.modelo.Pregunta;
import com.example.crodriguez.proyectofinalquestions.vista.adaptadores.AdaptadorRecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.Buffer;
import java.util.ArrayList;

public class PreguntasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FirebaseAuth auth;
    private FirebaseUser user;
    DatabaseReference referencia;

    private OnFragmentInteractionListener mListener;

    ArrayList<Pregunta> listaPreguntas;
    RecyclerView recyclerPreguntas;

    Activity actividad;
    IPreguntaFragmentView interfaceComunicaFragments;

    public PreguntasFragment() {

    }

    public static PreguntasFragment newInstance() {
        PreguntasFragment fragment = new PreguntasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preguntas, container, false);

        listaPreguntas=new ArrayList<>();
        recyclerPreguntas= (RecyclerView) view.findViewById(R.id.recyclerId);
        recyclerPreguntas.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        AdaptadorRecyclerView adapter=new AdaptadorRecyclerView(listaPreguntas);
        recyclerPreguntas.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Seleccion: "+
                        listaPreguntas.get(recyclerPreguntas.
                                getChildAdapterPosition(view)).getDescripcion_pregunta(),Toast.LENGTH_SHORT).show();

                String stCategoria = "";
                String stPregunta = "";

                stCategoria = listaPreguntas.get(recyclerPreguntas.getChildAdapterPosition(view)).getCategoria();
                stPregunta = listaPreguntas.get(recyclerPreguntas.getChildAdapterPosition(view)).getDescripcion_pregunta();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_sigin_respuesta, null);
                mBuilder.setMessage(stPregunta).setTitle(stCategoria);

                mBuilder.setView(mView);

                final EditText etRespuesta = (EditText) mView.findViewById(R.id.etRespuesta);

                mBuilder.setPositiveButton(R.string.Agregar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String respuestaIngresada = "";

                        respuestaIngresada = etRespuesta.getText().toString();

                        if (!respuestaIngresada.equals("")) {
                            //listPresenter.addTarea(respuestaIngresada, categoriaIngresada);
                            Toast.makeText(getActivity(), R.string.PreguntaRegistrada, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), R.string.CamposVaciosTarea, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mBuilder.create();
                mBuilder.show();

            }
        });

        return view;

    }


    private void llenarListaPersonajes() {

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference(FirebaseReferences.NODO_PADRE);


        String usuario = user.getEmail();
        usuario = usuario.replace(".", "");

        //listaPreguntas.add(new Pregunta("¿Capital de Colombia?", "10/20/2017","Geografia", false, "" ,R.drawable.homero));

        referencia.child(FirebaseReferences.USER_HIJO_NODO_PADRE).child("cristian@hotmailcom").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Pregunta post = dataSnapshot.getValue(Pregunta.class);
                String categoria = post.getFecha();
                String pregunta = post.getDescripcion_pregunta();
                String fecha = post.getCategoria();
                boolean estado = post.isRespuestas();

               listaPreguntas.add(new Pregunta(pregunta, fecha,categoria, estado, "" ,R.drawable.homero));
               
                recyclerPreguntas.getAdapter().notifyDataSetChanged();
                recyclerPreguntas.scrollToPosition(recyclerPreguntas.getAdapter().getItemCount()-1);
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


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
