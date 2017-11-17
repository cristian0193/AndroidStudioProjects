package com.example.crodriguez.questionandroidproject.vista.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.crodriguez.questionandroidproject.R;
import com.example.crodriguez.questionandroidproject.vista.presenters.IRecuperarPresenter;
import com.example.crodriguez.questionandroidproject.vista.presenters.RecuperarPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecuperarFragment extends Fragment implements IRecuperarFragmentView {

    @BindView(R.id.txtCorreoElectronico)
    EditText txtCorreoElectronico;

    @BindView(R.id.btnRecuperar)
    Button btnRecuperar;

    @BindView(R.id.progress)
    ProgressBar progress;

    private IRecuperarPresenter recuperarPresenter;
    private OnRecuperarFragmentInteractionListener mListener;

    public RecuperarFragment() {

    }

    public static RecuperarFragment newInstance() {
        RecuperarFragment fragment = new RecuperarFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recuperar, container, false);

        ButterKnife.bind(this, view);

        recuperarPresenter = new RecuperarPresenter(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRecuperarFragmentInteractionListener) {
            mListener = (OnRecuperarFragmentInteractionListener) context;
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
    public void mostrarConfirmacion(String mensaje) {
        Snackbar.make(getView(), mensaje, Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnRecuperar)
    public void recuperar() {

        final String email = txtCorreoElectronico.getText().toString();
        recuperarPresenter.recuperarPass(email);

    }

    @Override
    public void mostrarProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void irALogin() {
        if (mListener != null) {
            mListener.irALogin();
        }
    }

    @Override
    public void finalizarRecuperacion() {
        if (mListener != null) {
            mListener.finalizarRecuperacion();
        }
    }

    @Override
    public void mostrarError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_LONG).show();
    }


    public interface OnRecuperarFragmentInteractionListener {
        void finalizarRecuperacion();
        void irALogin();
    }
}
