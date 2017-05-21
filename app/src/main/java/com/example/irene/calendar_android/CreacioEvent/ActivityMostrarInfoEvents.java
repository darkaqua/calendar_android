package com.example.irene.calendar_android.CreacioEvent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONObject;

public class ActivityMostrarInfoEvents extends AppCompatActivity {

    TextView nomEvent, descripcio, hora, dia, duracio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_events);


        nomEvent = (TextView)findViewById(R.id.textViewInfoEventNom);
        descripcio = (TextView)findViewById(R.id.textViewInfoEventDescripcio);
        hora = (TextView)findViewById(R.id.textViewInfoHoraEvent);
        dia = (TextView)findViewById(R.id.textViewInfoDiaEvent);
        duracio = (TextView)findViewById(R.id.textViewInfoDuracioEvent);

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

        carregarDades();
    }

    private void carregarDades(){
        final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
        final Context context = this;
        final AppCompatActivity appCompatActivity = this;

        try{
            JSONObject jsonObject = new JSONObject();
            apiConnector.GET("User/Dates", jsonObject, new Request() {
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
                                    hora.setText(datetime);
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







}
