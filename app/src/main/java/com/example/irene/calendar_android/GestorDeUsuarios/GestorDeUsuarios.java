package com.example.irene.calendar_android.GestorDeUsuarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityRegistre;
import com.example.irene.calendar_android.R;

public class GestorDeUsuarios extends AppCompatActivity implements  View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_de_usuarios);

        Button aceptarRegistro = (Button) findViewById(R.id.btnRegistrarme);
        aceptarRegistro.setOnClickListener(this);

    }

    @Override



    public void onClick(View v) {
        Bundle b = new Bundle();
        int id = v.getId();

        switch (id){
            case R.id.btnRegistrarme:

                //Envia a un altre pagina
                Intent i = new Intent(GestorDeUsuarios.this, AfegirUsuari.class);
                startActivity(i);
                Toast.makeText(this, "Afageix l'usuari al Grup", Toast.LENGTH_SHORT).show();

                break;


        }
    }
}
