package com.example.crodriguez.androidfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import models.FirebaseReferences;
import models.Hogar;

public class BotonesActivity extends AppCompatActivity {

    @BindView(R.id.btnSala)
    ToggleButton btnSala;

    @BindView(R.id.btnBano)
    ToggleButton btnBano;

    @BindView(R.id.btnCocina)
    ToggleButton btnCocina;

    @BindView(R.id.btnHabitacion)
    ToggleButton btnHabitacion;

    DatabaseReference referencia;
    Hogar hogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);
        ButterKnife.bind(this);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference(FirebaseReferences.HOGAR_REFERENCIA);
        hogar = new Hogar();
    }

    @OnClick(R.id.btnSala)
    public void clickSala(){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnSala.isChecked()) {

            hogar.setSala(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {

            hogar.setSala(0);
            referencia.child(nombre_correo).setValue(hogar);

         }

    }


    @OnClick(R.id.btnBano)
    public void clickBano(){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnBano.isChecked()) {

            hogar.setBano(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {

            hogar.setBano(0);
            referencia.child(nombre_correo).setValue(hogar);

        }

    }


    @OnClick(R.id.btnCocina)
    public void clickCocina(){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnCocina.isChecked()) {

            hogar.setCocina(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {

            hogar.setCocina(0);
            referencia.child(nombre_correo).setValue(hogar);

        }

    }


    @OnClick(R.id.btnHabitacion)
    public void clickHabitacion(){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnHabitacion.isChecked()) {

            hogar.setHabitacion(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {

            hogar.setHabitacion(0);
            referencia.child(nombre_correo).setValue(hogar);

        }

    }
}
