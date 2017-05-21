package com.example.irene.calendar_android.CreacioGrups;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityLlistatGrups extends ListActivity {

    ListView llistat;
    private AdaptadorGrups cAdapter;
    public String company_uuid;
    Button eliminarGrup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_grups);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        //Boton atras de la toolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

      //  eliminarGrup = (Button)findViewById(R.id.btnGrupEliminar);
     //   eliminarGrup.setOnClickListener(this);
        company_uuid = getIntent().getExtras().getString("company_uuid");
        System.out.println("==================>"+company_uuid);

        //llistat = (ListView)findViewById(R.id.llistatGrups);
        carregarLlistat();

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
                                if(mc.getCount() == 0){
                                    System.out.print("No hi ha grups creats");

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
                            //    llistat.setAdapter(cAdapter);
//                                llistat.setOnItemClickListener(new AdapterView.OnItemClickListener() {


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


//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//
//        switch (id) {
//
//            case R.id.btnGrupEliminar:
//                final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
//                final Context context = this;
//                final ListActivity appCompatActivity = this;
//
//                new AlertDialog.Builder(this)
//                        .setTitle("Important")
//                        .setMessage("Estàs segur que vols eliminar aquest grup?")
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnCancelListener(){
//                            public void onClick(DialogInterface dialog, int which){
//                                try{//ELIMINAR GRUP
//                                    final JSONObject jsonObject = new JSONObject();
//                                    jsonObject.put("company_uuid", company_uuid);
//                                    jsonObject.put("group_id", g)
//
//
//                                }catch (Exception e){
//                                    e.printStackTrace();
//                                }
//                            }
//
//
//
//                        });
//
//
//
//
//                break;
//
//        }
//
//    }
}
