package com.example.ycuartas.listaandroiducc.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crodriguez.listaandroiducc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtNomUsuario)
    EditText txtUsuario;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.lbRegistrar)
    TextView lbRegistrar;

    @BindView(R.id.btnIngresar)
    Button btnIngresar;

    private FirebaseAuth auth;
    private String usuario;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.btnIngresar)
    public void clickIngresar(){
        usuario = txtUsuario.getText().toString();
        password = txtPassword.getText().toString();

        if(usuario.equals("") || password.equals("")){
            Toast.makeText(LoginActivity.this,"Ingrese Usuario y Password ",
                    Toast.LENGTH_SHORT).show();
        }else{
            auth.signInWithEmailAndPassword(usuario,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this,ListaActivity.class);
                                intent.putExtra("StringUser",usuario);
                                startActivity(intent);
                                finish();
                                return;
                            }
                            Toast.makeText(LoginActivity.this,"Error : El usuario no existe. ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @OnClick(R.id.lbRegistrar)
    public void clickRegistrar(){
        Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
        startActivity(intent);
    }

}

