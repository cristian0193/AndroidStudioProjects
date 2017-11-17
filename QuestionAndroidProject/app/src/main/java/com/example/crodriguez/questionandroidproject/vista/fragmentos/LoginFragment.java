package com.example.crodriguez.questionandroidproject.vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.crodriguez.questionandroidproject.R;
import com.example.crodriguez.questionandroidproject.vista.presenters.ILoginPresenter;
import com.example.crodriguez.questionandroidproject.vista.presenters.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends Fragment implements ILoginFragmentView{

    @BindView(R.id.txtEmailLogin)
    EditText txtEmail;

    @BindView(R.id.txtPasswordLogin)
    EditText txtContrasena;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.btnIngresarLogin)
    Button btnIngresarLogin;

    @BindView(R.id.btnCrearCuenta)
    Button btnCrearCuenta;

    @BindView(R.id.txtRecordarContrasena)
    TextView txtRecordarContrasena;

    private ILoginPresenter loginPresenter;
    private OnLoginFragmentInteraction mListener;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        loginPresenter = new LoginPresenter(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteraction) {
            mListener = (OnLoginFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void habilitarVistas() {
        txtContrasena.setEnabled(true);
        txtEmail.setEnabled(true);
        btnIngresarLogin.setEnabled(true);
        btnCrearCuenta.setEnabled(true);
    }

    @Override
    public void deshabilitarVistas() {
        txtContrasena.setEnabled(false);
        txtEmail.setEnabled(false);
        btnIngresarLogin.setEnabled(false);
        btnCrearCuenta.setEnabled(false);
    }

    @Override
    public void mostrarProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgress() {
        progress.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnIngresarLogin)
    @Override
    public void login() {
        final String email = txtEmail.getText().toString();
        String contrasena = txtContrasena.getText().toString();

        loginPresenter.login(email, contrasena);
    }

    @OnClick(R.id.btnCrearCuenta)
    @Override
    public void irARegistro() {
        if (mListener != null) {
            mListener.irARegistro();
        }
    }

    @OnClick(R.id.txtRecordarContrasena)
    @Override
    public void irARecuperar() {
        if (mListener != null) {
            mListener.irARecuperar();
        }
    }

    @Override
    public void finalizarLogin() {
        if (mListener != null) {
            mListener.finalizarLogin();
        }
    }

    @Override
    public void mostrarError(String mensaje) {
        Snackbar.make(getView(), mensaje, Snackbar.LENGTH_LONG).show();
    }


    public interface OnLoginFragmentInteraction {
        void finalizarLogin();
        void irARegistro();
        void irARecuperar();
    }
}
