package com.example.finalexam_hizola;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class myDbAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_database";
    private static final String TABLE_NAME = "USER";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME= "user_name";
    public static final String USER_PASSWORD = "user_passwordTEXT";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " (" + USER_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " VARCHAR(255), " + USER_PASSWORD + " VARCHAR(225));";

    public myDbAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long adduser(UserModelClass userModelClass){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbAdapter.USER_NAME, userModelClass.getName());
        contentValues.put(myDbAdapter.USER_PASSWORD, userModelClass.getPassword());
        long id = sqLiteDatabase.insert(myDbAdapter.TABLE_NAME,null,contentValues);

        return id;
    }

    public List<UserModelClass> getUserList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<UserModelClass> storeUser = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int user_id = Integer.parseInt(cursor.getString(0));
                String user_name = cursor.getString(1);
                String user_pass = cursor.getString(2);
                storeUser.add(new UserModelClass(user_id, user_name, user_pass));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return storeUser;

    }

    public void updateUser(UserModelClass userModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbAdapter.USER_NAME, userModelClass.getName());
        contentValues.put(myDbAdapter.USER_PASSWORD, userModelClass.getPassword());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, USER_ID + " =?", new String[]
                {String.valueOf(userModelClass.getId())});
    }

    public void deleteUser(int user_id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, USER_ID + " =?", new String[]
                {String.valueOf(user_id)});
    }


    public Boolean checkUserName(String userName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from USER where user_name =?", new String[]{userName});

        if(cursor.getCount() > 0)
        {
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkUserCred(String userName,String userPass){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from USER where user_name =? and user_passwordTEXT =?", new String[]{userName, userPass});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

}