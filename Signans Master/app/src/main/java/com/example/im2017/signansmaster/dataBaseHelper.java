package com.example.im2017.signansmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 9/26/2018.
 */

public class dataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "signDB";
    public static final String TABLE_NAME = "signTable";
    public static final String COL_1 ="ID";
    public static final String COL_2 = "MODE";
    public static final String COL_3 = "LANGUAGE";

    public dataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,MODE TEXT,LANGUAGE TEXT)"));
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String mode, String language){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,mode);
        contentValues.put(COL_3,language);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean updateData(String id, String mode, String language){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,mode);
        contentValues.put(COL_3,language);
        db.update(TABLE_NAME,contentValues,"ID = ? ",new String[] { id } );
        return true;

    }
    public String selectData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        List<String> fileName = new ArrayList<>();
        if (cursor.moveToFirst()){
            fileName.add(cursor.getString(cursor.getColumnIndex(COL_1)));
            while(cursor.moveToNext()){
                fileName.add(cursor.getString(cursor.getColumnIndex(COL_1)));
            }
        }
        cursor.close();
        db.close();
        return null;
    }
}
