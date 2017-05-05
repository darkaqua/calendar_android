package com.example.irene.calendar_android.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CalendarOpenHelper extends SQLiteOpenHelper {


    public static final String TAULA_API = "api";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_TOKEN = "client_token";

    private static final String NOM_BD = "Calendari";
    private static final int VERSIO_BD = 1;

    private static final String CREAR_BD = "create table "
            + TAULA_API + "( " +
            CLIENT_ID + " text not null, " +
            CLIENT_TOKEN + " text not null);";


    public CalendarOpenHelper(Context context) {
        super(context, NOM_BD, null, VERSIO_BD);
    }

    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREAR_BD);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS articulos");
        this.onCreate(database);
    }
}