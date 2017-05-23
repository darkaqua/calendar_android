package com.example.irene.calendar_android.Companyies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irene.calendar_android.CreacioEvent.ActivityMostrarInfoEvents;
import com.example.irene.calendar_android.CreacioEvent.Creacio_Events;
import com.example.irene.calendar_android.CreacioGrups.ActivityLlistatGrups;
import com.example.irene.calendar_android.CreacioGrups.ActivityMostrarInfoGrup;
import com.example.irene.calendar_android.CreacioGrups.Creacio_Grups;
import com.example.irene.calendar_android.CreacioGrups.ListActivityGrups;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.Login.ActivityRegistre;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.Usuaris.ActivityLlistatUsersCompanyies;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityMostrarInfoEmpresa extends AppCompatActivity implements View.OnClickListener{

    TextView nomEmpresa, descripcio, email, telefon, adreça, codiPostal, membres, pais, dataRegistre;
    Button btnEliminar, btnCrearGrup, btnLlistatUsers, btnListCompanyGrups;
    FloatingActionButton btnHome;

    private String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_empresa);

        dataRegistre = (TextView)findViewById(R.id.textViewInfoEmpresaDataRegistre);
        nomEmpresa = (TextView)findViewById(R.id.textViewInfoEmpresaNom);
        descripcio = (TextView)findViewById(R.id.textViewInfoEmpresaDescripcio);
        email = (TextView)findViewById(R.id.textViewInfoEmpresaEmail);
        telefon = (TextView)findViewById(R.id.textViewInfoEmpresaTelefon);
        adreça = (TextView)findViewById(R.id.textViewInfoEmpresaDireccio);
        codiPostal = (TextView)findViewById(R.id.textViewInfoEmpresaCodiPostal);
     //   membres = (TextView)findViewById(R.id.textViewInfoEmpresaMembres);
        pais = (TextView)findViewById(R.id.textViewInfoEmpresaPais);

        btnEliminar = (Button)findViewById(R.id.btnEliminarEmpresa);
        btnEliminar.setOnClickListener(this);

        btnLlistatUsers = (Button)findViewById(R.id.btnCompanyLlistatUsers);
        btnLlistatUsers.setOnClickListener(this);

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        btnCrearGrup = (Button)findViewById(R.id.btnCreacioGrup);
        btnCrearGrup.setOnClickListener(this);

        btnListCompanyGrups = (Button)findViewById(R.id.btnListCompanyGrups);
        btnListCompanyGrups.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Boton atras de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));


        uuid = getIntent().getExtras().getString("uuid");

        carregarDades();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityMostrarInfoEmpresa.this, ActivityLlistatEmpreses.class);
                startActivity(i);
            }
        });
    }

    private void carregarDades(){
        final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
        final Context context = this;
        final AppCompatActivity appCompatActivity = this;

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("company_uuid", uuid);

            apiConnector.GET("Company", jsonObject, new Request() {

                @Override
                public void Response(Object o) {
                    try{
                        final JSONObject object = ((JSONObject) o).getJSONObject("content");
                      //  System.out.println("==========>>>>>>><<<><<<<<<>>><<<<<" + object.toString());

                        appCompatActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{

                                    String name = object.getString("name");
                                    String description = object.getString("description");
                                    String mail = object.getString("email");
                                    String telephone = object.getString("telephone");
                                    String address = object.getString("address");
                                    String postalCode = object.getString("postal_code");
                             //       int members = object.getInt("members_count");
                                    String register = object.getString("register");

                                    nomEmpresa.setText(name);
                                    descripcio.setText(description);
                                    email.setText(mail);
                                    telefon.setText(telephone);
                                    adreça.setText(address);
                                    codiPostal.setText(postalCode);
                                    //membres.setText(members);
                                    pais.setText("ES");
                                    dataRegistre.setText(register);

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


            }catch (Exception e){
                e.printStackTrace();
            }
    }



    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.btnEliminarEmpresa:
                final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
                final Context context = this;
                final AppCompatActivity  appCompatActivity = this;

                new AlertDialog.Builder(this)
                        .setTitle("Important")
                        .setMessage("Estàs segur que vols eliminar aquesta empresa? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    final JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("company_uuid", uuid);
                                 //   System.out.println("===================>"+uuid);
                                    apiConnector.DELETE("Company", jsonObject, new Request() {
                                        @Override
                                        public void Response(Object o) {
                                            final JSONObject res = (JSONObject) o;
                                            appCompatActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Toast.makeText(context, res.get("message").toString(), Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(ActivityMostrarInfoEmpresa.this, MainActivity.class);
                                                        startActivity(i);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                //ELiminar empresa
                                Toast toast;
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                Toast.makeText(ActivityMostrarInfoEmpresa.this, "NO ELIMINAR EMPRESA", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
            case R.id.btnCreacioGrup:


                Intent i = new Intent(getApplicationContext(), Creacio_Grups.class);
                i.putExtra("company_uuid", uuid);
                startActivity(i);
                break;

            case R.id.floatingActionButtonMain:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnCompanyLlistatUsers:

                Intent intent1 = new Intent(getApplicationContext(), ActivityLlistatUsersCompanyies.class);
                intent1.putExtra("company_uuid", uuid);
                startActivity(intent1);
                break;

            case  R.id.btnListCompanyGrups:
                Intent i3 = new Intent(getApplicationContext(), ActivityLlistatGrups.class);
                i3.putExtra("company_uuid", uuid);
                startActivity(i3);
                break;
        }
    }

}
