package com.miniproject.smartgappe.ADMIN;
import static java.lang.String.valueOf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBALogin extends SQLiteOpenHelper{

    public DBALogin(@Nullable Context context) {
        super(context, "Admin.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase admin) {
        admin.execSQL("create table admin(aname text primary key, apass text, aloc text unique)");
        admin.execSQL("create table stock(dc int, cc int, pc int, loc text)");
        admin.execSQL("create table uorder(item text, ic int, cost int, loc text, uname text)");
        admin.execSQL("create table listorder(item text, ic int, cost int, loc text, uname text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase admin, int i, int i1) {
        admin.execSQL("drop table if exists admin");
        admin.execSQL("drop table if exists stock");
        admin.execSQL("drop table if exists uorder");
        admin.execSQL("drop table if exists listorder");

    }

    public boolean insertAdmin(String aname, String apass, String aloc)
    {
        SQLiteDatabase adb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("aname",aname);
        cv.put("apass",apass);
        cv.put("aloc",aloc);
        adb.execSQL("insert into stock values(?,?,?,?)",new String[]{"0","0","0",aloc});
        long res = adb.insert("admin",null,cv);
        if(res == -1)
        {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean caname(String adminname)
    {
        SQLiteDatabase adb = this.getWritableDatabase();
        Cursor cursor = adb.rawQuery("Select * from admin where aname = ?", new String[]{(adminname)});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkanamepass(String adminname, String password)
    {
        SQLiteDatabase adb = this.getWritableDatabase();
        Cursor cursor = adb.rawQuery("Select * from admin where aname = ? and apass = ?", new String[]{adminname, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;



    }

    public boolean insertstock(int a, int b, int c, String d)
    {
       SQLiteDatabase cdb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("dc",a);
        cv.put("cc",b);
        cv.put("pc",c);

        long res = cdb.update("stock",cv,"loc = ?",new String[]{d});
           if(res==-1)
               return false;
           else
               return true;
    }


    /*
    public int insertuorder1(String a, int b, int c, String d, String un)
    {
        int lv1;
        SQLiteDatabase iuodb = this.getWritableDatabase();
        SQLiteDatabase iuodb1 = this.getReadableDatabase();

        Cursor c1 = iuodb1.rawQuery("Select dc from stock where loc = ?",new String[]{d});
        c1.moveToFirst();
        lv1 = c1.getInt(0);
        ContentValues cv = new ContentValues();
        cv.put("item",a);
        cv.put("ic",b);
        cv.put("cost",c);
        cv.put("loc",d);
        cv.put("uname",un);
        long res;
        if(b<=lv1)
        {
            res = iuodb.insert("uorder", null, cv);
            if (res == -1)
            {
                return 0;
            }
            else {
                return 1;
            }
        }
        else
            return 2;
    }

    public int insertuorder2(String a, int b, int c, String d, String un)
    {
        int lv2;
        SQLiteDatabase iuodb = this.getWritableDatabase();
        SQLiteDatabase iuodb1 = this.getReadableDatabase();
        Cursor c2 = iuodb1.rawQuery("Select cc from stock where loc = ?",new String[]{d});
        c2.moveToFirst();
        lv2 = c2.getInt(0);
        ContentValues cv = new ContentValues();
        cv.put("item",a);
        cv.put("ic",b);
        cv.put("cost",c);
        cv.put("loc",d);
        cv.put("uname",un);
        long res;
        if(b<=lv2)
        {
            res = iuodb.insert("uorder", null, cv);
            if (res == -1)
            {
                return 0;
            }
            else {
                return 1;
            }
        }
        else
            return 2;
    }


    public int insertuorder3(String a, int b, int c, String d,String un)
    {
        int lv3;
        SQLiteDatabase iuodb = this.getWritableDatabase();
        SQLiteDatabase iuodb1 = this.getReadableDatabase();

        Cursor c3 = iuodb1.rawQuery("Select pc from stock where loc = ?",new String[]{d});
        c3.moveToFirst();
        lv3 = c3.getInt(0);

        ContentValues cv = new ContentValues();
        cv.put("item",a);
        cv.put("ic",b);
        cv.put("cost",c);
        cv.put("loc",d);
        cv.put("uname",un);
        long res;
        if(b<=lv3)
        {
            res = iuodb.insert("uorder", null, cv);
            if (res == -1)
            {
                return 0;
            }
            else {
                return 1;
            }
        }
        else
            return 2;

    }
*/
    public int insertuorder(String a, int b, int c, String d,String un)
    {

        SQLiteDatabase iuodb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("item",a);
        cv.put("ic",b);
        cv.put("cost",c);
        cv.put("loc",d);
        cv.put("uname",un);
        long res = iuodb.insert("uorder", null, cv);
        if (res == -1)
        {
            return 0;
        }
        else {
            return 1;
        }
    }

    public void insertalluorder(String a, int b, int c, String d,String un)
    {
        SQLiteDatabase iuodb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("item",a);
        cv.put("ic",b);
        cv.put("cost",c);
        cv.put("loc",d);
        cv.put("uname",un);
        iuodb.insert("listorder", null, cv);
    }

    public void clear(String a)
    {
        SQLiteDatabase cdb = this.getWritableDatabase();
        cdb.execSQL("delete from uorder where loc = ?", new String[]{a});
    }


    public int panic(String l)
    {
        Cursor count;
        SQLiteDatabase cc = this.getReadableDatabase();
        count = cc.rawQuery("Select pc from stock where loc = ?", new String[]{l});
        count.moveToFirst();
        int res = count.getInt(0);
        return res;
    }

    public int dahic(String l)
    {
        Cursor count;
        SQLiteDatabase cc = this.getReadableDatabase();
        count = cc.rawQuery("Select dc from stock where loc = ?", new String[]{l});
        count.moveToFirst();
        int res = count.getInt(0);
        return res;
    }

    public int churc(String l)
    {
        Cursor count;
        SQLiteDatabase cc = this.getReadableDatabase();
        count = cc.rawQuery("Select cc from stock where loc = ?", new String[]{l});
        count.moveToFirst();
        int res = count.getInt(0);
        return res;
    }

    public boolean uchurc(int c, String d)
    {
        SQLiteDatabase up = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor count;
        count = up.rawQuery("Select cc from stock where loc = ?", new String[]{d});
        count.moveToFirst();
        int res = count.getInt(0);
        if(res>=c)
        {
            int uc = res - c;
            cv.put("cc",uc);
            up.update("stock",cv,"loc = ?",new String[]{d});
            return true;
        }
        return false;

    }

    public boolean upanic(int c, String d)
    {
        SQLiteDatabase up = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor count;
        count = up.rawQuery("Select pc from stock where loc = ?", new String[]{d});
        count.moveToFirst();
        int res = count.getInt(0);
       if(res>=c)
       {
           int uc = res - c;
           cv.put("pc",uc);
           up.update("stock",cv,"loc = ?",new String[]{d});
           return true;
       }
       return false;
    }

    public boolean udahic(int c, String d)
    {
        SQLiteDatabase up = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor count;
        count = up.rawQuery("Select dc from stock where loc = ?", new String[]{d});
        count.moveToFirst();
        int res = count.getInt(0);
        if(res>=c)
        {
            int uc = res - c;
            cv.put("dc",uc);
            up.update("stock",cv,"loc = ?",new String[]{d});
            return true;
        }
        return false;
    }



    public List<String> getAllLabels()
    {
        List<String> list = new ArrayList<String>();
        String sq = "select aloc from admin";
        SQLiteDatabase ardb = this.getReadableDatabase();
        Cursor c = ardb.rawQuery(sq,null);
        if (c.moveToFirst()) {
            do {
                list.add(c.getString(0));
            } while (c.moveToNext());
        }
        return list;
    }

    public Cursor readAll(String a)
    {
        String location = a;
        SQLiteDatabase db9 = this.getWritableDatabase();
        Cursor c = db9.rawQuery("select * from  uorder where loc = ?",new String[]{location});
        return c;
    }

    public Cursor readAllOrder(String a)
    {
        String location = a;
        SQLiteDatabase db9 = this.getWritableDatabase();
        Cursor c = db9.rawQuery("select * from  listorder where loc = ?",new String[]{location});
        return c;
    }

    public int totalcost(String a)
    {
        int total = 0;
        SQLiteDatabase db10 = this.getWritableDatabase();
        Cursor count;
        count = db10.rawQuery("Select sum(cost) from listorder where loc = ?", new String[]{a});
        count.moveToFirst();
        int res = count.getInt(0);
        total = res;
        return total;
    }
}
