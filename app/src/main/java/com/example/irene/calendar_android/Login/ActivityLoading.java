package com.example.irene.calendar_android.Login;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.irene.calendar_android.Home.MainActivity;
import com.example.irene.calendar_android.R;
import com.example.irene.calendar_android.SQLite.CalendarDataSource;

import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityLoading extends AppCompatActivity {

//    private static final String ip = "calendar.darkaqua.net";
//    private static final int port = 8080;
    private static final String ip = "10.0.3.2";
    private static final int port = 22322;

    public static ApiConnector API_CONNECTOR;

    private CalendarDataSource dataSource;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        dataSource = new CalendarDataSource(this);

        try {
            ApiConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StartAnimations();
    }

    private boolean needsLogin = true;
    private boolean validatorResponse = false;

    private void ApiConnection() throws Exception{
        API_CONNECTOR = new ApiConnector(ip, port);
        //Valors sqlite
        String[] client = dataSource.getClient();
        String client_id = client[0];
        String client_token = client[1];
        //String client_id = "6eb3c233aed8d4db2e21bd72c769864c9dad06475c5b7fb8e8537b618b418b51";
        //String client_token = "48700148d6b34612264cbc940455a706c0888701f2f37aab0be9786c78fd4820";

        if(client_id == null && client_token == null) return;

        API_CONNECTOR.auth(client_id, client_token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id", client_id);
        jsonObject.put("client_token", client_token);

        API_CONNECTOR.POST("Account/Validator", jsonObject, new Request() {
            @Override
            public void Response(Object o) {
                JSONObject res = (JSONObject) o;
                try {
                    needsLogin = !res.getBoolean("valid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                validatorResponse = true;
            }
        });
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {

                    //Consulta a la API si SQLITE conte les dades de sessió
                    int waited = 0;
                    // Splash screen pause time
                    while (!validatorResponse || waited < 3000) {
                        sleep(150);
                        waited += 100;
                    }
                    //En cas de que la API respongui correctament, enviar directament a la pantalla d'usuari

                    //En cas contrari, enviar a la pantalla d'iniciar sessió
                    Intent intent = new Intent(ActivityLoading.this, needsLogin ? ActivityHome.class : MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    ActivityLoading.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    ActivityLoading.this.finish();
                }

            }
        };
        splashTread.start();

    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startMainMenu();
                finish();
            }
        }.start();

    }

    private void startMainMenu(){
        startActivity(new Intent(ActivityLoading.this, ActivityHome.class));
    }*/

}
