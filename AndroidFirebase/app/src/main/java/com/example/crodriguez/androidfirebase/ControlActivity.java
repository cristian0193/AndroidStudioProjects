package com.example.crodriguez.androidfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import models.Coche;
import models.FirebaseReferences;

public class ControlActivity extends AppCompatActivity {


    @BindView(R.id.txtMarca)
    EditText txtMarca;

    @BindView(R.id.txtDueno)
    EditText txtDueno;

    @BindView(R.id.txtPuertas)
    EditText txtPuertas;

    @BindView(R.id.txtRuedas)
    EditText txtRuedas;

    @BindView(R.id.btnRegistrarCoche)
    Button btnGuardar;

    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        ButterKnife.bind(this);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference(FirebaseReferences.TUTORIAL_REFERENCIA);

    }

    @OnClick(R.id.btnRegistrarCoche)
    public void guardar(){

        String marca = "";
        String dueno = "";
        String puerta = "";
        String rueda = "";

        int Num_puerta = 0;
        int Num_rueda = 0;

        dueno = txtDueno.getText().toString();
        marca = txtMarca.getText().toString();
        puerta = txtPuertas.getText().toString();
        rueda = txtRuedas.getText().toString();

        Num_puerta = Integer.parseInt(txtPuertas.getText().toString());
        Num_rueda = Integer.parseInt(txtRuedas.getText().toString());

        if(marca.equals("") || dueno.equals("") || puerta.equals("") || rueda.equals("")){
            Toast.makeText(ControlActivity.this, R.string.ErrorRegistroCoche,
                    Toast.LENGTH_SHORT).show();
        }else{
            Coche coche = new Coche(marca,dueno, Num_puerta,Num_rueda);
            referencia.child(FirebaseReferences.COCHE_REFERENCIA).push().setValue(coche);
            Toast.makeText(ControlActivity.this, R.string.RegistroCoche,
                    Toast.LENGTH_SHORT).show();
            Limpiar();
        }

    }

    public void Limpiar(){
        txtDueno.setText("");
        txtMarca.setText("");
        txtPuertas.setText("");
        txtRuedas.setText("");
    }

}
