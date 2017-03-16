package com.example.irene.calendar_android.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.ActivityNavigationDrawer;
import com.example.irene.calendar_android.R;

public class ActivityHome extends AppCompatActivity {

    EditText nomUsuari, password;
    Button login, registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btnIniciar);
        registrar = (Button)findViewById(R.id.btnRegistrar);
        nomUsuari = (EditText)findViewById(R.id.editTextUser);
        password = (EditText)findViewById(R.id.editTextPassword);


        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (nomUsuari.getText().toString().equals("admin") && password.getText().toString().equals("123")) {
                    Toast.makeText(getApplicationContext(), "Nom usuari i clau correcta", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ActivityHome.this, ActivityNavigationDrawer.class);
                    startActivity(i);
                } else {
                    //Toast.makeText(getApplicationContext(), "Nom usuari o clau incorrecta", Toast.LENGTH_LONG).show();
                    if (!nomUsuari.getText().toString().equals("admin")){
                        nomUsuari.setError("Nom incorrecta");
                    }if (!password.getText().toString().equals("123")){
                        password.setError("Contrasenya incorrecta");
                    }
                }
            }
        });


    }


 /*
*/







}
