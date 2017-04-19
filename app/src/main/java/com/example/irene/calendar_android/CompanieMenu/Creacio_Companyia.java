package com.example.irene.calendar_android.CompanieMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;

public class Creacio_Companyia extends AppCompatActivity implements View.OnClickListener{

    Button aceptarRegistro, cancelarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacio__companyia);

        aceptarRegistro = (Button) findViewById(R.id.btnAceptarCreacioComp);
        aceptarRegistro.setOnClickListener(this);
        cancelarRegistro = (Button) findViewById(R.id.btnCancelaCreacioComp);
        cancelarRegistro.setOnClickListener(this);




    }

    public void onClick(View v){
        int id = v.getId();

        switch (id){

            case R.id.btnAceptarCreacioComp:

                Intent intent = new Intent(Creacio_Companyia.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.btnCancelaCreacioComp:


                new AlertDialog.Builder(this)
                        .setTitle("CANCEL·lAR")
                        .setMessage("Estàs segur que vols cancel·lar l'operació ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Toast.makeText(Creacio_Companyia.this, "Creació cancel·lada",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                                Toast.makeText(Creacio_Companyia.this, "Acceptat",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                break;



        }


    }



}