package com.example.irene.calendar_android.Companyies;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.example.irene.calendar_android.R.id.txtNomEmpresa;

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

        ListView llista=(ListView)findViewById(R.id.list);
        carregarLlistat();
    }

    public static final String[] FROM = new String[]{
            "uuid",
            "name",
            "description"
    };
    public static final int[] TO = new int[]{
            R.id.uuidEmpresa,
            txtNomEmpresa,
            R.id.txtDescripcioEmpresa
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
                    MatrixCursor mc = new MatrixCursor(FROM);
                    try{
                        String name = jsonObject.getString("name");
                      //  txtNomEmpresa.setText(name);

                        for(int i = 0; i<res.length(); i ++){
                            JSONObject object = res.getJSONObject(i);
                            mc.addRow(new String[]{
                                    object.getString("uuid"),
                                    name = object.getString("name"),



                                    object.getString("description")});

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

