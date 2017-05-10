package com.example.irene.calendar_android.CompanieMenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.CreacioEvent.Creacio_Events;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.Login.ActivityRegistre;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class Creacio_Companyia extends AppCompatActivity implements View.OnClickListener{

    Button aceptarRegistro, cancelarRegistro;
    EditText nomEmpresa, descripcio, email, telefon, adreça, ciutat, postal, pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacio__companyia);

        aceptarRegistro = (Button) findViewById(R.id.btnAceptarCreacioComp);
        aceptarRegistro.setOnClickListener(this);
        cancelarRegistro = (Button) findViewById(R.id.btnCancelaCreacioComp);
        cancelarRegistro.setOnClickListener(this);

        nomEmpresa = (EditText)findViewById(R.id.etNomCompanyia);
        descripcio = (EditText)findViewById(R.id.etDescripcioCompanyia);
        email = (EditText)findViewById(R.id.etEmailCompanyia);
        telefon = (EditText)findViewById(R.id.etTelefonCompanyia);
        adreça = (EditText)findViewById(R.id.etAdreçaCompanyia);
        ciutat = (EditText)findViewById(R.id.etCiutatCompanyia);
        postal = (EditText)findViewById(R.id.etCodiPostalCompanyia);
        pais = (EditText)findViewById(R.id.etPaisCompanyia);


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

    public void onClick(View v){
        int id = v.getId();

        switch (id){

            case R.id.btnAceptarCreacioComp:

               //Intent intent = new Intent(Creacio_Companyia.this, MainActivity.class);
               // startActivity(intent);
             //   Toast.makeText(Creacio_Companyia.this, "Empresa creada", Toast.LENGTH_SHORT).show();

                try {
                    final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;

                    final AppCompatActivity appCompatActivity = this;
                    final Context context = getApplicationContext();

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", nomEmpresa.getText().toString());
                    jsonObject.put("description", descripcio.getText().toString());
                    jsonObject.put("email", email.getText().toString());
                    jsonObject.put("telephone", telefon.getText().toString());
                    jsonObject.put("address", adreça.getText().toString());
                    jsonObject.put("city", ciutat.getText().toString());
                    jsonObject.put("postal_code", postal.getText().toString());
                    jsonObject.put("country", "ES");


                    apiConnector.PUT("Company", jsonObject, new Request() {
                        @Override
                        public void Response(Object o) {
                            final JSONObject res = (JSONObject) o;
                            try{
                                if(res.getBoolean("valid")){
                                    Intent i = new Intent(Creacio_Companyia.this, MainActivity.class);
                                    startActivity(i);
                                    Toast.makeText(Creacio_Companyia.this, "Empresa creada", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                appCompatActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Toast.makeText(context, res.get("message").toString(), Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;

            case R.id.btnCancelaCreacioComp:


                new AlertDialog.Builder(this)
                        .setTitle("Important")
                        .setMessage("Estàs segur que vols cancel·lar l'operació ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Intent i = new Intent(Creacio_Companyia.this, MainActivity.class);
                                startActivity(i);
                                Toast.makeText(Creacio_Companyia.this, "Creació cancel·lada",
                                        Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                                //Toast.makeText(Creacio_Companyia.this, "Acceptat",
                                 //       Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                break;



        }


    }



}