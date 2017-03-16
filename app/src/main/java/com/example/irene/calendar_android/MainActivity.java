package com.example.irene.calendar_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnIniciar = (Button) findViewById(R.id.btnIniciar);
        Button btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnIniciar.setOnClickListener((View.OnClickListener) this);
        btnRegistrar.setOnClickListener((View.OnClickListener) this);



    }
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnIniciar:
                //startActivity( new Intent(MainActivity.this, ));

                break;
            case R.id.btnRegistrar:

                //startActivity( new Intent(MainActivity.this, ));
                break;
        }


    }






}
