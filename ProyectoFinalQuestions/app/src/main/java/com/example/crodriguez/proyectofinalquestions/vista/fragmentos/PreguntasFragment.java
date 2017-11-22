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
import com.example.crodriguez.proyectofinalquestions.vista.activities.MenuActivity;
import com.example.crodriguez.proyectofinalquestions.vista.adaptadores.AdaptadorRecyclerView;

import java.nio.Buffer;
import java.util.ArrayList;

import butterknife.ButterKnife;

public class PreguntasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<PersonajeVo> listaPersonaje;
    RecyclerView recyclerPersonajes;

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

        listaPersonaje=new ArrayList<>();
        recyclerPersonajes= (RecyclerView) view.findViewById(R.id.recyclerId);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        AdaptadorRecyclerView adapter=new AdaptadorRecyclerView(listaPersonaje);
        recyclerPersonajes.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Seleccion: "+
                        listaPersonaje.get(recyclerPersonajes.
                                getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_sigin, null);
                mBuilder.setMessage(listaPersonaje.get(recyclerPersonajes.
                        getChildAdapterPosition(view)).getNombre()).setTitle(R.string.dialog_title);

                String stTarea = listaPersonaje.get(recyclerPersonajes.getChildAdapterPosition(view)).getNombre();

                mBuilder.setView(mView);

                final EditText etTarea = (EditText) mView.findViewById(R.id.etTarea);
                final EditText etCategoria = (EditText) mView.findViewById(R.id.etCategoria);


                mBuilder.setPositiveButton(R.string.Agregar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String tareaIngresada = "";
                        String categoriaIngresada = "";

                        tareaIngresada = etTarea.getText().toString();
                        categoriaIngresada = etCategoria.getText().toString();

                        if (!tareaIngresada.equals("") && !categoriaIngresada.equals("")) {
                            // listPresenter.addTarea(tareaIngresada, categoriaIngresada);
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
        listaPersonaje.add(new PersonajeVo(getString(R.string.goku_nombre), getString(R.string.goku_descripcion_corta),
                getString(R.string.goku_descripcion_Larga), R.drawable.goku_cara,R.drawable.goku_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.gohan_nombre), getString(R.string.gohan_descripcion_corta),
                getString(R.string.gohan_descripcion_Larga), R.drawable.gohan_cara,R.drawable.gohan_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.goten_nombre), getString(R.string.goten_descripcion_corta),
                getString(R.string.goten_descripcion_Larga), R.drawable.goten_cara,R.drawable.goten_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.krilin_nombre), getString(R.string.krilin_descripcion_corta),
                getString(R.string.krilin_descripcion_Larga), R.drawable.krilin_cara,R.drawable.krilin_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.picoro_nombre), getString(R.string.picoro_descripcion_corta),
                getString(R.string.picoro_descripcion_Larga), R.drawable.picoro_cara,R.drawable.picoro_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.trunks_nombre), getString(R.string.trunks_descripcion_corta),
                getString(R.string.trunks_descripcion_Larga), R.drawable.trunks_cara,R.drawable.trunks_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.vegueta_nombre), getString(R.string.vegueta_descripcion_corta),
                getString(R.string.vegueta_descripcion_Larga), R.drawable.vegueta_cara,R.drawable.vegueta_detalle));
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
