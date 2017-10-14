package com.example.ycuartas.listaandroiducc.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crodriguez.listaandroiducc.R;
import com.example.ycuartas.listaandroiducc.modelos.Usuario;
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

public class RegistroActivity extends AppCompatActivity {

    @BindView(R.id.txtNomUsuario)
    EditText txtNombre;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    @BindView(R.id.lbUsuarioExistente)
    TextView lbUsuarioExistente;

    private FirebaseAuth auth;
    DatabaseReference referencia;

    public String nombre = "";
    public String email = "";
    public String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);

        // [START initialize_database_ref]
        auth = FirebaseAuth.getInstance();
        // [END initialize_database_ref]
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        referencia = mDatabase.getReference("DATOS");

    }

    @OnClick(R.id.btnRegistrar)
    public void clickRegistrar(){

        nombre = txtNombre.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();

        btnRegistrar.setEnabled(false);

        if(nombre.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(RegistroActivity.this, "Ingrese Nombre, Usuario y Contraseña",
                    Toast.LENGTH_SHORT).show();
        }else{
            auth.createUserWithEmailAndPassword(email,password)
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

                                        //referencia.child(FirebaseReferences.USER_HIJO_NODO_PADRE).push().setValue(ObjUsuario);

                                        Toast.makeText(RegistroActivity.this, R.string.registroUsuario,
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
                                        startActivity(intent);

                                        finish();

                                    }
                                });

                            }else{
                                auth.signInWithEmailAndPassword(email,password)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {

                                                if(task.isSuccessful()){

                                                    lbUsuarioExistente.setVisibility(View.VISIBLE);
                                                    Toast.makeText(RegistroActivity.this, R.string.usuarioExistente,
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

    @OnClick(R.id.lbUsuarioExistente)
    public void clickUsuarioExistente(){

        lbUsuarioExistente.setVisibility(View.VISIBLE);

        Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
        startActivity(intent);

        finish();

    }



}