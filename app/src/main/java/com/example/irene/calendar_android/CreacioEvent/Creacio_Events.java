package com.example.irene.calendar_android.CreacioEvent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.irene.calendar_android.CompanieMenu.Creacio_Companyia;
import com.example.irene.calendar_android.ConfiguracioUsuaris.ActivityConfiguracioUsuari;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.R;

import java.text.DateFormat;


@RequiresApi(api = Build.VERSION_CODES.N)
public class Creacio_Events extends AppCompatActivity implements View.OnClickListener {

    Button btnTime, btnDate, btnCancelar;
    TextView tvTime, tvDate;

    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;

    Calendar calendar = Calendar.getInstance();


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

        tvTime = (TextView)findViewById(R.id.textViewTime);
        tvDate = (TextView)findViewById(R.id.textViewDay);

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

    @Override
    public void onClick(View v){

        calendar = Calendar.getInstance();

        switch (v.getId()){
            case R.id.btnhoraPicker:{

                timePickerDialog = new TimePickerDialog(Creacio_Events.this ,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet (TimePicker view, int hourOfDay, int minute){

                        Calendar timeCalendar = Calendar.getInstance();
                        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        timeCalendar.set(Calendar.MINUTE, minute);

                        String timeString = DateUtils.formatDateTime(Creacio_Events.this, timeCalendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
                        tvTime.setText("Hora: "+  timeString);

                    }
                },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(Creacio_Events.this));

                timePickerDialog.show();
                break;
            }

            case R.id.btnDiaPicker:{

                datePickerDialog = new DatePickerDialog(Creacio_Events.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        java.util.Calendar dateCalendar = java.util.Calendar.getInstance();
                        dateCalendar.set(java.util.Calendar.YEAR, year);
                        dateCalendar.set(java.util.Calendar.MONTH, monthOfYear);
                        dateCalendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);

                        String dateString = DateUtils.formatDateTime(Creacio_Events.this, dateCalendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE);
                        tvDate.setText("Dia: " + dateString);

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
        }

    }

    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

}

