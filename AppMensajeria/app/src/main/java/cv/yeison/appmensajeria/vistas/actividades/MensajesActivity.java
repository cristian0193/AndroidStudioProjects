package cv.yeison.appmensajeria.vistas.actividades;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import cv.yeison.appmensajeria.vistas.fragmentos.PerfilFragment;
import cv.yeison.appmensajeria.R;
import cv.yeison.appmensajeria.vistas.fragmentos.EnviadosFragment;
import cv.yeison.appmensajeria.vistas.fragmentos.RecibidosFragment;
import cv.yeison.appmensajeria.vistas.fragmentos.Utilidades;

public class MensajesActivity extends AppCompatActivity
        implements RecibidosFragment.OnFragmentInteractionListener,
        EnviadosFragment.OnFragmentInteractionListener,
        PerfilFragment.OnFragmentInteractionListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    RecibidosFragment recibidosFragment;
    EnviadosFragment enviadosFragment;
    PerfilFragment perfilFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Validamos que se trabaja en modo portrait desde un smarthPhone
        if(findViewById(R.id.container)!=null){
            Utilidades.PORTRAIT=true;

            if (savedInstanceState!=null){
                return;
            }
            recibidosFragment=new RecibidosFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,recibidosFragment).commit();

            enviadosFragment=new EnviadosFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,enviadosFragment).commit();

            perfilFragment=new PerfilFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,perfilFragment).commit();
        }else{
            Utilidades.PORTRAIT=false;
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.botton_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_add:
                        Toast.makeText(MensajesActivity.this,"Action Add Click",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_edit:
                        Toast.makeText(MensajesActivity.this,"Action Edit Click",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "RECIBIDOS";
                case 1:
                    return "ENVIADOS";
                case 2:
                    return "PERFIL";
            }
            return null;
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static Fragment newInstance(int sectionNumber) {
            Fragment fragment=null;

            switch (sectionNumber){
                case 1:fragment= new RecibidosFragment();
                    break;
                case 2:fragment= new EnviadosFragment();
                    break;
                case 3:fragment= new PerfilFragment();
                    break;
            }

            return fragment;
        }

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
            Intent intent = new Intent(this, MensajesActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
