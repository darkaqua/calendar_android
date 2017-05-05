package com.example.irene.calendar_android.ConfiguracioUsuaris;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityRegistre;
import com.example.irene.calendar_android.R;

public class ActivityConfiguracioUsuari extends AppCompatActivity implements View.OnClickListener  {

    EditText nomUsuari, cognom, telefon, ciutat, email;
    Button  guardarCanvis, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio_usuari);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cancelar = (Button) findViewById(R.id.btnConfiguracioCancelar);
        cancelar.setOnClickListener(this);
    }

    public void onClick(View v){
        Bundle b = new Bundle();
        int id = v.getId();

        switch (id){

            case R.id.btnConfiguracioCancelar:
                Intent i = new Intent(ActivityConfiguracioUsuari.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Cancel·lar canvis", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }
}
