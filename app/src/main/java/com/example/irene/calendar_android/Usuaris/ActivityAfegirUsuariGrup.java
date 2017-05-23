package com.example.irene.calendar_android.Usuaris;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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

public class ActivityAfegirUsuariGrup extends AppCompatActivity implements View.OnClickListener{


    FloatingActionButton btnHome;
    Button btnAfegir, btnEliminar;
    public String company_uuid, group_id;
    public boolean can_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_usuari_companyia);

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

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        btnAfegir = (Button)findViewById(R.id.btnAfegirUsuari);
        btnAfegir.setOnClickListener(this);

        btnEliminar = (Button)findViewById(R.id.btnEliminarUsuari);
        btnEliminar.setOnClickListener(this);

        company_uuid = getIntent().getExtras().getString("company_uuid");
        group_id = getIntent().getExtras().getString("group_id");



    }
    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

    @Override
    public void onClick(View view) {

        final int id = view.getId();

        System.out.println(id);

        try{

            final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;
            final AppCompatActivity appCompatActivity = this;
            final Context context = getApplicationContext();

            JSONObject jObject = new JSONObject();
            jObject.put("username",  ((EditText)findViewById(R.id.editTextNomUsuari)).getText());
            System.out.println("===================="+jObject);

            apiConnector.GET("User/uuid", jObject, new Request() {

                @Override
                public void Response(Object o) {

                    System.out.println("--------------------$--------------------->" + o.toString());

                    switch (id){
                        case R.id.btnAfegirUsuari:
                            try{

                                final JSONObject res = (JSONObject) o;
                                System.out.println("=========>>>"+ res);

                                if(!res.getBoolean("valid")){
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
                                    return;
                                }

                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("company_uuid", company_uuid);
                                jsonObject.put("group_id", group_id);
                                jsonObject.put("user_uuid", res.getString("user_uuid"));
                                jsonObject.put("can_edit", true);

                                apiConnector.PUT("Company/Group/User", jsonObject, new Request() {

                                    @Override
                                    public void Response(Object o) {

                                        final JSONObject res = (JSONObject) o;

                                        try{

                                            if(res.getBoolean("valid")){
                                                Intent i = new Intent(ActivityAfegirUsuariGrup.this, ActivityLlistatUserGrups.class);
                                                i.putExtra("company_uuid", company_uuid);
                                                i.putExtra("group_id", group_id);
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

                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case R.id.btnEliminarUsuari:
                            try{

                                final JSONObject res = (JSONObject) o;
                                System.out.println("=========>>>"+ res);

                                if(!res.getBoolean("valid")){
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
                                    return;
                                }

                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("company_uuid", company_uuid);
                                jsonObject.put("group_id", group_id);
                                jsonObject.put("user_uuid", res.getString("user_uuid"));

                                apiConnector.DELETE("Company/Group/User", jsonObject, new Request() {

                                    @Override
                                    public void Response(Object o) {

                                        final JSONObject res = (JSONObject) o;

                                        try{

                                            if(res.getBoolean("valid")){
                                                Intent i = new Intent(ActivityAfegirUsuariGrup.this, ActivityLlistatUserGrups.class);
                                                i.putExtra("company_uuid", company_uuid);
                                                i.putExtra("group_id", group_id);
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

                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }


                }

            });



        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
