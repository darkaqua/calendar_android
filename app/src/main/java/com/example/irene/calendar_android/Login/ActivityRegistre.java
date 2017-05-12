package com.example.irene.calendar_android.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.SQLite.CalendarDataSource;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityRegistre extends AppCompatActivity implements View.OnClickListener {

    Button registrar, btn;
    EditText nameUser, username, email, re_email, password, re_password, telephone, city, postalCode, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        registrar = (Button)findViewById(R.id.btnRegistrarme);
        registrar.setOnClickListener(this);

        nameUser = (EditText)findViewById(R.id.etName);
        username = (EditText)findViewById(R.id.etUserName);
        email = (EditText)findViewById(R.id.etEmail);
        re_email = (EditText)findViewById(R.id.etReEmail);
        password = (EditText)findViewById(R.id.etPassword);
        re_password = (EditText)findViewById(R.id.etRePassword);
        telephone = (EditText)findViewById(R.id.etTelephone);
        city = (EditText)findViewById(R.id.etCity);
        postalCode = (EditText)findViewById(R.id.etPostalCode);
        country = (EditText)findViewById(R.id.etCountry);
    }


    @Override
    public void onClick(View v) {
        Bundle b = new Bundle();
        int id = v.getId();

        switch (id) {
            case R.id.btnRegistrarme:
                //Comprovacions dels camps un cop es puguin fer les trucades a la api
               // Intent i = new Intent(ActivityRegistre.this, MainActivity.class);
               // startActivity(i);
                //  Toast.makeText(this, "Registre correcte", Toast.LENGTH_SHORT).show();
              //  Toast.makeText(ActivityRegistre.this, "Registre creat", Toast.LENGTH_LONG).show();
               try {
                    final ApiConnector apiConnector  = ActivityLoading.API_CONNECTOR;

                    final AppCompatActivity appCompatActivity = this;
                    final Context context = getApplicationContext();

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", nameUser.getText().toString());
                    jsonObject.put("username", username.getText().toString());
                    jsonObject.put("email", email.getText().toString());
                    jsonObject.put("re_email", re_email.getText().toString());
                    jsonObject.put("password", password.getText().toString());
                    jsonObject.put("re_password", re_password.getText().toString());
                    jsonObject.put("telephone", telephone.getText().toString());
                    jsonObject.put("city", city.getText().toString());
                    jsonObject.put("postal_code", postalCode.getText().toString());
                    jsonObject.put("country", "ES");


                    apiConnector.POST("Account/Register", jsonObject, new Request() {
                        @Override
                        public void Response(Object o) {
                            final JSONObject res = (JSONObject) o;

                            try{
                                if(res.getBoolean("valid")){
                                    Toast.makeText(ActivityRegistre.this, "Registre creat", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(ActivityRegistre.this, ActivityHome.class);
                                    startActivity(i);


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

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

        }
    }
}
