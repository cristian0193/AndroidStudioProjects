package com.example.crodriguez.ejerciciointentcrodriguez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRecibir extends AppCompatActivity {

    @BindView(R.id.txtNombre)
    EditText txtNombre;

    @BindView(R.id.txtApellido)
    EditText txtApellido;

    @BindView(R.id.txtDireccion)
    EditText txtDireccion;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibir);
        ButterKnife.bind(this);

        String nombre = getIntent().getStringExtra("StringNombre");
        String apellido = getIntent().getStringExtra("StringApellido");
        String direccion = getIntent().getStringExtra("StringDireccion");
        String email = getIntent().getStringExtra("StringEmail");

        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtDireccion.setText(direccion);
        txtEmail.setText(email);

        Toast.makeText(ActivityRecibir.this,"Datos Recibidos con Exito",
                Toast.LENGTH_SHORT).show();

    }
}
