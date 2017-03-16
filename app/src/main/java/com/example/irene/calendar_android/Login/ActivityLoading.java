package com.example.irene.calendar_android.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.irene.calendar_android.R;

public class ActivityLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
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
    }

}
