package com.rahul.indrakant.cashmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class HelperLogin  extends SQLiteOpenHelper {
    //SQLite open helper helps to interact with db
    private SQLiteDatabase db;
    //constructor to create database
    public HelperLogin(Context context) { super(context, "database",null,1); }

    @Override //create table in selected db
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table person(id integer primary key autoincrement,name text not null,phone text not null unique ,password text not null )");
    }

    @Override //check if table already exist and upgrade it
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist person");
        onCreate(sqLiteDatabase);
    }

    public String save(Bean beanClass) {
        String msg;
        db=getWritableDatabase();
        try {
            ContentValues values=new ContentValues(); // kind of a container to save values of row entry
            values.put("name",beanClass.getName());
            values.put("phone",beanClass.getPhone());
            values.put("password",beanClass.getPassword());
            db.insert("person",null,values);
            msg="Signed Up";
        }
        catch (Exception e) { msg=e.getMessage(); }
        return msg;
    }

    public Cursor disp(Bean bean)
    {
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select password,name from person where phone= '" + bean.getPhone()+"'",null);
        return cursor;


    }
    public String updt(String str,String str1)
    {String ms;
        db=getWritableDatabase();
        try {
            ContentValues val=new ContentValues();
            val.put("password",str1);
            db.update("person",val,"phone=?",new String[]{str});
            ms="Password Changed";
        }
        catch (Exception e)
        {
            ms=e.getMessage();
        }
return ms;
    }

}
