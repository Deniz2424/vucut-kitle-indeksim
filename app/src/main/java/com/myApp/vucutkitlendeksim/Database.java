package com.myApp.vucutkitlendeksim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.LinkedList;


public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "indeksim";
    private static final String TABLE_NAME = "indeksdegerim";
    private static final int VERSION = 1;
    private static final String Vki_deger = "vki";
    private static final String Tarih="tarih ";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + Vki_deger + " TEXT,"+ Tarih+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void veriekle(String Vki,String tarih) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Vki_deger, Vki.trim());
        cv.put(Tarih,tarih.trim());
        long r = db.insert(TABLE_NAME, null, cv);
        if (r > -1)
            Log.i("tag", "İşlem Başarılı");
        else
            Log.e("tag", "İşlem Başarısız");
        db.close();
    }

    public void verisil(String Vki) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, Vki_deger + "=?", new String[]{String.valueOf(Vki)});
        db.close();
    }

    public LinkedList<String> veriListeleri() {
        LinkedList<String> veriler = new LinkedList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {Vki_deger,Tarih};
        Cursor cr = db.query(TABLE_NAME, sutunlar, null, null, null, null, null);
        while (cr.moveToNext()) {
            veriler.add(cr.getString(0));
        }
        return veriler;
    }
}