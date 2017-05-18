package com.example.irene.calendar_android.CreacioGrups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.irene.calendar_android.R;

public class ActivityMostrarInfoGrup extends AppCompatActivity {

    private String company_uuid, group_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_grup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        });

        company_uuid = getIntent().getExtras().getString("company_uuid");
        group_id = getIntent().getExtras().getString("id");
    }


    


}
