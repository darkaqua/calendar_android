package com.example.irene.calendar_android.CreacioEvent;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.Usuaris.ActivityLlistatUserEvents;
import com.example.irene.calendar_android.Usuaris.ActivityLlistatUserGrups;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityMostrarInfoEvents extends AppCompatActivity implements View.OnClickListener {

    TextView nomEvent, descripcio, data, duracio;
    String company_uuid, group_id, date_id;
    FloatingActionButton btnHome;

    Button btnEliminarEvent, btnEventsLlistatUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_events);

        nomEvent = (TextView)findViewById(R.id.textViewInfoEventNom);
        descripcio = (TextView)findViewById(R.id.textViewInfoEventDescripcio);
        data = (TextView)findViewById(R.id.textViewInfoDataEvent);
        duracio = (TextView)findViewById(R.id.textViewInfoDuracioEvent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEliminarEvent = (Button)findViewById(R.id.btnEliminarEvent);
        btnEliminarEvent.setOnClickListener(this);

        btnEventsLlistatUsers = (Button)findViewById(R.id.btnEventsLlistatUsers);
        btnEventsLlistatUsers.setOnClickListener(this);

        company_uuid = getIntent().getExtras().getString("company_uuid");
        group_id = getIntent().getExtras().getString("group_id");
        date_id = getIntent().getExtras().getString("date_id");

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
            jsonObject.put("date_id", date_id);

            apiConnector.GET("Company/Group/Date", jsonObject, new Request() {
                @Override
                public void Response(Object o) {
                    try{
                        final JSONObject object = (JSONObject) o;
                        System.out.println(object.toString());

                        appCompatActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{

                                    String title = object.getString("title");
                                    String description = object.getString("description");
                                    String datetime = object.getString("datetime");
                                    String longMinutes = object.getString("long_minutes");

                                    nomEvent.setText(title);
                                    descripcio.setText(description);
                                    data.setText(datetime);
                                    duracio.setText(longMinutes);

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });

                    } catch (Exception e){
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

        switch (id){
            case R.id.btnEliminarEvent:
                final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
                final Context context = this;
                final AppCompatActivity  appCompatActivity = this;

                new AlertDialog.Builder(this)
                        .setTitle("Important")
                        .setMessage("Estàs segur que vols eliminar aquest event? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    final JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("company_uuid", company_uuid);
                                    jsonObject.put("group_id", group_id);
                                    jsonObject.put("date_id", date_id);
                                    //   System.out.println("===================>"+uuid);
                                    apiConnector.DELETE("Company/Group/Date", jsonObject, new Request() {
                                        @Override
                                        public void Response(Object o) {
                                            final JSONObject res = (JSONObject) o;
                                            appCompatActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Toast.makeText(context, res.get("message").toString(), Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(ActivityMostrarInfoEvents.this, ActivityLlistatEvents.class);
                                                        i.putExtra("company_uuid", company_uuid);
                                                        i.putExtra("group_id", group_id);
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
                                Toast.makeText(ActivityMostrarInfoEvents.this, "NO ELIMINAR EVENT", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
            case R.id.btnEventsLlistatUsers:
                Intent intent2 = new Intent(getApplicationContext(), ActivityLlistatUserEvents.class);
                intent2.putExtra("company_uuid", company_uuid);
                intent2.putExtra("group_id", group_id);
                intent2.putExtra("date_id", date_id);

                System.out.println("btnEventsLlistaUsers------------>" + date_id);
                startActivity(intent2);
                break;

        }

    }

}
