package com.example.irene.calendar_android.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.irene.calendar_android.Altres.ActivityContacte;
import com.example.irene.calendar_android.Altres.ActivityInformacio;
import com.example.irene.calendar_android.Companyies.ActivityLlistatEmpreses;
import com.example.irene.calendar_android.ConfiguracioUsuaris.ActivityConfiguracioUsuari;
import com.example.irene.calendar_android.CreacioEvent.Creacio_Events;


import com.example.irene.calendar_android.CreacioGrups.ActivityLlistatGrups;
import com.example.irene.calendar_android.CreacioGrups.Creacio_Grups;
import com.example.irene.calendar_android.Perfil.ActivityPerfil;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.Tarifes.ActivityTarifes;

public  class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Calendari", "Empreses"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Tabs Calendari i Empreses
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 2; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        MyAdapterFragment pagerAdapter = new MyAdapterFragment(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);



        return  true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*Fragment fragment = null;
        Class fragmentClass;
         */

        //Creem una flag que ens servirà per saber si estem en un Fragment o no
        boolean FragmentTransaction = false;
        Fragment fragment = null;


         /*   fragment  = new FragmentPerfil();
            FragmentTransaction = true;*/

        // Intent i = new Intent(MainActivity.this, EditorGrupos.class);

         //   startActivity(i);

        if(id == R.id.nav_events){
            Intent i = new Intent(MainActivity.this, Creacio_Events.class);
            startActivity(i);
        }else if(id == R.id.nav_empreses){
            Intent i = new Intent(MainActivity.this, ActivityLlistatEmpreses.class);
            startActivity(i);
        }else if(id == R.id.nav_grups){
            Intent i = new Intent(MainActivity.this, ActivityLlistatGrups.class);
            startActivity(i);
        }else if(id == R.id.nav_configuracio){
            Intent i = new Intent(MainActivity.this, ActivityConfiguracioUsuari.class);
            startActivity(i);
        }else if (id == R.id.nav_informacio){
            Intent i = new Intent(MainActivity.this, ActivityInformacio.class);
            startActivity(i);
        }else if (id == R.id.nav_contacte){
            Intent i = new Intent(MainActivity.this, ActivityContacte.class);
            startActivity(i);
        }else if (id == R.id.nav_perfil){
            Intent i = new Intent(MainActivity.this, ActivityPerfil.class);
            startActivity(i);
        }else if (id == R.id.nav_tarifes){
            Intent i = new Intent(MainActivity.this, ActivityTarifes.class);
            startActivity(i);
        }
        else if (id == R.id.nav_sortir_sessio) {
            AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
            dialog1.setTitle("Important");

            dialog1.setMessage("Segur que vols tancar la sessió?");
            dialog1.setCancelable(false);

            dialog1.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    aceptar();
                }
            });

            dialog1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    cancelar();
                }
            });

             dialog1.show();
        }

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();


            //Aquestes dues lineas faran que el titol del lloc tingui el mateix nom que el de la nav bar
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void aceptar(){
        finish();
    }

    public void cancelar(){

    }

    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}