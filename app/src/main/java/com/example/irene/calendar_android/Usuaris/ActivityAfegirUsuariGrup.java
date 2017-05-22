package com.example.irene.calendar_android.Usuaris;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;

public class ActivityAfegirUsuariGrup extends AppCompatActivity implements View.OnClickListener{


    FloatingActionButton btnHome;
    Button btnAfegir, btnEiminar;
    public String company_uuid, user_uuid;
    public boolean can_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_usuari_companyia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Boton atras de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

//        company_uuid = getIntent().getExtras().getString("company_uuid");
//        user_uuid = getIntent().getExtras().getString("user_uuid");



    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.floatingActionButtonMain:

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
        }

    }
}
