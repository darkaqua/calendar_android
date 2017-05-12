package com.example.irene.calendar_android.Companyies;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irene.calendar_android.CreacioEvent.Creacio_Events;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;

public class ActivityMostrarInfoEmpresa extends AppCompatActivity implements View.OnClickListener{

    TextView nomEmpresa, descripcio, email, telefon, adreça, codiPostal, membres, pais;
    Button btnEliminar, btnModificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_empresa);

        nomEmpresa = (TextView)findViewById(R.id.textViewInfoEmpresaNom);
        descripcio = (TextView)findViewById(R.id.textViewInfoEmpresaDescripcio);
        email = (TextView)findViewById(R.id.textViewInfoEmpresaEmail);
        telefon = (TextView)findViewById(R.id.textViewInfoEmpresaTelefon);
        adreça = (TextView)findViewById(R.id.textViewInfoEmpresaDireccio);
        codiPostal = (TextView)findViewById(R.id.textViewInfoEmpresaCodiPostal);
        membres = (TextView)findViewById(R.id.textViewInfoEmpresaMembres);
        pais = (TextView)findViewById(R.id.textViewInfoEmpresaPais);

        btnEliminar = (Button)findViewById(R.id.btnEliminarEmpresa);
        btnEliminar.setOnClickListener(this);

        btnModificar = (Button)findViewById(R.id.btnModificarEmpresa);
        btnModificar.setOnClickListener(this);

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
    }



    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.btnEliminarEmpresa:

                new AlertDialog.Builder(this)
                        .setTitle("Important")
                        .setMessage("Estàs segur que vols eliminar aquesta empresa? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //ELiminar empresa
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
            case R.id.btnModificarEmpresa:
                Intent i = new Intent(ActivityMostrarInfoEmpresa.this, ActivityModificarEmpresa.class);
                startActivity(i);
                break;
        }
    }

}
