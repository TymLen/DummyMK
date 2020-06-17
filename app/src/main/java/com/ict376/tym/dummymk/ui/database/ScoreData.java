package com.ict376.tym.dummymk.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.DateFormat;
import java.util.Date;

public class ScoreData extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Score.db";
    private static String TABLE_NAME = "HIGHSCORES";
    private static String ID = "ID";
    private static String DATE = "DATE";
    private static String HERO = "HERO";
    private static String SCORE = "SCORE";
    private static String SCENARIO = "SCENARIO";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+TABLE_NAME;

    public ScoreData(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+
                ID+" INTEGER PRIMARY KEY, "+
                DATE+" DATE, "+
                HERO+" TEXT, "+
                SCORE+" INTEGER, "+
                SCENARIO+" TEXT)"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    public int saveScore(String inHero, int inScore, String inScenario){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        DateFormat dform = DateFormat.getDateInstance();
        String date = dform.format(new Date());
        values.put(DATE, date);
        values.put(HERO, inHero);
        values.put(SCORE, inScore);
        values.put(SCENARIO, inScenario);
        try{
            db.insert(TABLE_NAME, null, values);
            db.close();
            return 1;
        }catch(Exception e){
            db.close();
            return 0;
        }
    }
    public Cursor getAllScores(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+SCORE+ " DESC";
        return db.rawQuery(query, null);
    }
    public Cursor getScenarioScores(String selection){
        SQLiteDatabase db = getReadableDatabase();
        String[] dbSel = new String[]{selection};
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+SCENARIO+" = ? ORDER BY "+SCORE+" DESC";
        return db.rawQuery(query, dbSel);
    }
    public int deleteEntry(String inDate, String inHero, String inScore, String inScenario){
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.delete(TABLE_NAME, DATE + "=? and " + HERO + "=? and " + SCORE + "=? and " + SCENARIO + "=?", new String[]{inDate, inHero, inScore, inScenario});
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
