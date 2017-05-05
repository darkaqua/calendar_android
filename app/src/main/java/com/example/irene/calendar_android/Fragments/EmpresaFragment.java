package com.example.irene.calendar_android.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.irene.calendar_android.CompanyCreation.Creacio_Companyia;
import com.example.irene.calendar_android.CreacioEvent.Creacio_Events;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.SQLite.CalendarDataSource;
import com.example.irene.calendar_android.SQLite.CalendarOpenHelper;

import static android.app.Activity.RESULT_OK;
import static com.example.irene.calendar_android.R.id.view;


public class EmpresaFragment extends Fragment  {


  /*  private static int ACTIVITY_TASK_ADD = 1;
    private static int ACTIVITY_TASK_UPDATE = 2;

    private CalendarDataSource BD;
    private long idActual;
    private int positionActual;
    // private adaperLlistaEmpresa simpleCursorEmpresa;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_list, container, false);

        ListView llistat = (ListView)view.findViewById(R.id.llistatCompanyia);

        //Boto per agregar els empreses
        Button crearEmpreses;
        crearEmpreses = (Button) view.findViewById(R.id.btnCrearEmpresesList);
        crearEmpreses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Creacio_Events.class);
                startActivity(i);
            }
        });
        return view;
    }

  //  @Override
   // public void onClick(View view) {

        //btnCrearCompanyiaList

     /*   switch (view.getId()){
            case R.id.btnCrearCompanyiaList:

                Bundle b = new Bundle();
                b.putLong("id", -1);

                Intent i = new Intent (getActivity(), Creacio_Companyia.class);
                i.putExtras(b);
                //    startActivityForResult(i,ACTIVITY_TASK_ADD);

                break;
        }*/
  //  }
 /*
        BD = new CalendarDataSource(this);
        carregarLlistat();

        ListView llistatEmpreses = (ListView)getView().findViewById(R.id.llistatCompanyia);
        llistatEmpreses.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View vista, int posicion,
                                    long id) {
               updateLListat(id);

            }

        });
        return  v;
    }
    */
/*
    private void carregarLlistat() {

        Cursor cursor = BD.carregarTotaLaTaulaEmpresa();
        ListView llistat = (ListView)getView().findViewById(R.id.llistatCompanyia);
        simpleCursorEmpresa = new adaperLlistaEmpresa(getActivity(), R.layout.list_empreses, cursor, from, to, 1);
        llistat.setAdapter(simpleCursorEmpresa);
    }

    private void updateLListat(long id) {
        Bundle b = new Bundle();
        b.putLong("id", id);

        idActual = id;

        Intent i = new Intent(getActivity(), Creacio_Companyia.class);
        i.putExtras(b);
        startActivityForResult(i, ACTIVITY_TASK_UPDATE);
    }

    private void refreshLlistatEmpreses(){
        Cursor cursor = BD.carregarTotaLaTaulaEmpresa();
        simpleCursorEmpresa.changeCursor(cursor);
        simpleCursorEmpresa.notifyDataSetChanged();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ACTIVITY_TASK_ADD){
            if(resultCode == RESULT_OK){
                refreshLlistatEmpreses();
            }
        }
        if(requestCode == ACTIVITY_TASK_UPDATE){
            if(requestCode == RESULT_OK){
                refreshLlistatEmpreses();
            }
        }
    }
*/







/*
class adaperLlistaEmpresa extends android.widget.SimpleCursorAdapter {

    public adaperLlistaEmpresa(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
}

*/



}



