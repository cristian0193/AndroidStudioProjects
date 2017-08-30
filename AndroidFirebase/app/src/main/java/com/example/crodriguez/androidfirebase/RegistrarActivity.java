package com.example.crodriguez.androidfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import models.FirebaseReferences;
import models.Usuario;

public class RegistrarActivity extends AppCompatActivity{

    @BindView(R.id.txtNombre)
    EditText txtNombre;

    @BindView(R.id.txtUsuario)
    EditText txtUsuario;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    @BindView(R.id.btnUsuarioExistente)
    Button btnUsuarioExistente;

    private FirebaseAuth auth;
    DatabaseReference referencia;

    public String nombre = "";
    public String usuario = "";
    public String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        ButterKnife.bind(this);

        // [START initialize_database_ref]
        auth = FirebaseAuth.getInstance();
        // [END initialize_database_ref]
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference(FirebaseReferences.TUTORIAL_REFERENCIA);
    }

    @OnClick(R.id.btnRegistrar)
    public void clickRegistrar(){

        nombre = txtNombre.getText().toString();
        usuario = txtUsuario.getText().toString();
        password = txtPassword.getText().toString();

        btnRegistrar.setEnabled(false);

        if(nombre.equals("") || usuario.equals("") || password.equals("")){
            Toast.makeText(RegistrarActivity.this, "Ingrese Nombre, Usuario y Contraseña",
                    Toast.LENGTH_SHORT).show();
        }else{
            auth.createUserWithEmailAndPassword(usuario,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                               final String uid = task.getResult().getUser().getUid();

                                task.getResult().getUser().getToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<GetTokenResult> task) {

                                        Usuario ObjUsuario = new Usuario();

                                        ObjUsuario.setNombre(nombre);
                                        ObjUsuario.setToken(task.getResult().getToken());

                                        referencia.child(FirebaseReferences.USER_REFERENCIA).push().setValue(ObjUsuario);

                                        Toast.makeText(RegistrarActivity.this, R.string.registroUsuario,
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegistrarActivity.this,LoginActivity.class);
                                        startActivity(intent);

                                        finish();

                                    }
                                });

                            }else{
                                auth.signInWithEmailAndPassword(usuario,password)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {

                                                if(task.isSuccessful()){

                                                    btnUsuarioExistente.setEnabled(true);
                                                    Toast.makeText(RegistrarActivity.this, R.string.usuarioExistente,
                                                            Toast.LENGTH_SHORT).show();
                                                    btnRegistrar.setEnabled(false);
                                                    return;
                                                }
                                            }
                                        });
                            }
                        }
                    });
        }
    }

    @OnClick(R.id.btnUsuarioExistente)
    public void clickUsuarioExistente(){

        btnUsuarioExistente.setEnabled(false);

        Intent intent = new Intent(RegistrarActivity.this,LoginActivity.class);
        startActivity(intent);

        finish();

    }


}
