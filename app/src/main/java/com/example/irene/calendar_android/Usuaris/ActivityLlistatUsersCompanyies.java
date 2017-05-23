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

import com.example.irene.calendar_android.CreacioGrups.AdaptadorGrups;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityLlistatUsersCompanyies  extends ListActivity implements View.OnClickListener{

    private AdaptadorGrups cAdapter;
    FloatingActionButton btnHome;
    public String company_uuid;
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        btnAgregarUser = (Button)findViewById(R.id.btnLlistAfegirUsuaris);
        btnAgregarUser.setOnClickListener(this);

        company_uuid = getIntent().getExtras().getString("company_uuid");
        System.out.println("=================> "+company_uuid);

        carregarLlistat();
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


    private void carregarLlistat() {

        try{

            final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;
            final ListActivity appCompatActivity = this;
            final Context context = this;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("company_uuid", company_uuid);

            apiConnector.GET("Company/Users", jsonObject, new Request() {
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

                Intent intent = new Intent(getApplicationContext(), ActivityAfegirUsuariCompanyia.class);
                intent.putExtra("company_uuid", company_uuid);
                startActivity(intent);
                break;


        }
    }
}
