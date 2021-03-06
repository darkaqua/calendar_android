package com.example.irene.calendar_android.CreacioGrups;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.irene.calendar_android.Companyies.ActivityLlistatEmpreses;
import com.example.irene.calendar_android.Companyies.ActivityMostrarInfoEmpresa;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.Usuaris.ActivityLlistatUsersCompanyies;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityLlistatGrups extends ListActivity implements View.OnClickListener{

    ListView llistat;
    private AdaptadorGrups cAdapter;
    public String company_uuid;
    Button btnListCrearGrup;
    FloatingActionButton btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_grups);

        company_uuid = getIntent().getExtras().getString("company_uuid");
        System.out.println("==================>"+company_uuid);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);
        //Boton atras de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));

        btnListCrearGrup = (Button) findViewById(R.id.btnListCrearGrup);
        btnListCrearGrup.setOnClickListener(this);


        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

        carregarLlistat();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityLlistatGrups.this, ActivityMostrarInfoEmpresa.class);
                i.putExtra("uuid", company_uuid);
                startActivity(i);
            }
        });

    }

    public static final String[] FROM = new String[]{
            "_id",
            "company_uuid",
            "group_id",
            "name",
            "description",
    };
    public static final int[] TO = new int[]{
            -1,
            R.id.row_group_id,
            R.id.row_group_company_uuid,
            R.id.txtNomGrup,
            R.id.txtDescripcioGrup,
    };



    private void carregarLlistat(){

        try {
            final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;
            final ListActivity appCompatActivity = this;
            final Context context = this;

            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("company_uuid", company_uuid);

            apiConnector.GET("User/Company/Groups", jsonObject, new Request() {
                @Override
                public void Response(Object o) {
                    System.out.println(o.toString());

                    final JSONArray res = (JSONArray) o;
                    System.out.println("----------------------------"+res.toString());
                    final MatrixCursor mc = new MatrixCursor(FROM);

                    appCompatActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                for(int i = 0; i<res.length(); i ++){
                                    JSONObject object = res.getJSONObject(i);
                                    mc.addRow(new Object[]{
                                            i + "",
                                            object.getString("company_uuid"),
                                            object.getString("id"),
                                            object.getString("name"),
                                            object.getString("description")
                                    });

                                }
                                cAdapter= new AdaptadorGrups(
                                        appCompatActivity,
                                        R.layout.row_grups,
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(ListView l , View view, int position, long id) {
        try{
            Cursor jsonObject1 = (Cursor) getListAdapter().getItem(position);
            Intent i = new Intent(getApplicationContext(), ActivityMostrarInfoGrup.class);

            i.putExtra("company_uuid", jsonObject1.getString(jsonObject1.getColumnIndexOrThrow("company_uuid")));
            i.putExtra("group_id", jsonObject1.getString(jsonObject1.getColumnIndexOrThrow("group_id")));
            startActivity(i);


        }catch (Exception e){
            e.printStackTrace();
        }

        // Toast.makeText(ActivityLlistatGrups.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        // mostrarInfoEmpreses(id);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.floatingActionButtonMain:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnListCrearGrup:
                Intent i = new Intent(getApplicationContext(), Creacio_Grups.class);
                i.putExtra("company_uuid", company_uuid);
                startActivity(i);
                break;
        }
    }
}
