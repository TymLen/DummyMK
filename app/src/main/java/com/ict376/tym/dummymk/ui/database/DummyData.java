package com.ict376.tym.dummymk.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DummyData extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Dummy.db";
    private static String TABLE_NAME = "SAVEGAME";
    private static String ID = "ID";
    private static String GREENMANA = "GREENMANA";
    private static String REDMANA = "REDMANA";
    private static String WHITEMANA = "WHITEMANA";
    private static String BLUEMANA = "BLUEMANA";
    private static String GREENCARD = "GREENCARD";
    private static String REDCARD = "REDCARD";
    private static String WHITECARD = "WHITECARD";
    private static String BLUECARD = "BLUECARD";
    private static String DAYS = "DAYS";
    private static String NIGHTS = "NIGHTS";
    private static String ROUND = "ROUND";
    private static String HERO = "HERO";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DummyData(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (" +
                ID+" INTEGER PRIMARY KEY,"+
                GREENMANA+ " INTEGER, "+
                REDMANA+ " INTEGER, "+
                WHITEMANA+" INTEGER, "+
                BLUEMANA+" INTEGER, "+
                GREENCARD+" INTEGER, "+
                REDCARD+" INTEGER, "+
                WHITECARD+"  INTEGER, "+
                BLUECARD+" INTEGER, "+
                DAYS+" INTEGER, "+
                NIGHTS+" INTEGER, "+
                ROUND+" INTEGER, "+
                HERO+" TEXT)"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    public int saveGame(int gMana, int rMana, int bMana, int wMana, int gCard, int rCard, int bCard, int wCard, int days, int nights, int round, String hero){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM "+TABLE_NAME, null);
        cursor.close();
        ContentValues values = new ContentValues();
        values.put(GREENMANA, gMana);
        values.put(REDMANA, rMana);
        values.put(BLUEMANA, bMana);
        values.put(WHITEMANA, wMana);
        values.put(GREENCARD, gCard);
        values.put(REDCARD, rCard);
        values.put(BLUECARD, bCard);
        values.put(WHITECARD, wCard);
        values.put(DAYS, days);
        values.put(NIGHTS, nights);
        values.put(ROUND, round);
        values.put(HERO, hero);
        try{
            db.insertOrThrow(TABLE_NAME, null, values);
            db.close();
            return 1;

        }catch(Exception e){
            db.close();
            return 0;
        }

    }
    public Cursor loadGame(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+ID+" DESC LIMIT 1";
        return db.rawQuery(query, null);
    }


}
