package com.example.irene.calendar_android.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.irene.calendar_android.CompanieMenu.Creacio_Companyia;
import com.example.irene.calendar_android.CompanyCreation.CreacioCompanyia;
import com.example.irene.calendar_android.R;


public class EmpresaFragment extends Fragment  {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_list, container, false);

        //Boto per agregar els empreses
        Button crearEmpreses;
        crearEmpreses = (Button) view.findViewById(R.id.btnCrearEmpresesList);
        crearEmpreses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Creacio_Companyia.class);
                startActivity(i);
            }
        });
        return view;
    }
}



