package com.example.crodriguez.proyectofinalandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crodriguez.proyectofinalandroid.R;

/**
 * Created by CRODRIGUEZ on 21/11/2017.
 */

public class ExplorerFragment extends Fragment {
    public ExplorerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explorer, container, false);
    }

}
