package com.example.irene.calendar_android.CreacioGrups;

import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleCursorAdapter;


public class Adapter extends SimpleCursorAdapter  {
    public Adapter(AppCompatActivity appCompatActivity, int row_grups, MatrixCursor mc, String[] from, int[] to, int i) {
        super(appCompatActivity, row_grups, mc, from, to);
    }

}


