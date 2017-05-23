package com.example.irene.calendar_android.CreacioGrups;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.irene.calendar_android.Companyies.ActivityMostrarInfoEmpresa;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.Usuaris.ActivityAfegirUsuariCompanyia;
import com.example.irene.calendar_android.Usuaris.ActivityLlistatUsersCompanyies;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListActivityGrups extends ListActivity {

    private Adapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_grups);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_flecha));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivityGrups.this, MainActivity.class);
                startActivity(i);
            }
        });

        carregarLlistat();
    }

    public static final String[] FROM = new String[]{
            "_id",
            "group_id",
            "company_uuid",
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
            apiConnector.GET("User/Groups", jsonObject, new Request() {
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
                                            object.getString("id"),
                                            object.getString("company_uuid"),
                                            object.getString("name"),
                                            object.getString("description")
                                    });

                                    System.out.println(mc.getCount());
                                    cAdapter = new Adapter(
                                            appCompatActivity,
                                            R.layout.row_grups,
                                            mc,
                                            FROM,
                                            TO,
                                            1
                                    );
                                    setListAdapter(cAdapter);
                                    /*llistat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                            try{
                                                JSONObject jsonObject1 = res.getJSONObject(0);
                                                Intent i = new Intent(getApplicationContext(), ActivityMostrarInfoGrup.class);
                                                i.putExtra("company_uuid", jsonObject1.getString("company_uuid"));
                                                i.putExtra("group_id", jsonObject1.getString("id"));
                                                startActivity(i);


                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }

                                        }
                                    });*/

                                }



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

    }
}

