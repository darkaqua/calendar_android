package com.example.irene.calendar_android.CreacioEvent;


import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleCursorAdapter;

public class AdaptadorEvents extends SimpleCursorAdapter {

    public AdaptadorEvents(ListActivity appCompatActivity, int row_events, MatrixCursor mc, String[] from, int[] to, int i) {
        super(appCompatActivity, row_events, mc, from, to);
    }

}
