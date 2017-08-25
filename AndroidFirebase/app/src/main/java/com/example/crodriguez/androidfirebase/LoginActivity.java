package com.example.crodriguez.androidfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @BindView(R.id.btnIngresar)
    Button btnIngresar;

    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // [START initialize_database_ref]
        auth = FirebaseAuth.getInstance();
        // [END initialize_database_ref]
    }

    @OnClick(R.id.btnIngresar)
    public void clickIngresar(){
        String usuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();

        if(usuario.equals("") || password.equals("")){
            Toast.makeText(LoginActivity.this,"Ingrese Usuario y Password ",
                    Toast.LENGTH_SHORT).show();
        }else{
            auth.signInWithEmailAndPassword(usuario,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this,ControlActivity.class);
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


    @OnClick(R.id.btnRegistrar)
    public void clickRegistrar(){

        Intent intent = new Intent(LoginActivity.this,RegistrarActivity.class);
        startActivity(intent);

    }

}
