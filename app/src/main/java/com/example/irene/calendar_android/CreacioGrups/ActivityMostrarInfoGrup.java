package com.example.irene.calendar_android.CreacioGrups;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irene.calendar_android.Companyies.ActivityMostrarInfoEmpresa;
import com.example.irene.calendar_android.CreacioEvent.Creacio_Events;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityMostrarInfoGrup extends AppCompatActivity implements View.OnClickListener{

    public String company_uuid ;
    public String group_id;
    TextView nomGrup, descripcio, creacio, edit;

    Button btnCrearEvents, btnEliminarGrup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_grup);

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

        btnCrearEvents = (Button)findViewById(R.id.btnGrupCrearEvent);
        btnCrearEvents.setOnClickListener(this);

        btnEliminarGrup = (Button)findViewById(R.id.btnGrupEliminar);
        btnEliminarGrup.setOnClickListener(this);

        nomGrup = (TextView)findViewById(R.id.textViewInfoGrupNom);
        descripcio = (TextView)findViewById(R.id.textViewInfoGrupDescripcio);
        creacio = (TextView)findViewById(R.id.textViewInfoGrupCreacio);


        company_uuid = getIntent().getExtras().getString("company_uuid");
        group_id = getIntent().getExtras().getString("group_id");
        carregarDades();
    }


    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

    private void carregarDades(){
        final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
        final Context context = this;
        final AppCompatActivity appCompatActivity = this;

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("company_uuid", company_uuid);
            jsonObject.put("group_id", group_id);

            System.out.println("UUID de la companyia "+company_uuid);
            System.out.println("ID del grup "+group_id);

            apiConnector.GET("Company/Group", jsonObject, new Request() {
                @Override
                public void Response(Object o) {

                    final JSONObject res = (JSONObject) o;
                    try{
                        //final JSONObject object = ((JSONObject) o).getJSONObject("content");
                        System.out.println("==========" + res.toString());

                        appCompatActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    String name = res.getString("name");
                                    String description = res.getString("description");
                                    String creation = res.getString("creation");

                                    nomGrup.setText(name);
                                    descripcio.setText(description);
                                    creacio.setText(creation);

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

            case R.id.btnGrupEliminar:

                final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
                final Context context = this;
                final AppCompatActivity  appCompatActivity = this;

                new AlertDialog.Builder(this)
                        .setTitle("Important")
                        .setMessage("Estàs segur que vols eliminar aquest grup? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    final JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("company_uuid", company_uuid);
                                    jsonObject.put("group_id", group_id);
                                    //   System.out.println("===================>"+uuid);
                                    apiConnector.DELETE("Company/Group", jsonObject, new Request() {
                                        @Override
                                        public void Response(Object o) {
                                            final JSONObject res = (JSONObject) o;
                                            appCompatActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Toast.makeText(context, res.get("message").toString(), Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(ActivityMostrarInfoGrup.this, MainActivity.class);
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
                                //ELiminar grup
                                Toast toast;
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                Toast.makeText(ActivityMostrarInfoGrup.this, "NO ELIMINAR GRUP", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
            case R.id.btnGrupCrearEvent:

                Intent i = new Intent(getApplicationContext(), Creacio_Events.class);
                i.putExtra("company_uuid", company_uuid);
                i.putExtra("group_id", group_id);
                startActivity(i);
                break;



        }
    }


}
