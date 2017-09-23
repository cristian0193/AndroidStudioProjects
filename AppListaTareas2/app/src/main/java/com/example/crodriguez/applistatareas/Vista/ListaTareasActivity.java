package com.example.crodriguez.applistatareas.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crodriguez.applistatareas.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListaTareasActivity extends AppCompatActivity {

    @BindView(R.id.btnCerrarSesion)
    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);
        ButterKnife.bind(this);

        if(AccessToken.getCurrentAccessToken() == null){
            pantallaLogin();
        }
    }

    private void pantallaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @OnClick(R.id.btnCerrarSesion)
    public void CierreFacebook(View view){
        LoginManager.getInstance().logOut();
        finish();
        pantallaLogin();
    }
}
