package com.example.crodriguez.applistatareas.Vista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crodriguez.applistatareas.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;;

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

    @BindView(R.id.login_button)
    LoginButton login_button;

    private FirebaseAuth auth;
    private String usuario;
    private String password;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
                                Intent intent = new Intent(LoginActivity.this,ListaTareasActivity.class);
                                intent.putExtra("StringCorreo",usuario);
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

    @OnClick(R.id.login_button)
    public void loginFacebook(){
        callbackManager = CallbackManager.Factory.create();
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                 pantallaPrincipal();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.cancel_login, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_login, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void pantallaPrincipal() {
        Intent intent = new Intent(this, ListaTareasActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

}
