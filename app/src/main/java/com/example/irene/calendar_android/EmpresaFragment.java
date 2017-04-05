package com.example.irene.calendar_android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class EmpresaFragment extends Fragment {


    public EmpresaFragment() {

        //Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_empresa);

        //Bundle b = getIntent().getExtras();

     //  AdaptadorLlistatEmpreses adaptador = new AdaptadorLlistatEmpreses(this, dades);



    }

}

class AdaptadorLlistatEmpreses extends ArrayAdapter<EmpresesLlista>{

    public AdaptadorLlistatEmpreses(Context context, EmpresesLlista[] dades) {
        super(context, R.layout.list_empreses, dades);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_empreses, null);

        TextView textNomEmpresa = (TextView)item.findViewById(R.id.txtNomEmpresa);
        textNomEmpresa.setText(dades[position].getNomEmpresa());

        TextView textNomCarrer = (TextView)item.findViewById(R.id.txtCarrer);
        textNomCarrer.setText(dades[position].getNomCarrer());

        return item;
    }

    private EmpresesLlista dades[] =
            new EmpresesLlista[]{
                    new EmpresesLlista("Empresa1", "Carrer1"),
                    new EmpresesLlista("Empresa2", "Carrer2"),
                    new EmpresesLlista("Empresa3", "Carrer3"),
            };

}











