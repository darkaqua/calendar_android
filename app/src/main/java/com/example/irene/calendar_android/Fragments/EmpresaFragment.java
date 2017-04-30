package com.example.irene.calendar_android.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.irene.calendar_android.CompanyCreation.Creacio_Companyia;
import com.example.irene.calendar_android.EmpresesLlista;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class EmpresaFragment extends Fragment{

    //Definicions dels elements del fragment
    Button btnAceptarCrearEmpresa;

    //Contructor
    public EmpresaFragment() {

        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_company_list, container, false);

        /*AdaptadorEmpreses adaptadorEmpreses = new AdaptadorEmpreses(this, dadesExempleEmpresa);
        ListView llistat = (ListView)findViewById(R.id.lvCompanyList);
        llistat.setAdapter(adaptadorEmpreses);*/

        //Boto per la creació de les empresa on et enviarà a un altre fragment
        btnAceptarCrearEmpresa = (Button) v.findViewById(R.id.button);
        btnAceptarCrearEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crida d'un activity des de un Fragment.
                Intent intent = new Intent(getActivity(), Creacio_Companyia.class);
                getActivity().startActivity(intent);
            }
        });


        return v;
    }


//Adaptardor per el llistat de les empreses on serveix per simular diferents empreses ja creades
/*
class AdaptadorEmpreses extends ArrayAdapter<EmpresesLlista>{
    /*
        Cridem a una classe anomenada EmpresesLlista on solament hi ha les seves propietats(NOM EMPRESA, CARRER),
        més li afegom el layout per visualitzar el contingut de cada fila.
    */

/*

    public AdaptadorEmpreses(Context context,  EmpresesLlista[] dadesExempleEmpresa) {
        super(context, R.layout.list_empreses);
    }

    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_empreses, null);

        //Declaració de cada element que hi ha en la fila
        //Nom empresa
        TextView lblNomEmpresa = (TextView) item.findViewById(R.id.txtNomEmpresa);
        lblNomEmpresa.setText(dadesExempleEmpresa[position].getNomEmpresa());
        //Nom carrer
        TextView lblNomCarrer = (TextView) item.findViewById(R.id.txtCarrer);
        lblNomCarrer.setText(dadesExempleEmpresa[position].getNomCarrer());
        //Imatge
        ImageView foto = (ImageView)item.findViewById(R.id.imageViewEmpresa);
        switch (dadesExempleEmpresa[position].getImatge())
        {
            case "logoDarkAqua": foto.setImageDrawable(getContext().getResources().getDrawable(R.drawable.darkaqua));
                break;
            case "logoApp": foto.setImageDrawable(getContext().getResources().getDrawable(R.drawable.calendar_icon));
                break;
        }
        return (item);
    }*/
}
/*
    private EmpresesLlista dadesExempleEmpresa[] =
            new EmpresesLlista[]{
                    new EmpresesLlista("Empresa 1", "Carrer 1", ""),
                    new EmpresesLlista("Empresa 2", "Carrer 2", ""),
                    new EmpresesLlista("Empresa 3", "Carrer 3", ""),
                    new EmpresesLlista("Empresa 4", "Carrer 4", ""),
                    new EmpresesLlista("Empresa 5", "Carrer 5", ""),



            };
*/
/*
     int [] imatges = {
             R.drawable.darkaqua,
             R.drawable.calendar_icon
     };
*/








