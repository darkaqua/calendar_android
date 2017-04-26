package com.example.irene.calendar_android.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Irene on 26/04/2017.
 */

public class CalendarDataSource {

    //Creem basedadesRead i basedadesWrite per llegir i modificar la BD
    private SQLiteDatabase bdRead, bdWrite;

    //Fer la comunicació entre la BD
    private CalendarDataSource bdOpenHelper;
    private Cursor cursor;

    //Constructor
    public CalendarDataSource(Context context){

        bdOpenHelper = new CalendarDataSource(context);
        //bdRead = bdOpenHelper.getReadableDatabase();
        //bdWrite = bdOpenHelper.getWritableDatabase();
    }

    /*public Cursor CarregarTotaLaTaula(){
        return bdRead.query(bdOpenHelper.T)
    }*/

  /*  public Cursor CarregarTotaLaTaula(){
        return bdRead.query(bdOpenHelper.TAULA_USUARIS, new String []{
                bdOpenHelper.
        })
    }*/


}


