package com.mm.storage.local.db.sites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mm.sites.Site;
import com.mm.storage.local.db.DBHelper;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.DBStorage;

/**
 * Created by nayerram on 05-10-2016.
 */
public class SiteStorage implements ISitesStorage {
    @Override
    public boolean persistSite(Context context, Site site) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBSchema.Sites.NAME,site.name);
        values.put(DBSchema.Sites.LOCATION,site.location);
        values.put(DBSchema.Sites.STARTDATE,site.startDate);

        //  TODO:Return Value check
        db.insert(DBSchema.Sites.TABLE_NAME,null,values);
        db.close();

        return true;
    }

    @Override
    public Cursor getAllSites(Context context) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query(
                DBSchema.Sites.TABLE_NAME,              // The table to query
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
    public Cursor getActiveSites(Context context) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query(
                DBSchema.Sites.TABLE_NAME,              // The table to query
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
    public Cursor getClosedSites(Context context) {
        return null;
    }

    @Override
    public Cursor getPendingSites(Context context) {
        return null;
    }

    @Override
    public Site getSite(Context context, Integer siteId) {
        Site site =null;
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


//        Cursor c = db.query(
//                DBSchema.Sites.TABLE_NAME,              // The table to query
//                null,                               // The columns to return
//                null,                                // The columns for the WHERE clause
//                null,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                null                                 // The sort order
//        );

        Cursor c= db.rawQuery("SELECT * FROM " + DBSchema.Sites.TABLE_NAME + " where " +
                                DBSchema.Sites._ID + " = " + siteId.toString(),null);

        if (c.getCount() == 1){
            site = new Site();
            c.moveToFirst();
            site.id = c.getInt(0);
            site.name =  c.getString(1);
            site.location = c.getString(2);
            site.startDate = c.getString(3);
        }else{
            //TODO: Error handling code
        }

       return site;
    }

    @Override
    public Site getSite(Context context, String siteName) {
        return null;
    }

    @Override
    public Cursor getSites(Context context, String searchString) {

        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query(
                true,
                DBSchema.Sites.TABLE_NAME,
                null,
                DBSchema.Sites.NAME + " LIKE ?",
                new String[] {"%"+searchString+"%"},
                null,
                null,
                null,
                null
        );

        return c;
    }
}
