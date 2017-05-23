package com.example.irene.calendar_android.Usuaris;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.irene.calendar_android.CreacioGrups.AdaptadorGrups;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;

public class ActivityLlistatUserEvents extends ListActivity implements View.OnClickListener{

    private AdaptadorGrups cAdapter;
    FloatingActionButton btnHome;
    public String company_uuid;
    Button btnAgregarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);
        //Boton atras de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        btnAgregarUser = (Button)findViewById(R.id.btnLlistAfegirUsuaris);
        btnAgregarUser.setOnClickListener(this);

        carregarLlistat();
    }


    private void carregarLlistat(){

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.floatingActionButtonMain:

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;

            case R.id.btnLlistAfegirUsuaris:

                Intent intent = new Intent(getApplicationContext(), ActivityAfegirUsuariCompanyia.class);
                startActivity(intent);
                break;
        }
    }
}
