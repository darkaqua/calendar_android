package com.example.irene.calendar_android.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CalendarOpenHelper extends SQLiteOpenHelper{


    //CAmps de la BD EVENTS
    public static final String TAULA_EVENTS = "events";
    public static final String EVENTS_ID = "_id";
    public static final String EVENTS_NOM = "nomEvent";
    public static final String EVENTS_DESCRIPCIO = "descripcio";
    public static final String EVENTS_HORA_EVENTS = "hora";
    public static final String EVENTS_DIA_EVENTS= "dia";


    //Camps de la BD Empresa
    public static final String TAULA_EMPRESA = "empresa";
    public static final String EMPRESA_ID = "_id";
    public static final String EMPRESA_NOM = "nomEmpresa";
    public static final String EMPRESA_TELEFON = "telefon";
    public static final String EMPRESA_EMAIL = "email";
    public static final String EMPRESA_CIUTAT= "ciutat";
    public static final String EMPRESA_ADREÇA= "adreça";
    public static final String EMPRESA_CODIPOSTAL= "codiPostal";

    //Nom de la nostre BD
    private static final String NOM_BD = "Calendari";
    private static final int VERSIO_BD = 1;

    //Creacio de la base de dades d'events
    private static final String CREAR_BD_EVENTS = "create table "
            + TAULA_EVENTS + "( " +
            EVENTS_ID+ " integer primary key autoincrement, " +
            EVENTS_NOM + " text not null, " +
            EVENTS_DESCRIPCIO + " text not null, " +
            EVENTS_HORA_EVENTS + " integer not null, " +
            EVENTS_DIA_EVENTS + " integer not null);";

    //Creació de la base de dades empresa
    private static final String CREAR_BD_EMPRESA = "create table "
            + TAULA_EMPRESA + "( " +
            EMPRESA_ID+ " integer primary key autoincrement, " +
            EMPRESA_NOM + " text not null, " +
            EMPRESA_TELEFON + " integer not null, " +
            EMPRESA_EMAIL + " text not null, " +
            EMPRESA_ADREÇA + " text not null, " +
            EMPRESA_CODIPOSTAL + " integer not null, " +
            EMPRESA_CIUTAT + " text not null);";


    public CalendarOpenHelper(Context context) {
        super(context, NOM_BD, null, VERSIO_BD);

    }


    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREAR_BD_EMPRESA);

    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS articulos");
        this.onCreate(database);
    }



}