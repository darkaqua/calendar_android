package com.example.irene.calendar_android.CreacioEvent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.irene.calendar_android.CreacioGrups.ActivityLlistatGrups;
import com.example.irene.calendar_android.CreacioGrups.ActivityMostrarInfoGrup;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;


@RequiresApi(api = Build.VERSION_CODES.N)
public class Creacio_Events extends AppCompatActivity implements View.OnClickListener {

    Button btnTime, btnDate, btnCancelar, btnCrearEvent;
    TextView tvTime, tvDate;
    EditText titolEvent, descripcio, duracio;
    FloatingActionButton btnHome;

    private String company_uuid, group_id;

    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;

    Calendar calendar = Calendar.getInstance();

    String[] pickedDateTime = { "00/00/0000", "00:00" };

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacio__events);

        btnTime = (Button)findViewById(R.id.btnhoraPicker);
        btnTime.setOnClickListener(this);
        btnDate = (Button)findViewById(R.id.btnDiaPicker);
        btnDate.setOnClickListener(this);

        btnCancelar = (Button)findViewById(R.id.btnCancelarEvent);
        btnCancelar.setOnClickListener(this);

        btnCrearEvent = (Button)findViewById(R.id.btnAcceptarEvent) ;
        btnCrearEvent.setOnClickListener(this);

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        tvTime = (TextView)findViewById(R.id.textViewTime);
        tvDate = (TextView)findViewById(R.id.textViewDay);


        titolEvent = (EditText)findViewById(R.id.editTextNomEvent);
        descripcio = (EditText)findViewById(R.id.editTextDescripcioEvent);
        duracio = (EditText)findViewById(R.id.editTextDuracioEvent);

        company_uuid = getIntent().getExtras().getString("company_uuid");
        group_id = getIntent().getExtras().getString("group_id");

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Boton atras de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Creacio_Events.this, ActivityMostrarInfoGrup.class);
                i.putExtra("company_uuid", company_uuid);
                i.putExtra("group_id", group_id);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v){

        calendar = Calendar.getInstance();

        switch (v.getId()){
            case R.id.btnhoraPicker:{

                timePickerDialog = new TimePickerDialog(Creacio_Events.this ,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet (TimePicker view, int hourOfDay, int minute){

                        Calendar timeCalendar = Calendar.getInstance();
                        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay-2);
                        timeCalendar.set(Calendar.MINUTE, minute);

                        String timeString = DateUtils.formatDateTime(Creacio_Events.this, timeCalendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
                        System.out.println(timeString);
                        pickedDateTime[1] = timeString.split(" ")[0];
                        ((TextView)findViewById(R.id.textViewTime)).setText(pickedDateTime[1]);

                    }
                },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(Creacio_Events.this));

                timePickerDialog.show();
                break;
            }

            case R.id.btnDiaPicker:{

                datePickerDialog = new DatePickerDialog(Creacio_Events.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        System.out.println(dayOfMonth + "/" + monthOfYear + "/" + year);
                        pickedDateTime[0] = dayOfMonth + "/" + monthOfYear + "/" + year;
                        ((TextView)findViewById(R.id.textViewDay)).setText(pickedDateTime[0]);

                    }
                }, calendar.get(java.util.Calendar.YEAR), calendar.get(java.util.Calendar.MONTH), calendar.get(java.util.Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
                break;
            }

            case R.id.btnCancelarEvent:
                new AlertDialog.Builder(this)
                        .setTitle("Important")
                        .setMessage("Estàs segur que vols cancel·lar l'operació ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Intent i = new Intent(Creacio_Events.this, MainActivity.class);
                                startActivity(i);
                                Toast.makeText(Creacio_Events.this, "Creació cancel·lada",
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

            case R.id.btnAcceptarEvent:

                try{
                    final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
                    final AppCompatActivity appCompatActivity = this;
                    final Context context = getApplicationContext();

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("company_uuid", company_uuid);
                    jsonObject.put("group_id", group_id);
                    jsonObject.put("title", titolEvent.getText().toString());
                    jsonObject.put("description", descripcio.getText().toString());
                    jsonObject.put("datetime", pickedDateTime[0] + " " + pickedDateTime[1]);
                    jsonObject.put("long_minutes", duracio.getText().toString());
                    System.out.println("===========> "+jsonObject);

                    apiConnector.PUT("Company/Group/Date", jsonObject, new Request() {
                        @Override
                        public void Response(Object o) {
                            final JSONObject res = (JSONObject) o;
                            System.out.println("===========> "+res);

                            appCompatActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try{

                                        if(res.getBoolean("valid")){
                                          //  Intent i = new Intent(Creacio_Events.this, MainActivity.class);
                                            Intent i = new Intent(getApplicationContext(), ActivityLlistatEvents.class);
                                            i.putExtra("company_uuid", company_uuid);
                                            i.putExtra("group_id", group_id);
                                            startActivity(i);

                                            Toast.makeText(Creacio_Events.this, "Event creat", Toast.LENGTH_LONG).show();
                                            startActivity(i);
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

                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.floatingActionButtonMain:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

}

