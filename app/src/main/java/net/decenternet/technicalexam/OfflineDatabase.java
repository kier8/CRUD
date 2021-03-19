package net.decenternet.technicalexam;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class OfflineDatabase extends SQLiteOpenHelper {
    //Variable
    private static final String DATABASE_NAME = "data_db";
    private static final String TABLE_USER = "data_user";
    public OfflineDatabase(@Nullable Context context) { super(context, DATABASE_NAME, null, 1); }

    public long insertDataUser(String fname, String email, String num){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullname", fname);
        cv.put("email", email);
        cv.put("number", num);
        long id = db.insert(TABLE_USER, null, cv);
        db.close();
        return id;
    }
    public int updatecode(String semail,String svcodes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("vcode", svcodes);
        String[] whereArgs = { semail };
        int count = db.delete(TABLE_USER, "email" + " =? ", whereArgs);
        return count;
    }
    public int Deleteuser(String email) {
        SQLiteDatabase db = getWritableDatabase();
        String[] whereArgs = {email};
        int count = db.delete(TABLE_USER, "email" + " =? ", whereArgs);
        return count;
    }
    public String getDataUser(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM data_user", null);
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()) {
            long cid = c.getLong(c.getColumnIndex("id"));
            String fname1 = c.getString(c.getColumnIndex("fullname"));
            String email1 = c.getString(c.getColumnIndex("email"));
            String num1 = c.getString(c.getColumnIndex("number"));
            buffer.append(cid + "\n" + fname1 + "\n" + email1 + "\n" + num1);
        }
        return buffer.toString();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table
        String tbl_user = "CREATE TABLE "+ TABLE_USER + "(id INTEGER PRIMARY KEY AUTOINCREMENT, fullname VARCHAR(100), email VARCHAR(100), user VARCHAR(100), password VARCHAR(100), number VARCHAR(100))";
        db.execSQL(tbl_user);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}