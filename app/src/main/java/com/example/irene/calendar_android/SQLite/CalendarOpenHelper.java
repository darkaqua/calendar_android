package com.example.irene.calendar_android.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Irene on 19/04/2017.
 */

public class CalendarOpenHelper extends SQLiteOpenHelper {

/*
Eveents --> titol, descipcio, dia hora, temps min, assignarPErsones
 */
    //Nom de la nostre BD

    private static final String NOM_BD = "Usuaris";
    private static final int VERSIO_BD = 1;


    //CAmps de la BB
    public static final String TAULA_USUARIS = "taula_usuaris";
    public static final String COLUMNE_ID = "_id";
    public static final String COLUMNE_ID_USUARIS = "idUsuaris";
    public static final String COLUMNE_TOKEN = "token";

    //Crear BD de usuaris
    private static final String CREAR_BD ="create table "
            +TAULA_USUARIS + " ( " +
            COLUMNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNE_ID_USUARIS + " TEXT NOT NULL, " +
            COLUMNE_TOKEN + " TEXT NOT NULL);";

    public CalendarOpenHelper(Context context) {
        super(context, NOM_BD, null, VERSIO_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREAR_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS articulos");
        this.onCreate(database);
    }

}