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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.R;

import java.text.DateFormat;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Creacio_Events extends AppCompatActivity implements View.OnClickListener {


    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private TextView text;
    private Button btn_date;
    private Button btn_time;
    Button aceptarRegistro, cancelarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacio__events);

        aceptarRegistro = (Button) findViewById(R.id.btnAceptarCreacioEvent);
        aceptarRegistro.setOnClickListener(this);
        cancelarRegistro = (Button) findViewById(R.id.btnCancelaCreacioComp);
        cancelarRegistro.setOnClickListener(this);

        text = (TextView) findViewById(R.id.txt_TextDateTime);
        btn_date = (Button) findViewById(R.id.btn_diaPicker);
        btn_time = (Button) findViewById(R.id.btn_horaPicker);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        updateTextLabel();
    }
    public void onClick(View v){
        int id = v.getId();

        switch (id){

            case R.id.btnAceptarCreacioComp:

                Intent intent = new Intent(Creacio_Events.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.btnCancelaCreacioComp:


                new AlertDialog.Builder(this)
                        .setTitle("CANCEL·lAR")
                        .setMessage("Estàs segur que vols cancel·lar l'operació ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Toast.makeText(Creacio_Events.this, "Creació cancel·lada",
                                        Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Creacio_Events.this, MainActivity.class);
                                startActivity(i);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                                Toast.makeText(Creacio_Events.this, "Acceptat",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                break;



        }


    }




    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextLabel();
        }
    };

    private void updateTextLabel(){
        text.setText(formatDateTime.format(dateTime.getTime()));
    }

}
