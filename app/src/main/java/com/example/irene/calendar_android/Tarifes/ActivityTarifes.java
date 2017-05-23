package com.example.irene.calendar_android.Tarifes;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.Login.ActivityHome;
import com.example.irene.calendar_android.Login.ActivityLoading;
import com.example.irene.calendar_android.R;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityTarifes extends AppCompatActivity  implements View.OnClickListener {
    FloatingActionButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifes);

        btnHome = (FloatingActionButton)findViewById(R.id.floatingActionButtonMain);
        btnHome.setOnClickListener(this);

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

        findViewById(R.id.tarifa_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarifa(1);
            }
        });

        findViewById(R.id.tarifa_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarifa(2);
            }
        });

        findViewById(R.id.tarifa_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarifa(3);
            }
        });
    }

    private void tarifa(int tarifa_option) {
        try {
            final ApiConnector apiConnector = ActivityLoading.API_CONNECTOR;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("payment_id", tarifa_option);

            final Context context = getApplicationContext();
            final AppCompatActivity appCompatActivity = this;

            apiConnector.POST("Account/Payment", jsonObject, new Request() {
                @Override
                public void Response(Object o) {
                    final JSONObject res = (JSONObject) o;
                    try {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Carregar ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.floatingActionButtonMain:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
