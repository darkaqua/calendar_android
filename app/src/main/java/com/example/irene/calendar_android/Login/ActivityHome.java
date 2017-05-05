package com.example.irene.calendar_android.Login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;

public class ActivityHome extends AppCompatActivity implements  View.OnClickListener {

    EditText nomUsuari, password;
    Button login, registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = (Button) findViewById(R.id.btnIniciar);
        login.setOnClickListener(this);
        registrar = (Button)findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
        nomUsuari = (EditText)findViewById(R.id.editTextUser);
        password = (EditText)findViewById(R.id.editTextPassword);
    }


    public void onClick(View v){
        Bundle b = new Bundle();
        int id = v.getId();

        switch (id){

            case R.id.btnIniciar:

               /* if (nomUsuari.getText().toString().equals("admin") && password.getText().toString().equals("123")) {
                   // Toast.makeText(getApplicationContext(), "Nom usuari i clau correcta", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ActivityHome.this, MainActivity.class);
                    startActivity(i);
                } else {
                    //Toast.makeText(getApplicationContext(), "Nom usuari o clau incorrecta", Toast.LENGTH_LONG).show();
                    if (!nomUsuari.getText().toString().equals("admin")){
                        nomUsuari.setError("Nom incorrecta");
                    }if (!password.getText().toString().equals("123")){
                        password.setError("Contrasenya incorrecta");
                    }
                }*/

                Intent i = new Intent(ActivityHome.this, MainActivity.class);
                startActivity(i);


            break;


            case R.id.btnRegistrar:
               Intent intent = new Intent(ActivityHome.this, ActivityRegistre.class);
                startActivity(intent);

            break;



        }


    }



 /*
*/







}
