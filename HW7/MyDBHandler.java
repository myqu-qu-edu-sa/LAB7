package com.example.sulimanlab_7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "info.db";
    public static final String TABLE_NAME = "tblFreinds";
    public static final String COLUMN_RECID = "recID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";

    public MyDBHandler (MainActivity context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String sqlStmt = " CREATE TABLE " + TABLE_NAME+ " (" +
                COLUMN_RECID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_PHONE + " TEXT);" ;

        Log.d("DB", "Created");
        db.execSQL(sqlStmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Log.d("DB","Tha table has been dropped");
        onCreate(db);
    }

    public void addRecord ( String nameStr, String phoneStr){

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("insert into "+ TABLE_NAME + "("+ COLUMN_NAME + "," +
                COLUMN_PHONE + ") VALUES (?,?)", new String [] {nameStr, phoneStr});
        //db.close();
    }

    public String dataBaseToString()
    {
        String dbData = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor c = db.rawQuery(query,null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            dbData += c.getString(c.getColumnIndex(COLUMN_RECID));
            dbData += " | "+ c.getString(c.getColumnIndex(COLUMN_NAME));
            dbData += " | "+ c.getString(c.getColumnIndex(COLUMN_PHONE));
            dbData += "\n";
            c.moveToNext();
        }

        //db.close();
        return dbData;
    }

    public void updateRecord(String id,String name,String phone){
        //Left as HW
        SQLiteDatabase db= getWritableDatabase();
        ContentValues coValues=new ContentValues();
        coValues.put(COLUMN_RECID,id);
        coValues.put(COLUMN_NAME,name);
        coValues.put(COLUMN_PHONE,phone);
        db.update(TABLE_NAME,coValues,"recID= ?",new String[] {id});
    }
}