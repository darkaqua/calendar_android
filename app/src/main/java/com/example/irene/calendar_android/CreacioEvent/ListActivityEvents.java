package com.example.irene.calendar_android.CreacioEvent;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.irene.calendar_android.CreacioGrups.ActivityMostrarInfoGrup;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListActivityEvents extends ListActivity {

    public String company_uuid, group_id;

    private AdaptadorEvents cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_events);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        company_uuid = getIntent().getExtras().getString("company_uuid");
        group_id = getIntent().getExtras().getString("group_id");

        carregarLlistat();

    }


    public static final String[] FROM = new String[]{
            "_id",
            "date_id",
            "group_id",
            "company_uuid",
            "title",
            "datetime"
    };

    public static final int[] TO = new int[]{
            -1,
            R.id.idEvent,
            R.id.idGroup,
            R.id.uuidCompany,
            R.id.txtTitolEvent,
            R.id.txtHoraEvent
    };

    private void carregarLlistat (){

        try{

            final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
            final ListActivity appCompatActivity = this;
            final Context context = this;

            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("company_uuid", company_uuid);
            jsonObject.put("group_id", group_id);

            apiConnector.GET("Company/Group/Dates", jsonObject, new Request(){

                @Override
                public void Response(Object o) {
                    System.out.println("=====>====>===> "+o.toString());

                    final JSONArray res = (JSONArray) o ;
                    final MatrixCursor mc = new MatrixCursor(FROM);

                    appCompatActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                for(int i = 0; i<res.length(); i++){
                                    JSONObject object = res.getJSONObject(i);
                                    mc.addRow(new Object[]{
                                            i + "",
                                            object.getString("id"),
                                            group_id,
                                            company_uuid,
                                            object.getString("title"),
                                            object.getString("datetime")
                                    });
                                }
                                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>> " + mc.getCount());

                                cAdapter = new AdaptadorEvents(
                                        appCompatActivity,
                                        R.layout.row_events,
                                        mc,
                                        FROM,
                                        TO,
                                        1
                                );

                                setListAdapter(cAdapter);
                                /*llistat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        try{
                                            JSONObject jsonObject1 = res.getJSONObject(0);
                                            Intent intent = new Intent(getApplicationContext(), ActivityMostrarInfoEvents.class);
                                            intent.putExtra("id", jsonObject1.getString("id"));
                                            startActivity(intent);


                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                });*/






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
    public void onListItemClick(ListView l , View view, int position, long id) {
        try{
            Cursor jsonObject1 = (Cursor) getListAdapter().getItem(position);
            Intent i = new Intent(getApplicationContext(), ActivityMostrarInfoEvents.class);

            i.putExtra("company_uuid", jsonObject1.getString(jsonObject1.getColumnIndexOrThrow("company_uuid")));
            i.putExtra("group_id", jsonObject1.getString(jsonObject1.getColumnIndexOrThrow("group_id")));
            i.putExtra("date_id", jsonObject1.getString(jsonObject1.getColumnIndexOrThrow("date_id")));
            startActivity(i);


        }catch (Exception e){
            e.printStackTrace();
        }

        // Toast.makeText(ActivityLlistatGrups.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        // mostrarInfoEmpreses(id);

    }
}
