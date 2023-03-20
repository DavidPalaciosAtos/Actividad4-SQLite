package com.example.actividad4_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context ) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(dni TEXT primary key, name TEXT,apellidos TEXT, edad TEXT, direccion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertuserdata(String dni, String name, String apellidos, String edad, String direccion){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dni", dni);
        contentValues.put("name", name);
        contentValues.put("apellidos", apellidos);
        contentValues.put("edad", edad);
        contentValues.put("direccion", direccion);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result== -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateuserdata(String dni, String name, String apellidos, String edad, String direccion){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("apellidos", apellidos);
        contentValues.put("edad", edad);
        contentValues.put("direccion", direccion);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where dni = ?", new String[]{dni});
        if(cursor.getCount()>0) {

            long result = DB.update("Userdetails", contentValues, "dni=?", new String[]{dni});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else{
            return false;
        }
    }

    public boolean deletedata(String dni){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where dni = ?", new String[]{dni});
        if(cursor.getCount()>0) {

            long result = DB.delete("Userdetails", "dni=?", new String[]{dni});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails ", null);
        return cursor;
    }


}
