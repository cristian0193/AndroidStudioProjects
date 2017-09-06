package com.example.crodriguez.androidfirebase;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        ActualizarEstadoBotones();

    }

    @OnClick(R.id.btnSala)
    public void clickSala(View v){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnSala.isChecked()) {

            v.setBackgroundColor(ContextCompat.getColor(this, R.color.azul ));
            hogar.setSala(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.gris ));
            hogar.setSala(0);
            referencia.child(nombre_correo).setValue(hogar);

         }

    }


    @OnClick(R.id.btnBano)
    public void clickBano(View v){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnBano.isChecked()) {
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.azul ));
            hogar.setBano(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.gris ));
            hogar.setBano(0);
            referencia.child(nombre_correo).setValue(hogar);

        }

    }


    @OnClick(R.id.btnCocina)
    public void clickCocina(View v){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnCocina.isChecked()) {
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.azul ));
            hogar.setCocina(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.gris ));
            hogar.setCocina(0);
            referencia.child(nombre_correo).setValue(hogar);

        }

    }


    @OnClick(R.id.btnHabitacion)
    public void clickHabitacion(View v){

        String correo = "";

        correo = getIntent().getStringExtra("StringCorreo");

        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        if(btnHabitacion.isChecked()) {
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.azul ));
            hogar.setHabitacion(1);
            referencia.child(nombre_correo).setValue(hogar);

        }else {
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.gris ));
            hogar.setHabitacion(0);
            referencia.child(nombre_correo).setValue(hogar);

        }

    }


    public void ActualizarEstadoBotones(){

        String correo = "";
        correo = getIntent().getStringExtra("StringCorreo");
        String[] parts = correo.split("@");
        String nombre_correo = parts[0];


        referencia.child(nombre_correo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Hogar post = dataSnapshot.getValue(Hogar.class);

                int sala = post.getSala();
                int bano = post.getBano();
                int cocina = post.getCocina();
                int habitacion = post.getHabitacion();

                if (sala == 0){
                    btnSala.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gris ));
                }else{
                    btnSala.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.azul ));
                }

                if (bano == 0){
                    btnBano.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gris ));
                }else{
                    btnBano.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.azul ));
                }

                if (cocina == 0){
                    btnCocina.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gris ));
                }else{
                    btnCocina.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.azul ));
                }

                if (habitacion == 0){
                    btnHabitacion.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gris ));
                }else{
                    btnHabitacion.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.azul ));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}





