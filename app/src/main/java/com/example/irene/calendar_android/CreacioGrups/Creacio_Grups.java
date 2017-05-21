package com.example.irene.calendar_android.CreacioGrups;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class Creacio_Grups extends AppCompatActivity implements View.OnClickListener{

    EditText nomGrup, descripcio;
    Button guardarCanvis, cancelar;
    public String company_uuid;
    int group_id;
    public static ApiConnector API_CONNECTOR;
    FloatingActionButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacio__grups);

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

        nomGrup = (EditText)findViewById(R.id.editTextCreacioGrupNom);
        descripcio = (EditText)findViewById(R.id.editTextCreacioEventsDescripcio);

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        company_uuid = getIntent().getExtras().getString("company_uuid");

        cancelar = (Button)findViewById(R.id.btnCreacioGrupsCancelar);
        cancelar.setOnClickListener(this);
        guardarCanvis = (Button)findViewById(R.id.btnCreacioGrupsGuardar);
        guardarCanvis.setOnClickListener(this);
    }
    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

    public void onClick(View v){
        Bundle b = new Bundle();
        int id = v.getId();

        switch (id){

            case R.id.btnCreacioGrupsCancelar:

                new AlertDialog.Builder(this)
                        .setTitle("Important")
                        .setMessage("Estàs segur que vols cancel·lar l'operació ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                Intent i = new Intent(Creacio_Grups.this, MainActivity.class);
                                startActivity(i);
                                Toast.makeText(Creacio_Grups.this, "Creació cancel·lada",
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
            case R.id.btnCreacioGrupsGuardar:

                    try {
                        final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;

                        final AppCompatActivity appCompatActivity = this;
                        final Context context = getApplicationContext();

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("company_uuid", company_uuid);
                        jsonObject.put("id", group_id);
                        jsonObject.put("name", nomGrup.getText().toString());
                        jsonObject.put("description", descripcio.getText().toString());

                        apiConnector.PUT("Company/Group", jsonObject, new Request() {
                            @Override
                            public void Response(Object o) {
                                final JSONObject res = (JSONObject) o;

                                    appCompatActivity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                if(res.getBoolean("valid")){
                                                    Bundle b =  new Bundle();
                                                    b.putString("company_uuid", company_uuid);
                                                    b.putInt("group_id", group_id);

                                                    Intent i = new Intent(getApplicationContext(), ActivityLlistatGrups.class);
                                                    i.putExtras(b);

    //                                                    i.putExtra("company_uuid", company_uuid);
//                                                    i.putExtra("group_id", company_uuid);
                                                    Toast.makeText(Creacio_Grups.this, "Grup creat", Toast.LENGTH_LONG).show();
                                                //    i.putExtras(b);
                                                    startActivity(i);



                                                    return;
                                                }


                 Toast.makeText(context, res.get("message").toString(), Toast.LENGTH_SHORT).show();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });


                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                break;

            case R.id.floatingActionButtonMain:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;


        }
    }
}
