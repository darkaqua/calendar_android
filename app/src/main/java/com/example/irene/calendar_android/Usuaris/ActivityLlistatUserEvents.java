package com.example.irene.calendar_android.Usuaris;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.irene.calendar_android.CreacioEvent.ActivityLlistatEvents;
import com.example.irene.calendar_android.CreacioEvent.ActivityMostrarInfoEvents;
import com.example.irene.calendar_android.CreacioGrups.AdaptadorGrups;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityLlistatUserEvents extends ListActivity implements View.OnClickListener{

    private AdaptadorGrups cAdapter;
    FloatingActionButton btnHome;
    public String company_uuid, group_id, date_id;
    Button btnAgregarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);
        //Boton atras de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        btnAgregarUser = (Button)findViewById(R.id.btnLlistAfegirUsuaris);
        btnAgregarUser.setOnClickListener(this);

        company_uuid = getIntent().getExtras().getString("company_uuid");
        group_id = getIntent().getExtras().getString("group_id");
        date_id = getIntent().getExtras().getString("date_id");

        carregarLlistat();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityLlistatUserEvents.this, ActivityMostrarInfoEvents.class);
                i.putExtra("company_uuid", company_uuid);
                i.putExtra("group_id", group_id);
                i.putExtra("date_id", date_id);
                startActivity(i);
            }
        });
    }

    public static final String[] FROM = new String[]{
            "_id",
            "name",
            "username",
    };
    public static final int[] TO = new int[]{
            -1,
            R.id.txtNomUser,
            R.id.txtCognomUsuari,
    };


    private void carregarLlistat(){
        try{

            final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;
            final ListActivity appCompatActivity = this;
            final Context context = this;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("company_uuid", company_uuid);
            jsonObject.put("group_id", group_id);
            jsonObject.put("date_id", date_id);

            apiConnector.GET("Company/Group/Date/Users", jsonObject, new Request() {
                @Override
                public void Response(Object o) {
                    final JSONArray res = (JSONArray) o;
                    final MatrixCursor mc = new MatrixCursor(FROM);

                    appCompatActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                for(int i = 0; i<res.length(); i ++){
                                    JSONObject object = res.getJSONObject(i);
                                    mc.addRow(new Object[]{
                                            i + "",
                                            object.getString("name"),
                                            object.getString("username")
                                    });

                                }
                                cAdapter= new AdaptadorGrups(
                                        appCompatActivity,
                                        R.layout.row_usuari,
                                        mc,
                                        FROM,
                                        TO,
                                        1
                                );
                                setListAdapter(cAdapter);


                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });

                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.floatingActionButtonMain:

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;

            case R.id.btnLlistAfegirUsuaris:

                Intent intent = new Intent(getApplicationContext(), ActivityAfegirUsuariEvent.class);
                intent.putExtra("company_uuid", company_uuid);
                intent.putExtra("group_id", group_id);
                intent.putExtra("date_id", date_id);
                startActivity(intent);
                break;
        }
    }
}
