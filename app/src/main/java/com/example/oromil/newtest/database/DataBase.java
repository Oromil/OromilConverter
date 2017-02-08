package com.example.oromil.newtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oromil on 29.09.2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public final static int DB_VERSION = 1;
    public final static String DB_NAME = "courses";
    public static final String KEY_ID = "id";
    public final static String TABLE_NAME = "valutes";
    public final static String KEY_VALUTE = "name";
    public final static String KEY_COURSE = "course";
    private SQLiteDatabase db;
    Cursor c;

    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VALUTE + " TEXT,"
                + KEY_COURSE + " REAL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB() {
        db = this.getWritableDatabase();
    }

    public void clearDB(){db.delete(TABLE_NAME, null, null);}

    public void closeDB() {
        db.close();
    }

    public void insertItem(ContentValues values) {
        db.insert(TABLE_NAME, null, values);
    }

    public Cursor get(){
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
    return c;}
}
