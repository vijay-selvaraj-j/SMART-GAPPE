package com.miniproject.smartgappe.USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBULogin extends SQLiteOpenHelper {
    public DBULogin(@Nullable Context context) {
        super(context, "User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase udb) {
        udb.execSQL("create table user(uname text primary key, upass text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase udb, int i, int i1) {
        udb.execSQL("drop table if exists user");
    }

    public boolean insertUser(String uname, String upass)
    {
        SQLiteDatabase udb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uname",uname);
        cv.put("upass",upass);
        long res = udb.insert("user",null,cv);
        if(res == -1)
        {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean cuname(String username)
    {
        SQLiteDatabase udb = this.getWritableDatabase();
        Cursor cursor = udb.rawQuery("Select * from user where uname = ?", new String[]{(username)});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkunamepass(String username, String password)
    {
        SQLiteDatabase udb = this.getWritableDatabase();
        Cursor c = udb.rawQuery("select * from user where uname = ? and upass = ?", new String[]{username,password});
        if(c.getCount()>0)
            return true;
        else
            return false;

    }
}
