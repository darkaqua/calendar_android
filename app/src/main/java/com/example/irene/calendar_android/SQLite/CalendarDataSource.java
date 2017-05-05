package com.example.irene.calendar_android.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.irene.calendar_android.Fragments.EmpresaFragment;




public class CalendarDataSource {

    //Creem bdLlegir i bdEscriure per llegir i modificar la nostra BD
    private SQLiteDatabase bdLlegir, bdEscriure;
    //PEr fer la comunicació entre la BD
    private CalendarOpenHelper bdOpenHelper;
    private Cursor cursor;


    //Constructor
    public CalendarDataSource(Context context) {

        bdOpenHelper = new CalendarOpenHelper(context);
        bdLlegir = bdOpenHelper.getReadableDatabase();
        bdEscriure = bdOpenHelper.getWritableDatabase();
    }



    //Iniciem la BD
    public void obrir() {
        bdLlegir = bdOpenHelper.getReadableDatabase();
        bdEscriure = bdOpenHelper.getWritableDatabase();
    }

    @Override
    protected void finalize() {
        bdLlegir.close();
        bdEscriure.close();
    }





}


