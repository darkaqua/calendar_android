package com.example.irene.calendar_android.CreacioGrups;


import android.app.ListActivity;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleCursorAdapter;

public class AdaptadorGrups  extends SimpleCursorAdapter {

    public AdaptadorGrups(ListActivity appCompatActivity, int row_grup, MatrixCursor mc, String[] from, int[] to, int i) {
        super(appCompatActivity, row_grup, mc, from, to);
    }



}
