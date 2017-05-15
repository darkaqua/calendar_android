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
            "_id",
            "uuid",
            "name",
            "description"
    };
    public static final int[] TO = new int[]{
            -1,
            R.id.uuidEmpresa,
            R.id.txtNomEmpresa,
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
                    final MatrixCursor mc = new MatrixCursor(FROM);

                    appCompatActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                for(int i = 0; i<res.length(); i ++){
                                    JSONObject object = res.getJSONObject(i);
                                    mc.addRow(new String[]{
                                            i + "",
                                            object.getString("uuid"),
                                            object.getString("name"),
                                            object.getString("description")});

                                }
                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + mc.getCount());
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

}


class AdapterListEmpreses extends SimpleCursorAdapter {


    public AdapterListEmpreses(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

    }






}

