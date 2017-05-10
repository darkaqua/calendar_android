package com.example.irene.calendar_android.CompanyCreation;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.ConfiguracioUsuaris.ActivityConfiguracioUsuari;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityRegistre;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.SQLite.CalendarDataSource;

public class CreacioCompanyia extends AppCompatActivity {

   /* Button aceptarRegistro, cancelarRegistro;


    private CalendarDataSource bd;
    EditText nomEmpresa, telefon, email, ciutat, adreça, codiPostal;

    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacio__companyia);

        aceptarRegistro = (Button) findViewById(R.id.btnAceptarCreacioComp);
        aceptarRegistro.setOnClickListener(this);
        cancelarRegistro = (Button) findViewById(R.id.btnCancelaCreacioComp);
        cancelarRegistro.setOnClickListener(this);

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

                Intent intent = new Intent(CreacioCompanyia.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.btnCancelaCreacioComp:

                Toast.makeText(getApplicationContext(), "Cancel·lar canvis", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CreacioCompanyia.this, MainActivity.class);
                startActivity(i);


                /*new AlertDialog.Builder(this)
                        .setTitle("CANCEL·lAR")
                        .setMessage("Estàs segur que vols cancel·lar l'operació ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Toast.makeText(CreacioCompanyia.this, "Creació cancel·lada",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                                Toast.makeText(CreacioCompanyia.this, "Acceptat",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
*/

           //     break;
/*


        }


    }*/



}
