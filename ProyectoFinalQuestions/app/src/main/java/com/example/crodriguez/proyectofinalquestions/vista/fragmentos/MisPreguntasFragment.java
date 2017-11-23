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
import com.example.crodriguez.proyectofinalquestions.modelo.PersonajeVo;
import com.example.crodriguez.proyectofinalquestions.modelo.Pregunta;
import com.example.crodriguez.proyectofinalquestions.vista.adaptadores.AdaptadorRecyclerView;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class MisPreguntasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Pregunta> listaMisPreguntas;
    RecyclerView recyclerMisPreguntas;

    Activity actividad;
    IMisPreguntaFragmentView interfaceComunicaFragments;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_preguntas, container, false);

        listaMisPreguntas=new ArrayList<>();
        recyclerMisPreguntas = (RecyclerView) view.findViewById(R.id.recyclerIdMisPregunta);
        recyclerMisPreguntas.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        AdaptadorRecyclerView adapter=new AdaptadorRecyclerView(listaMisPreguntas);
        recyclerMisPreguntas.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Seleccion: " +
                        listaMisPreguntas.get(recyclerMisPreguntas.
                                getChildAdapterPosition(view)).getCategoria(), Toast.LENGTH_SHORT).show();

                String stCategoria = "";
                String stPregunta = "";

                stCategoria = listaMisPreguntas.get(recyclerMisPreguntas.getChildAdapterPosition(view)).getCategoria();
                stPregunta = listaMisPreguntas.get(recyclerMisPreguntas.getChildAdapterPosition(view)).getDescripcion_pregunta();


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
                            // listPresenter.addTarea(respuestaIngresada, categoriaIngresada);
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
                listaMisPreguntas.add(new Pregunta("Prueba", "10/20/2017",
                        "Prueba", false, "" ,R.drawable.vegueta_cara));
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
