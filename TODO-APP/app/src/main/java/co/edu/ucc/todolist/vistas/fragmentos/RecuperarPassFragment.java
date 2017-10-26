package co.edu.ucc.todolist.vistas.fragmentos;

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
import co.edu.ucc.todolist.R;
import co.edu.ucc.todolist.vistas.presenters.ILoginPresenter;
import co.edu.ucc.todolist.vistas.presenters.IRecuperarPresenter;
import co.edu.ucc.todolist.vistas.presenters.LoginPresenter;
import co.edu.ucc.todolist.vistas.presenters.RecuperarPresenter;

public class RecuperarPassFragment extends Fragment implements IRecuperarPassFragmentView{

    private OnFragmentInteractionListener mListener;
    private IRecuperarPresenter recuperarPresenter;

    @BindView(R.id.txtCorreoElectronico)
    EditText txtCorreoElectronico;

    @BindView(R.id.btnRecuperar)
    Button btnRecuperar;

    @BindView(R.id.progress)
    ProgressBar progress;

    public RecuperarPassFragment() {

    }

    public static RecuperarPassFragment newInstance() {
        RecuperarPassFragment fragment = new RecuperarPassFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recuperar_pass, container, false);

        ButterKnife.bind(this, view);

        recuperarPresenter = new RecuperarPresenter(this);

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
    public void mostrarConfirmacion(String mensaje) {
        Snackbar.make(getView(), mensaje, Snackbar.LENGTH_LONG).show();
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



    public interface OnFragmentInteractionListener {
        void finalizarRecuperacion();
        void irALogin();
    }
}
