package com.myApp.vucutkitlendeksim;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "INDEKSIM";
    private static final String TABLE_NAME = "INDEKSDEGERIM";
    private static final int VERSION = 1;
    private static final String VKIDEGER ="VKI";
    private static final String TARIH ="TAKVIM";
    
    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + VKIDEGER + " TEXT,"+TARIH+" TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void veriekle(String Vki, String tarih)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VKIDEGER,Vki.trim());
        cv.put(TARIH,tarih.trim());
        long r = db.insert(TABLE_NAME,null,cv);
        if (r > -1)
            Log.i("tag", "İşlem Başarılı");
        else
            Log.e("tag", "İşlem Başarısız");
        db.close();
    }

    public void verisil(String Vki) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, VKIDEGER+"=? ",new String[]{String.valueOf(Vki)});
        db.close();
    }

    public List<String> veriListeleri() {
        List<String> veriler = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {VKIDEGER,TARIH};
        @SuppressLint("Recycle") Cursor cr = db.query(TABLE_NAME, sutunlar, null, null, null, null, null);
        while (cr.moveToNext()) {
            veriler.add(cr.getString(0) +"            "+ cr.getString(1) );
        }
        return veriler;
    }
}
