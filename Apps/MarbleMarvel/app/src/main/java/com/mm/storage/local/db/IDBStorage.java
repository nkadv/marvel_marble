package com.mm.storage.local.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nayerram on 04-10-2016.
 */
public interface IDBStorage {

//    public boolean createDB(Context context);
    public boolean openDB();
    public SQLiteDatabase getWritableDB();
    public SQLiteDatabase getReadableDB();
    public boolean closeDB();

}
