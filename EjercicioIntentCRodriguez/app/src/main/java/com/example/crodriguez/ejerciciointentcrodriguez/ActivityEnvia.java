package com.example.crodriguez.ejerciciointentcrodriguez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityEnvia extends AppCompatActivity {

    @BindView(R.id.txtNombre)
    EditText txtNombre;

    @BindView(R.id.txtApellido)
    EditText txtApellido;

    @BindView(R.id.txtDireccion)
    EditText txtDireccion;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envia);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnRegistrar)
    public void clickEnviar(){

        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String direccion = txtDireccion.getText().toString();
        String email = txtEmail.getText().toString();

        Intent intent = new Intent(ActivityEnvia.this,ActivityRecibir.class);
        intent.putExtra("StringNombre",nombre);
        intent.putExtra("StringApellido",apellido);
        intent.putExtra("StringDireccion",direccion);
        intent.putExtra("StringEmail",email);

        startActivity(intent);
    }
}
