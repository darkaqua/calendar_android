package com.example.irene.calendar_android.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.irene.calendar_android.CompanyCreation.Creacio_Companyia;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;

import java.util.ArrayList;


public class EmpresaFragment extends Fragment{

    //Definicions dels elements del fragment
    Button btnAceptarCrearEmpresa;

    //SQlite simular en la creacció


    //Contructor
    public EmpresaFragment() {

        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_company_list, container, false);
        // Inflate the layout for this fragment

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


}











