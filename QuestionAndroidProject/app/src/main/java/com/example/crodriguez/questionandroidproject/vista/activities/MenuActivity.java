package com.example.crodriguez.questionandroidproject.vista.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.crodriguez.questionandroidproject.vista.fragmentos.MisPreguntasFragment;
import com.example.crodriguez.questionandroidproject.R;
import com.example.crodriguez.questionandroidproject.modelo.Pregunta;
import com.example.crodriguez.questionandroidproject.vista.fragmentos.PreguntasFragment;

public class MenuActivity extends FragmentActivity {

    TabHost TbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup();                               //lo activamos

        TabHost.TabSpec tab1 = TbH.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = TbH.newTabSpec("tab2");

        tab1.setIndicator("PREGUNTAS");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.pestana1);//definimos el id de cada Tab (pestaña)

        tab2.setIndicator("MIS PREGUNTAS");
        tab2.setContent(R.id.pestana2);

        TbH.addTab(tab1); //añadimos los tabs ya programados
        TbH.addTab(tab2);
    }
}
