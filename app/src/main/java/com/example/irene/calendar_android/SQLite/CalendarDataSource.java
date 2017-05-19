package com.example.irene.calendar_android.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.irene.calendar_android.Fragments.EmpresaFragment;

import static com.example.irene.calendar_android.SQLite.CalendarOpenHelper.TAULA_API;


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


    public String[] getClient(){
        String[] values = new String[2];

        Cursor cursor = bdLlegir.query(
                TAULA_API,
                new String[]{CalendarOpenHelper._ID, CalendarOpenHelper.CLIENT_ID, CalendarOpenHelper.CLIENT_TOKEN},
                CalendarOpenHelper._ID + "=1",
                null, null, null, null
        );
        if(cursor.moveToFirst()){
            values[0] = cursor.getString(cursor.getColumnIndex(CalendarOpenHelper.CLIENT_ID));
            values[1] = cursor.getString(cursor.getColumnIndex(CalendarOpenHelper.CLIENT_TOKEN));
        }
        cursor.close();
        return values;
    }

    public long setClient(String client_id, String client_token){
        ContentValues contentValues = new ContentValues();

        contentValues.put(CalendarOpenHelper._ID, "1");
        contentValues.put(CalendarOpenHelper.CLIENT_ID, client_id);
        contentValues.put(CalendarOpenHelper.CLIENT_TOKEN, client_token);


        if(getClient()[0] != null){
            return bdEscriure.update(TAULA_API, contentValues, CalendarOpenHelper._ID+"=1", null);
        }
        return bdEscriure.insert(TAULA_API, null, contentValues);
    }

    public Cursor getEmpreses(long id) {


        Cursor cursor = bdLlegir.rawQuery("Select * from "+ TAULA_API +" where _id = "+id,null);


        return cursor;
    }





}


