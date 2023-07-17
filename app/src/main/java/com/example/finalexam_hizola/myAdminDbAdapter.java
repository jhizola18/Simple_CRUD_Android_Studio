package com.example.finalexam_hizola;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class myAdminDbAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "admin_database";
    private static final String TABLE_NAME = "ADMIN";
    public static final String ADMIN_ID = "admin_id";
    public static final String ADMIN_NAME= "admin_nameTEXT";
    public static final String ADMIN_PASSWORD = "admin_passwordTEXT";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " (" + ADMIN_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_NAME + " VARCHAR(255), " + ADMIN_PASSWORD  + " VARCHAR(225));";

    public myAdminDbAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long add_admin(AdminModelClass adminModelClass){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myAdminDbAdapter.ADMIN_NAME, adminModelClass.getAdmin_name());
        contentValues.put(myAdminDbAdapter.ADMIN_PASSWORD, adminModelClass.getAdmin_password());
        long id = sqLiteDatabase.insert(myAdminDbAdapter.TABLE_NAME,null,contentValues);

        return id;
    }

    public List<AdminModelClass> getAdminList(){
        String sql = " select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<AdminModelClass> storeAdmin = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int admin_id = Integer.parseInt(cursor.getString(0));
                String admin_name = cursor.getString(1);
                String admin_pass = cursor.getString(2);
                storeAdmin.add(new AdminModelClass(admin_id, admin_name, admin_pass));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return storeAdmin;

    }

    public void updateAdmin(AdminModelClass adminModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(myAdminDbAdapter.ADMIN_NAME, adminModelClass.getAdmin_name());
        contentValues.put(myAdminDbAdapter.ADMIN_PASSWORD, adminModelClass.getAdmin_password());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, ADMIN_ID + " =?", new String[]
                {String.valueOf(adminModelClass.getAdmin_id())});
    }

    public void deleteAdmin(int admin_id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ADMIN_ID + " =?", new String[]
                {String.valueOf(admin_id)});
    }

    public Boolean checkAdminName(String adminName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from ADMIN where admin_nameTEXT =?", new String[]{adminName});

        if(cursor.getCount() > 0)
        {
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkAdminCred(String adminName,String adminPass){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from ADMIN where admin_nameTEXT =? and admin_passwordTEXT =?", new String[]{adminName, adminPass});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }


}
