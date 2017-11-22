package com.example.crodriguez.proyectofinalquestions.vista.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.crodriguez.proyectofinalquestions.R;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.LoginFragment;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.RecuperarFragment;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.RegistroFragment;

public class PrincipalActivity extends AppCompatActivity
        implements LoginFragment.OnLoginFragmentInteraction,RegistroFragment.OnRegistroInteractionListener,RecuperarFragment.OnRecuperarFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        initFragment();
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, LoginFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void irALogin() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, LoginFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void finalizarLogin() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void irARegistro() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, RegistroFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void finalizarRegistro() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, LoginFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void irARecuperar() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, RecuperarFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void finalizarRecuperacion() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameAuthActivity, LoginFragment.newInstance());
        transaction.commit();
    }
}
