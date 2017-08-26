package com.example.crodriguez.androidfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import models.Coche;
import models.FirebaseReferences;

public class ControlActivity extends AppCompatActivity {

    @BindView(R.id.btnGuardar)
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

    @OnClick(R.id.btnGuardar)
    public void guardar(){

        Coche coche = new Coche("Ford", "Toni", 4, 3);
        referencia.child(FirebaseReferences.COCHE_REFERENCIA).push().setValue(coche);

    }



}
