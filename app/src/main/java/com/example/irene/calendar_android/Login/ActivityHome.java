package com.example.irene.calendar_android.Login;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.ActivityNavigationDrawer;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.RegistreFragment;

public class ActivityHome extends AppCompatActivity implements  View.OnClickListener, RegistreFragment.OnFragmentInteractionListener {

    EditText nomUsuari, password;
    Button login, registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btnIniciar);
        login.setOnClickListener(this);
        registrar = (Button)findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
        nomUsuari = (EditText)findViewById(R.id.editTextUser);
        password = (EditText)findViewById(R.id.editTextPassword);


       /* Bundle arguments = new Bundle();
        arguments.putString("id", );
        RegistreFragment fragment = RegistreFragment.newInstance(arguments);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, fragment, RegistreFragment.TAG);
        ft.commit();*/

        /*login.setOnClickListener(new View.OnClickListener(){

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
        });*/


    }


    public void onClick(View v){
        Bundle b = new Bundle();
        int id = v.getId();





        switch (id){

            case R.id.btnIniciar:

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


            break;


            case R.id.btnRegistrar:
               Intent i = new Intent(ActivityHome.this, ActivityRegistre.class);
                startActivity(i);

            break;



        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


 /*
*/







}
