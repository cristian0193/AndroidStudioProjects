package com.example.crodriguez.proyectofinalquestions.vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.crodriguez.proyectofinalquestions.R;
import com.example.crodriguez.proyectofinalquestions.modelo.FirebaseReferences;
import com.example.crodriguez.proyectofinalquestions.modelo.Pregunta;
import com.example.crodriguez.proyectofinalquestions.vista.adaptadores.AdaptadorRecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MisPreguntasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private FirebaseAuth auth;
    private FirebaseUser user;
    DatabaseReference referencia;

    ArrayList<Pregunta> listaMisPreguntas;
    RecyclerView recyclerMisPreguntas;

    public MisPreguntasFragment() {
    }


    public static MisPreguntasFragment newInstance() {
        MisPreguntasFragment fragment = new MisPreguntasFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_preguntas, container, false);

        listaMisPreguntas=new ArrayList<>();
        recyclerMisPreguntas= (RecyclerView) view.findViewById(R.id.recyclerIdMisPregunta);
        recyclerMisPreguntas.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        AdaptadorRecyclerView adapter=new AdaptadorRecyclerView(listaMisPreguntas);
        recyclerMisPreguntas.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stCategoria = "";
                String stPregunta = "";
                String stFecha = "";

                stCategoria = listaMisPreguntas.get(recyclerMisPreguntas.getChildAdapterPosition(view)).getCategoria();
                stPregunta = listaMisPreguntas.get(recyclerMisPreguntas.getChildAdapterPosition(view)).getDescripcion_pregunta();
                stFecha = listaMisPreguntas.get(recyclerMisPreguntas.getChildAdapterPosition(view)).getFecha();

                    //VISUALIZAR UNA PREGUNTA RESUELTA

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                    View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_sigin_respuesta_resuelta, null);
                    mBuilder.setMessage(stPregunta).setTitle(stCategoria);

                    TextView txtFecha = (TextView)mView.findViewById(R.id.lbFecha);
                    txtFecha.setText(stFecha);

                    mBuilder.setView(mView);
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
                referencia = mDatabase.getReference(FirebaseReferences.BASE_DATOS);

                String usuario = user.getEmail();
                usuario = usuario.replace(".", "");

                referencia.child(FirebaseReferences.PREGUNTA).child(usuario).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Pregunta post = dataSnapshot.getValue(Pregunta.class);
                        String categoria = post.getCategoria();
                        String pregunta = post.getDescripcion_pregunta();
                        String fecha = post.getFecha();
                        boolean estado = post.isRespuestas();
                        String respuesta = post.getDescripcion_respuestas();
                        String key = post.getKEY();

                        listaMisPreguntas.add(new Pregunta(pregunta, fecha,categoria, estado, respuesta ,R.drawable.burns,key));

                        recyclerMisPreguntas.getAdapter().notifyDataSetChanged();
                        recyclerMisPreguntas.scrollToPosition(recyclerMisPreguntas.getAdapter().getItemCount() - 1);

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
