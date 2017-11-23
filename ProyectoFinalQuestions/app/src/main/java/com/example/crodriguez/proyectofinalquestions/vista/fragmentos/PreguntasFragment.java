package com.example.crodriguez.proyectofinalquestions.vista.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

                String stCategoria = "";
                String stPregunta = "";
                Boolean stRespuesta;

                stCategoria = listaPreguntas.get(recyclerPreguntas.getChildAdapterPosition(view)).getCategoria();
                stPregunta = listaPreguntas.get(recyclerPreguntas.getChildAdapterPosition(view)).getDescripcion_pregunta();
                stRespuesta = listaPreguntas.get(recyclerPreguntas.getChildAdapterPosition(view)).isRespuestas();

                if(stRespuesta){
                    //VISUALIZAR UNA PREGUNTA RESUELTA

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                    View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_sigin_respuesta_resuelta, null);
                    mBuilder.setMessage(stPregunta).setTitle(stCategoria);

                    mBuilder.setView(mView);

                    mBuilder.setPositiveButton(R.string.compartir, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, "El mejor blog de android http://javaheros.blogspot.pe/");
                            startActivity(Intent.createChooser(intent, "Share with"));

                        }
                    });

                    mBuilder.create();
                    mBuilder.show();
                }else{
                    //REGISTRAR NUEVA RESPUESTA
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

                                Toast.makeText(getActivity(), R.string.PreguntaRegistrada, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), R.string.CamposVaciosTarea, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    mBuilder.create();
                    mBuilder.show();
                }







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

        referencia.child(FirebaseReferences.USER_HIJO_NODO_PADRE).child("cristian@hotmailcom").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Pregunta post = dataSnapshot.getValue(Pregunta.class);
                String categoria = post.getCategoria();
                String pregunta = post.getDescripcion_pregunta();
                String fecha = post.getFecha();
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
