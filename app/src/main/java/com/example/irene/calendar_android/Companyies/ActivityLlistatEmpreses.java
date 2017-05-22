package com.example.irene.calendar_android.Companyies;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.irene.calendar_android.CreacioGrups.ActivityMostrarInfoGrup;
import com.example.irene.calendar_android.CreacioGrups.AdaptadorGrups;
import com.example.irene.calendar_android.CreacioGrups.ListActivityGrups;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.SQLite.CalendarDataSource;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ActivityLlistatEmpreses extends ListActivity implements View.OnClickListener{

    private AdaptadorGrups cAdapter;
    FloatingActionButton btnHome;
    private CalendarDataSource BD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_empreses);

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

        carregarLlistat();

    }

    public static final String[] FROM = new String[]{
            "_id",
            "uuid",
            "name",
            "description"
    };
    public static final int[] TO = new int[]{
            -1,
            R.id.uuidEmpresa,
            R.id.txtNomGrup,
            R.id.txtDescripcioGrup
    };

    private void carregarLlistat(){
        try {
            final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;
            final ListActivity appCompatActivity = this;
            final Context context = this;

            final JSONObject jsonObject = new JSONObject();

            apiConnector.GET("User/Companies", jsonObject, new Request() {
                @Override
                public void Response(Object o) {
                    System.out.println(o.toString());

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
                                                object.getString("uuid"),
                                                object.getString("name"),
                                                object.getString("description")
                                        });

                                    }
                                    if(mc.getCount() == 0){
                                        System.out.print("No hi ha empreses creades");


                                    }
                                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + mc.getCount());
                                    cAdapter= new AdaptadorGrups(
                                                    appCompatActivity,
                                                    R.layout.row_grups,
                                                    mc,
                                                    FROM,
                                                    TO,
                                                    1
                                            );
                                    setListAdapter(cAdapter);
                                   /* llistat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                            try{
                                                JSONObject jsonObject1 = res.getJSONObject(0);
                                                Intent i = new Intent(getApplicationContext(), ActivityMostrarInfoEmpresa.class);
                                                i.putExtra("uuid", jsonObject1.getString("uuid"));
                                                startActivity(i);


                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }

                                            //Toast.makeText(ActivityLlistatEmpreses.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
                                            //mostrarInfoEmpreses(id);
                                            //
                                        }
                                    });*/

                                    findViewById(R.id.btnLlistCrearEmpresa).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(getApplicationContext(), Creacio_Companyia.class));
                                        }
                                    });

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                            }
                        });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarInfoEmpreses(long id){

        Cursor cursor = BD.getEmpreses(id);
        cursor.moveToFirst();

        //String idEmpresa = cursor.getString(cursor.getColumnIndex("client_id"));
        //Toast.makeText(ActivityLlistatEmpreses.this, Integer.toString(cursor.getCount()), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, ActivityMostrarInfoEmpresa.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.floatingActionButtonMain:

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onListItemClick(ListView l , View view, int position, long id) {
        try{
            Cursor jsonObject1 = (Cursor) getListAdapter().getItem(position);
            Intent i = new Intent(getApplicationContext(), ActivityMostrarInfoEmpresa.class);

            i.putExtra("uuid", jsonObject1.getString(jsonObject1.getColumnIndexOrThrow("uuid")));
            startActivity(i);


        }catch (Exception e){
            e.printStackTrace();
        }

        //Toast.makeText(ActivityLlistatEmpreses.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        //mostrarInfoEmpreses(id);

    }
}


