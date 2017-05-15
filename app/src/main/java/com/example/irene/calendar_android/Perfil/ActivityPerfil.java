package com.example.irene.calendar_android.Perfil;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irene.calendar_android.ConfiguracioUsuaris.ActivityConfiguracioUsuari;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.Login.ActivityRegistre;
import com.example.irene.calendar_android.R;


import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityPerfil extends AppCompatActivity implements View.OnClickListener {

    TextView nom, nomUsuari, uuid, telefon, ciutat, codiPostal, correu, dataRegistre, pais;
    ImageButton btnConfiguracio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

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

        nom =(TextView)findViewById(R.id.textViewPerfilNom);
        nomUsuari =(TextView)findViewById(R.id.textViewPerfilNomUsuari);
       // uuid =(TextView)findViewById(R.id.textViewPerfilNom);
        telefon =(TextView)findViewById(R.id.textViewPerfilTelefon);
        ciutat =(TextView)findViewById(R.id.textViewPerfilCiutat);
        codiPostal =(TextView)findViewById(R.id.textViewPerfilCodiPostal);
        correu =(TextView)findViewById(R.id.textViewPerfilCorreu);
        dataRegistre =(TextView)findViewById(R.id.textViewPerfilDataRegistre);
        pais =(TextView)findViewById(R.id.textViewPerfilPais);

        btnConfiguracio = (ImageButton)findViewById(R.id.imageButtonPerfilConfiguracioUser);
        btnConfiguracio.setOnClickListener(this);

        carregarDades();

    }

    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

    private void carregarDades(){
        final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;
        final Context context = this;
        final AppCompatActivity  appCompatActivity = this;

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_uuid", "96d211ac-a7dd-4301-983d-2b386c227a11");

            apiConnector.GET("User", jsonObject, new Request() {
                @Override
                public void Response(Object o) {
                    try{
                        JSONObject object = (JSONObject) o;
                        System.out.println(object.toString());

                        String name = object.getString("name");
                        nom.setText(name);
                        String username = object.getString("username");
                        nomUsuari.setText(username);
                        String telephone = object.getString("telephone");
                        telefon.setText(telephone);
                        String city = object.getString("city");
                        ciutat.setText(city);
                        String postal = object.getString("postal_code");
                        codiPostal.setText(postal);
                        String email = object.getString("email");
                        correu.setText(email);
                        String register = object.getString("register_timestamp");
                        dataRegistre.setText(register);
                        String country = object.getString("country");
                        pais.setText(country);



                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.imageButtonPerfilConfiguracioUser:
                Intent i = new Intent(ActivityPerfil.this, ActivityConfiguracioUsuari.class);
                startActivity(i);
                break;

            }

    }
}
