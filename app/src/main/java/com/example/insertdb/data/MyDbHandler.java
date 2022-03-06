package com.example.insertdb.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.insertdb.model.Items;
import com.example.insertdb.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context){
        super(context, Params.DB_NAME,null,Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME +"("
                + Params.KEY_ID + " INTEGER PRIMARY KEY, " +
                Params.KEY_ITEM + " TEXT, " +
                Params.KEY_QUAN + " TEXT " + ")";
        Log.d("db","Query being run is : " +create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void additem(Items items){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.KEY_ITEM, items.getItem());
        values.put(Params.KEY_QUAN, items.getQuan());
        db.insert(Params.TABLE_NAME,null,values);
        Log.d("db","Values are Inserted...");
        db.close();
    }

    public int updateitem(Items items){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.KEY_ITEM, items.getItem());
        values.put(Params.KEY_QUAN, items.getQuan());

        return db.update(Params.TABLE_NAME,values,Params.KEY_ITEM + "=?",
                new String[]{String.valueOf(items.getItem())});
    }

    public void deleteitem(String item){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ITEM + "=?",
                new String[]{String.valueOf(item)});
        db.close();
    }

    public List<Items> getAllUsers(){
        List<Items> itemsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do {
                Items items = new Items();
                items.setId(Integer.parseInt(cursor.getString(0)));
                items.setItem(cursor.getString(1));
                items.setQuan(cursor.getString(2));
                itemsList.add(items);
            }while (cursor.moveToNext());
        }
        return itemsList;
    }

}
