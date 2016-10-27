package com.mm.storage.local.db.labour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mm.labours.Labour;
import com.mm.storage.local.db.DBHelper;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.DBStorage;

import java.util.ArrayList;

/**
 * Created by nayerram on 04-10-2016.
 */
public class LabourStorage implements ILabourStorage {

    @Override
    public boolean persistLabour(Context context, Labour labour) {

        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBSchema.Labours.LABOUR_ID,labour.id);
        values.put(DBSchema.Labours.NAME,labour.name);
        values.put(DBSchema.Labours.FATHER_NAME,labour.fathersName);
        values.put(DBSchema.Labours.PLACE,labour.place);
        values.put(DBSchema.Labours.DOJ,labour.doj);
        values.put(DBSchema.Labours.RATE,labour.rate);
        values.put(DBSchema.Labours.PHOTO,labour.photo);
        labour.balance=0.0;
        values.put(DBSchema.Labours.BALANCE,labour.balance);

        //  TODO:Return Value check
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        db.close();

        return true;
    }


    @Override
    public Cursor getAllLabours(Context context) {

        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Integer rowCount;
        ArrayList<Labour> labourList =null;

        Cursor c = db.query(
            DBSchema.Labours.TABLE_NAME,              // The table to query
            null,                               // The columns to return
            null,                                // The columns for the WHERE clause
            null,                            // The values for the WHERE clause
            null,                                     // don't group the rows
            null,                                     // don't filter by row groups
            null                                 // The sort order
        );

        return c;

    }

    @Override
    public Labour getLabour(Context context,Integer labourId) {
        return null;
    }

    @Override
    public Labour getLabour(Context context,String labourName) {
        return null;
    }

    @Override
    public Cursor getLabours(Context context, String searchString) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


//        select * from <tablle_name> where <Column> like %searchString%'
        Cursor c = db.query(
                true,
                DBSchema.Labours.TABLE_NAME,              // The table to query
                null,                               // The columns to return
                DBSchema.Labours.NAME + " LIKE ?" + " OR " + DBSchema.Labours.LABOUR_ID + " LIKE ?" ,  // The columns for the WHERE clause
                new String[] {"%"+searchString+"%","%"+searchString+"%"},                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null,
                null// The sort order
        );

        return c;
    }
}
