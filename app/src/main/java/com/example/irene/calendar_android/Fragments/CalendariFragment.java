package com.example.irene.calendar_android.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.irene.calendar_android.Companyies.ActivityLlistatEmpreses;
import com.example.irene.calendar_android.CreacioEvent.Creacio_Events;
import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.SQLite.CalendarDataSource;
import com.example.irene.calendar_android.SQLite.CalendarOpenHelper;


public class CalendariFragment extends Fragment {

    ImageView events, empreses, grups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendari, container, false);

        events = (ImageView)view.findViewById(R.id.imageViewLlistatEvents);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Intent i = new Intent(getActivity())
            }
        });

        empreses = (ImageView)view.findViewById(R.id.imageViewLlistatEmpreses);
        empreses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ActivityLlistatEmpreses.class);
                startActivity(i);
            }
        });

        grups = (ImageView)view.findViewById(R.id.imageViewLlistatGrups);
        grups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Intent i = new Intent(getActivity(), .class);
                startActivity(i);*/
            }
        });

        //Boto per agregar els events
        FloatingActionButton fAgregarEvent;
//        fAgregarEvent = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAgregarEvent);
//        fAgregarEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), Creacio_Events.class);
//                startActivity(i);
//            }
//        });



        return view;
    }





}

