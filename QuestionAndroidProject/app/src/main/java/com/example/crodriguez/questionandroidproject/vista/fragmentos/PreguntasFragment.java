package com.example.crodriguez.questionandroidproject.vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crodriguez.questionandroidproject.R;

import java.nio.Buffer;

import butterknife.ButterKnife;


public class PreguntasFragment extends Fragment {

    private OnPreguntasFragmentInteractionListener mListener;

    public PreguntasFragment() {
    }

    public static PreguntasFragment newInstance(String param1, String param2) {
        PreguntasFragment fragment = new PreguntasFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preguntas, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPreguntasFragmentInteractionListener) {
            mListener = (OnPreguntasFragmentInteractionListener) context;
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

    public interface OnPreguntasFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
