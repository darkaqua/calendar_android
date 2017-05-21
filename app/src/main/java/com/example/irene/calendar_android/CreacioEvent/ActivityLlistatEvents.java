package com.example.irene.calendar_android.CreacioEvent;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.irene.calendar_android.Companyies.ActivityMostrarInfoEmpresa;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityLlistatEvents extends ListActivity {

    public String company_uuid ;
    public String group_id;

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

        carregarLlistat();

    }


    public static final String[] FROM = new String[]{
            "_id",
            "date_id",
            "title",
            "datetime",
            "long_minutes",
    };

    public static final int[] TO = new int[]{
            -1,
            R.id.uuidEvent,
            R.id.txtTitolEvent,
            R.id.txtDiaEvent,
            R.id.txtHoraEvent,
    };

    private void carregarLlistat (){

        try{

            final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
            final ListActivity appCompatActivity = this;
            final Context context = this;

            final JSONObject jsonObject = new JSONObject();
            apiConnector.GET("User/Dates", jsonObject, new Request(){

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
                                            object.getString("title"),
                                            object.getString("datetime"),
                                            object.getString("long_minutes")
                                    });
                                }
                                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>> "+mc.getCount());

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
}
