package com.mm.storage.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nayerram on 04-10-2016.
 */
public class DBStorage implements IDBStorage {
    private static DBHelper dbHelperInstance;

    private DBStorage(){ }

    public static synchronized  DBHelper getInstance(Context context){
        if(dbHelperInstance == null){
            dbHelperInstance = new DBHelper(context);
        }
        return dbHelperInstance;
    }

    @Override
    public boolean openDB() {
        return false;
    }

    @Override
    public SQLiteDatabase getWritableDB() {
        return dbHelperInstance.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDB() {
       return dbHelperInstance.getReadableDatabase();
    }

    @Override
    public boolean closeDB() {
        dbHelperInstance.close();
        return true;
    }
}
