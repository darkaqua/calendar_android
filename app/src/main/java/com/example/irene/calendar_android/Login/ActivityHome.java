package com.example.irene.calendar_android.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.SQLite.CalendarDataSource;
import com.example.irene.calendar_android.SQLite.CalendarOpenHelper;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityHome extends AppCompatActivity implements  View.OnClickListener {

    EditText nomUsuari, password;
    Button login, registrar;

    private CalendarDataSource calendarDataSource;

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

        calendarDataSource = new CalendarDataSource(this);
    }


    public void onClick(View v){
        Bundle b = new Bundle();
        int id = v.getId();

        switch (id){

            case R.id.btnIniciar:
                Intent i = new Intent(ActivityHome.this, MainActivity.class);
                startActivity(i);

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

             /*  try {
                   final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;

                   JSONObject jsonObject = new JSONObject();
                   jsonObject.put("email", nomUsuari.getText().toString());
                   jsonObject.put("password", password.getText().toString());

                   final Context context = getApplicationContext();
                   final AppCompatActivity appCompatActivity = this;

                   apiConnector.POST("Account/Login", jsonObject, new Request() {
                       @Override
                       public void Response(Object o) {
                           final JSONObject res = (JSONObject) o;
                           try {
                               if(res.getBoolean("valid")){
                                   Intent i = new Intent(ActivityHome.this, MainActivity.class);
                                   startActivity(i);
                                   String client_id = res.get("client_id").toString();
                                   String client_token = res.get("client_token").toString();
                                   calendarDataSource.setClient(client_id, client_token);
                                   apiConnector.auth(client_id, client_token);
                                   return;
                               }
                               appCompatActivity.runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       try {
                                           Toast.makeText(context, res.get("message").toString(), Toast.LENGTH_SHORT).show();
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               });
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }
                   });
               } catch (Exception e){
                   e.printStackTrace();
               }
*/



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
