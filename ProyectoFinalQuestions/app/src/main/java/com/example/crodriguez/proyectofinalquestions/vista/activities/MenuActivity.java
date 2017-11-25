package com.example.crodriguez.proyectofinalquestions.vista.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crodriguez.proyectofinalquestions.R;
import com.example.crodriguez.proyectofinalquestions.modelo.PersonajeVo;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.IPreguntaFragmentView;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.MisPreguntasFragment;
import com.example.crodriguez.proyectofinalquestions.vista.fragmentos.PreguntasFragment;
import com.example.crodriguez.proyectofinalquestions.vista.presenters.IPreguntaPresenter;
import com.example.crodriguez.proyectofinalquestions.vista.presenters.PreguntaPresenter;
import com.example.crodriguez.proyectofinalquestions.vista.utilidades.Utilidades;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity
        implements PreguntasFragment.OnFragmentInteractionListener,
                    MisPreguntasFragment.OnFragmentInteractionListener,NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawer;
    PreguntasFragment listaFragment;
    IPreguntaPresenter preguntaPresenter;
    MisPreguntasFragment listaFragmentmispreguntas;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        preguntaPresenter = new PreguntaPresenter();

        //Validamos que se trabaja en modo portrait desde un smarthPhone
        if(findViewById(R.id.container)!=null){
            Utilidades.PORTRAIT=true;

            if (savedInstanceState!=null){
                return;
            }
            listaFragment=new PreguntasFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,listaFragment).commit();

            listaFragmentmispreguntas=new MisPreguntasFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,listaFragmentmispreguntas).commit();
        }else{
            Utilidades.PORTRAIT=false;
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         }

    @OnClick(R.id.fab)
    public void AgregarPregunta(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_sigin, null);
                mBuilder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
                mBuilder.setView(mView);

                final EditText etPregunta = (EditText) mView.findViewById(R.id.etPregunta);
                final EditText etCategoria = (EditText) mView.findViewById(R.id.etCategoria);

                mBuilder.setPositiveButton(R.string.Agregar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String preguntaIngresada = "";
                        String categoriaIngresada = "";

                        preguntaIngresada = etPregunta.getText().toString();
                        categoriaIngresada = etCategoria.getText().toString();

                        if (!preguntaIngresada.equals("") && !categoriaIngresada.equals("")) {

                            Calendar calendario = Calendar.getInstance();
                            int dia, mes, ano;

                            dia = calendario.get(Calendar.DATE);
                            mes = calendario.get(Calendar.MONTH);
                            ano = calendario.get(Calendar.YEAR);

                            String formato = dia + "/" + mes + "/" + ano;

                            preguntaPresenter.addPregunta(preguntaIngresada,formato,categoriaIngresada,false,"");
                            preguntaPresenter.addTodasPregunta(preguntaIngresada,formato,categoriaIngresada,false,"");


                            Toast.makeText(getApplicationContext(), R.string.PreguntaRegistrada, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.CamposVaciosTarea, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mBuilder.create();
                mBuilder.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.accion_salir) {
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pregunta) {
            tabLayout.getTabAt(0).select();
            tabLayout.setupWithViewPager(mViewPager);
            drawer.closeDrawers();

        } else if (id == R.id.nav_mis_pregunta) {
            tabLayout.getTabAt(1).select();
            tabLayout.setupWithViewPager(mViewPager);
            drawer.closeDrawers();

        } else if (id == R.id.nav_compartir) {

        } else if (id == R.id.nav_salir) {
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static Fragment newInstance(int sectionNumber) {
            Fragment fragment=null;

            switch (sectionNumber){
                case 1:fragment= new PreguntasFragment();
                    break;
                case 2:fragment= new MisPreguntasFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.main);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PREGUNTAS";
                case 1:
                    return "MIS PREGUNTAS";
            }
            return null;
        }
    }
}