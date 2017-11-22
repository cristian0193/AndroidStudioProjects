package com.example.crodriguez.recyclerviewdragonball;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.crodriguez.recyclerviewdragonball.entidades.PersonajeVo;
import com.example.crodriguez.recyclerviewdragonball.fragments.DetallePersonajeFragment;
import com.example.crodriguez.recyclerviewdragonball.fragments.ListaPersonajesFragment;
import com.example.crodriguez.recyclerviewdragonball.interfaces.IComunicaFragments;
import com.example.crodriguez.recyclerviewdragonball.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity
        implements ListaPersonajesFragment.OnFragmentInteractionListener,
            DetallePersonajeFragment.OnFragmentInteractionListener, IComunicaFragments{

    ListaPersonajesFragment listaFragment;
    DetallePersonajeFragment detalleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Validamos que se trabaja en modo portrait desde un smarthPhone
        if(findViewById(R.id.contenedorFragment)!=null){
            Utilidades.PORTRAIT=true;
            if (savedInstanceState!=null){
                return;
            }
            listaFragment=new ListaPersonajesFragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.contenedorFragment,listaFragment).commit();
        }else{
            Utilidades.PORTRAIT=false;
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void enviarPersonaje(PersonajeVo personaje) {

        detalleFragment= (DetallePersonajeFragment) this.getSupportFragmentManager().
                                                        findFragmentById(R.id.fragDetalle);

        if(detalleFragment!=null && findViewById(R.id.contenedorFragment)==null){
            detalleFragment.asignarInformacion(personaje);
        }else{
            detalleFragment=new DetallePersonajeFragment();
            Bundle bundleEnvio=new Bundle();
            bundleEnvio.putSerializable("objeto",personaje);
            detalleFragment.setArguments(bundleEnvio);

            //cargamos el fragment en el Activity
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.contenedorFragment,detalleFragment).
                    addToBackStack(null).commit();
        }

    }
}

























