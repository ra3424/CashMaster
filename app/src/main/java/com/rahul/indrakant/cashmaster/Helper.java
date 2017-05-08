package com.rahul.indrakant.cashmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Helper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;

    private String tbname;
    public Helper(Context context,String name)
    {
        super(context,"dbname",null,1);
        tbname=name;
        sqLiteDatabase= getWritableDatabase();
onCreate(sqLiteDatabase);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not exists "+tbname +" (id integer primary key autoincrement,name text not null,category text not null,price real not null,date text not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exist "+tbname);
        onCreate(sqLiteDatabase);
    }

    public  String save(BeanItem beanItem)
    {
        String msg;

        sqLiteDatabase=getWritableDatabase();
        try {
            ContentValues contentValues=new ContentValues();
            contentValues.put("name",beanItem.getName());
            contentValues.put("category",beanItem.getCategory());
            contentValues.put("price",beanItem.getPrice());
            contentValues.put("date",beanItem.getDate());
            sqLiteDatabase.insert(tbname,null,contentValues);
            msg="data saved";
        }
        catch (Exception e)
        {
            msg=e.getMessage();
        }
        return msg;
    }

    public Cursor disp(String dt)
    {
        sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select price,name,date from "+ tbname+" where date='"+dt+"';",null);
        return cursor;
    }

    public  Cursor expense(int days)
    {
        sqLiteDatabase=getReadableDatabase();
        Cursor  cursor=sqLiteDatabase.rawQuery("select * from "+tbname+" where julianday('now') - julianday(date) <"+days,null);
        return cursor;
    }

}
