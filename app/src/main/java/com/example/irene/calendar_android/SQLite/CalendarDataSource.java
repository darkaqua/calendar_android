package com.example.irene.calendar_android.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.irene.calendar_android.Fragments.EmpresaFragment;

import static com.example.irene.calendar_android.SQLite.CalendarOpenHelper.EMPRESA_ID;
import static com.example.irene.calendar_android.SQLite.CalendarOpenHelper.TAULA_EMPRESA;


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

    /*

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
*/
    //=====================Events====================
    public Cursor carregaTotaLaTaula() {

        return bdLlegir.query(bdOpenHelper.TAULA_EVENTS, new String[]{
                        bdOpenHelper.EVENTS_ID,
                        bdOpenHelper.EVENTS_NOM,
                        bdOpenHelper.EVENTS_DESCRIPCIO,
                        bdOpenHelper.EVENTS_HORA_EVENTS,
                        bdOpenHelper.EVENTS_DIA_EVENTS
                },
                null, null,
                null, null, bdOpenHelper.EVENTS_ID);
    }

    public Cursor carregarPerId(long id) {
        // Retorna un cursor només amb el ID indicat
        return bdLlegir.query(bdOpenHelper.TAULA_EVENTS, new String[]{
                        bdOpenHelper.EVENTS_ID,
                        bdOpenHelper.EVENTS_NOM,
                        bdOpenHelper.EVENTS_DESCRIPCIO,
                        bdOpenHelper.EVENTS_HORA_EVENTS,
                        bdOpenHelper.EVENTS_DIA_EVENTS
                },
                bdOpenHelper.EVENTS_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

    }

    public Cursor nomRepetit(String nom) {
        return bdLlegir.query(bdOpenHelper.TAULA_EVENTS, new String[]{
                        bdOpenHelper.EVENTS_ID,
                        bdOpenHelper.EVENTS_NOM,
                        bdOpenHelper.EVENTS_DESCRIPCIO,
                        bdOpenHelper.EVENTS_HORA_EVENTS,
                },
                bdOpenHelper.EVENTS_NOM + "=?", new String[]{String.valueOf(nom)},
                null, null, null);
    }

    public long AfegirEvent(String nom, String descripcio, int hora, int dia) {

        ContentValues valors = new ContentValues();

        valors.put(bdOpenHelper.EVENTS_NOM, nom);
        valors.put(bdOpenHelper.EVENTS_DESCRIPCIO, descripcio);
        valors.put(bdOpenHelper.EVENTS_HORA_EVENTS, hora);
        valors.put(bdOpenHelper.EVENTS_DIA_EVENTS, dia);

        return bdEscriure.insert(bdOpenHelper.TAULA_EVENTS, null, valors);
    }

    /*public void ModificarEvent(long id, String descripcio, double pvp , int stock) {
        // Modifiquem els valors de las tasca amb clau primària "id"
        ContentValues valors = new ContentValues();
        valors.put(bdOpenHelper.PRODUCTES_DESCRIPCIO, descripcio);
        valors.put(bdOpenHelper.PRODUCTES_PVP, pvp);
        valors.put(bdOpenHelper.PRODUCTES_ESTOC, stock);

        bdEscriure.update(bdOpenHelper.TAULA_PRODUCTES,valors, bdOpenHelper.PRODUCTES_ID + " = ?", new String[] {
                String.valueOf(id) });
    }*/
    //metode per eliminar una row atraves de la seva id=PK
    public void ElminarEvent(long id) {
        // Eliminem la task amb clau primària "id"
        bdEscriure.delete(bdOpenHelper.TAULA_EVENTS, bdOpenHelper.EVENTS_ID + " = ?", new String[]{
                String.valueOf(id)});
    }

    //===========EMPRESA==============


    public Cursor carregarTotaLaTaulaEmpresa() {

        return bdLlegir.query(bdOpenHelper.TAULA_EMPRESA, new String[]{
                bdOpenHelper.EMPRESA_ID,
                bdOpenHelper.EMPRESA_NOM,
                bdOpenHelper.EMPRESA_ADREÇA,
                bdOpenHelper.EMPRESA_CIUTAT,
                bdOpenHelper.EMPRESA_CODIPOSTAL,
                bdOpenHelper.EMPRESA_EMAIL,
                bdOpenHelper.EMPRESA_TELEFON
        },
                null, null,
                null, null, EMPRESA_ID);
    }

    public Cursor carregarPerIDEmpresa(long id) {

        return bdLlegir.query(TAULA_EMPRESA, new String[]{
                        bdOpenHelper.EMPRESA_ID,
                        bdOpenHelper.EMPRESA_NOM,
                        bdOpenHelper.EMPRESA_ADREÇA,
                        bdOpenHelper.EMPRESA_CIUTAT,
                        bdOpenHelper.EMPRESA_CODIPOSTAL,
                        bdOpenHelper.EMPRESA_EMAIL,
                        bdOpenHelper.EMPRESA_TELEFON
                },
                EMPRESA_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    public long AfegirEmpreses(String nom, String adreça, String ciutat, int codiPostal, int telefon, String email){
        ContentValues valors =new ContentValues();

        valors.put(bdOpenHelper.EMPRESA_NOM, nom);
        valors.put(bdOpenHelper.EMPRESA_ADREÇA, adreça);
        valors.put(bdOpenHelper.EMPRESA_CIUTAT, ciutat);
        valors.put(bdOpenHelper.EMPRESA_CODIPOSTAL, codiPostal);
        valors.put(bdOpenHelper.EMPRESA_TELEFON, telefon);
        valors.put(bdOpenHelper.EMPRESA_EMAIL, email);

        return bdEscriure.insert(TAULA_EMPRESA,null,valors);
    }




}


