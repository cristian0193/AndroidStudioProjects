package com.example.crodriguez.proyectofinalandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crodriguez.proyectofinalandroid.Tab.MyAdapter;
import com.example.crodriguez.proyectofinalandroid.Tab.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private SlidingTabLayout mslidingLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),this));

        mslidingLayout = (SlidingTabLayout)findViewById(R.id.stl_tabs);
        mslidingLayout.setDistributeEvenly(true);
        mslidingLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mslidingLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mslidingLayout.setCustomTabView(R.layout.tab_view,R.id.tv_tab);
        mslidingLayout.setViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
