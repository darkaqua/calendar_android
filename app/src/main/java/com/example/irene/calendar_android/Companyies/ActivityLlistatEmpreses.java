package com.example.irene.calendar_android.Companyies;

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
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.Login.ActivityRegistre;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityLlistatEmpreses extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_empreses);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        });*/


//        ListView llista=(ListView)findViewById(R.id.listViewLlistatEmpreses);



        loadlist();



    }

    public static final String[] FROM = new String[]{"uuid", "name", "description"};
    public static final int[] TO = new int[]{
            R.id.uuidEmpresa,
            R.id.txtNomEmpresa,
            R.id.txtDescripcioEmpresa
    };

    private void loadlist(){

        try {
            final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;

            final ListActivity appCompatActivity = this;
            final Context context = getApplicationContext();

            final JSONObject jsonObject = new JSONObject();

            apiConnector.GET("User/Companies", jsonObject, new Request() {
                @Override
                public void Response(Object o) {
                    System.out.println(o.toString());
                    final JSONArray res = (JSONArray) o;
                    MatrixCursor mc = new MatrixCursor(FROM);
                    try{
                        for(int i = 0; i<res.length(); i ++){
                            JSONObject object = res.getJSONObject(i);
                            mc.addRow(new String[]{object.getString("uuid"), object.getString("name"), object.getString("description")});

                        }
                        appCompatActivity.setListAdapter(
                                new AdapterListEmpreses(
                                        appCompatActivity,
                                        R.layout.row_empreses,
                                        mc,
                                        FROM,
                                        TO,
                                        1
                                )
                        );
                    }catch (Exception e){

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}


class AdapterListEmpreses extends SimpleCursorAdapter {


    public AdapterListEmpreses(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

    }




}

