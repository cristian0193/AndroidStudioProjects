package cv.yeison.appmensajeria.vistas.fragmentos;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cv.yeison.appmensajeria.R;
import cv.yeison.appmensajeria.vistas.presentadores.IRegistroPresenter;
import cv.yeison.appmensajeria.vistas.presentadores.RegistroPresenter;


public class RegistroFragment extends Fragment implements IRegistroFragmentView {

    @BindView(R.id.txtEmailLogin)
    EditText txtEmailLogin;

    @BindView(R.id.txtNombres)
    EditText txtNombres;

    @BindView(R.id.txtEdad)
    EditText txtEdad;

    @BindView(R.id.txtTelefono)
    EditText txtTelefono;

    @BindView(R.id.txtPasswordLogin)
    EditText txtPasswordLogin;

    @BindView(R.id.btnCrearCuenta)
    Button btnCrearCuenta;

    @BindView(R.id.progress)
    ProgressBar progress;


    private IRegistroPresenter registroPresenter;
    private OnFragmentInteractionListener mListener;

    public RegistroFragment() {
        // Required empty public constructor
    }


    public static RegistroFragment newInstance() {
        RegistroFragment fragment = new RegistroFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        ButterKnife.bind(this, view);

        registroPresenter = new RegistroPresenter(this);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public void habilitarControles() {

        txtNombres.setEnabled(true);
        txtEmailLogin.setEnabled(true);
        txtPasswordLogin.setEnabled(true);
        btnCrearCuenta.setEnabled(true);
    }

    @Override
    public void deshabilitarControles() {
        txtNombres.setEnabled(false);
        txtEmailLogin.setEnabled(false);
        txtPasswordLogin.setEnabled(false);
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

    @OnClick(R.id.btnCrearCuenta)
    @Override
    public void registrar() {

        String nombres = txtNombres.getText().toString();
        String email = txtEmailLogin.getText().toString();
        String password = txtPasswordLogin.getText().toString();
        String edad = txtEdad.getText().toString();
        String telefono = txtTelefono.getText().toString();

        registroPresenter.registrar(nombres, email, password, edad, telefono);

    }

    @Override
    public void irALogin() {
        if (mListener != null) {
            mListener.irALogin();
        }
    }

    @Override
    public void finalizarRegistro() {
        if (mListener != null) {
            mListener.finalizarRegistro();
        }
    }

    @Override
    public void mostrarError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }


    public interface OnFragmentInteractionListener {
        void irALogin();
        void finalizarRegistro();
    }
}
