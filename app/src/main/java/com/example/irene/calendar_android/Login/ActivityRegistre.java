package com.example.irene.calendar_android.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.irene.calendar_android.R;

public class ActivityRegistre extends AppCompatActivity implements View.OnClickListener {

    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        registrar = (Button)findViewById(R.id.btnRegistrarme);
    }


    @Override
    public void onClick(View v) {
        Bundle b = new Bundle();
        int id = v.getId();

        switch (id){
            case R.id.btnRegistrarme:

                //Comprovacions dels camps un cop es puguin fer les trucades a la api
                Intent i = new Intent(ActivityRegistre.this, ActivityHome.class);
                startActivity(i);
              //  Toast.makeText(this, "Registre correcte", Toast.LENGTH_SHORT).show();

                break;


        }
    }
}
