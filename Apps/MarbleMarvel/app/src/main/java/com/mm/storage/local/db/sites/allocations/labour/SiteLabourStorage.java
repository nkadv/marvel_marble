package com.mm.storage.local.db.sites.allocations.labour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.mm.storage.local.db.DBHelper;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.DBStorage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nayerram on 07-10-2016.
 */
public class SiteLabourStorage implements ISiteLabourStorage {
    @Override
    public boolean AddLabours(Context context, ArrayList<Integer> l_list, Integer siteId, Date date) {

        Long rInsert;
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (int i=0;i<l_list.size();i++){
            values.put(DBSchema.SiteLabours.LABOUR_ID,l_list.get(i));
            values.put(DBSchema.SiteLabours.SITE_ID,siteId);
            values.put(DBSchema.SiteLabours.START_DATE, date.toString());
            values.put(DBSchema.SiteLabours.STATUS,DBSchema.status.active);
            rInsert = db.insert(DBSchema.SiteLabours.TABLE_NAME,null,values);

            //Toast.makeText(context, "Inserted : "+rInsert, Toast.LENGTH_SHORT).show();

        }

        db.close();

        return true;
    }

    @Override
    public boolean transferLabours(Context context, ArrayList<Integer> l_list, Integer fromSiteId, Integer toSiteId,Date date) {
        return false;
    }

    @Override
    public boolean removeLabours(Context context, ArrayList<Integer> l_list, Integer siteId,Date date) {
        return false;
    }

    @Override
    public Cursor getLabours(Context context, Integer siteId) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        Cursor c = db.query(
//                true,
//                DBSchema.SiteLabours.TABLE_NAME,              // The table to query
//                null,                               // The columns to return
//                DBSchema.SiteLabours.SITE_ID + " = ?",                                // The columns for the WHERE clause
//                new String[] {siteId.toString()},                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                null,
//                null// The sort order
//        );
        //TODO: Check extra column DBSchema.Labours.TABLE_NAME+"."+DBSchema.Labours._ID

//        String QUERY = "SELECT " +   DBSchema.Labours.TABLE_NAME+"."+DBSchema.Labours._ID+ ","+DBSchema.Labours.NAME+ ","+
//                DBSchema.Labours.TABLE_NAME+"."+DBSchema.Labours.LABOUR_ID + ","+ DBSchema.Labours.FATHER_NAME+ ","+
//                DBSchema.Labours.PLACE+ " FROM " + DBSchema.SiteLabours.TABLE_NAME +" join "+ DBSchema.Labours.TABLE_NAME+
//                " on "+ DBSchema.SiteLabours.TABLE_NAME+"."+DBSchema.SiteLabours.LABOUR_ID +" = "+
//                DBSchema.Labours.TABLE_NAME+"."+DBSchema.Labours.LABOUR_ID + " WHERE "+ DBSchema.SiteLabours.SITE_ID + " = ?"  ;
//
//
//        String selectionArgs [] ={siteId.toString()};
//        Cursor c = db.rawQuery(QUERY, selectionArgs);



        String lTable = DBSchema.Labours.TABLE_NAME;
        String slTable = DBSchema.SiteLabours.TABLE_NAME;


//        String QUERY = "SELECT " +  lTable+"."+DBSchema.Labours._ID+","+lTable+"."+DBSchema.Labours.LABOUR_ID+ ","+DBSchema.Labours.NAME+ ","+
//                DBSchema.Labours.FATHER_NAME+","+DBSchema.Labours.PLACE + " FROM " + slTable +" join "+ lTable+
//                " on "+ slTable+"."+DBSchema.SiteLabours.LABOUR_ID +" = "+
//                lTable+"."+DBSchema.Labours.LABOUR_ID + " WHERE "+ DBSchema.SiteLabours.SITE_ID + " = ?"  ;

        String QUERY = "SELECT *" +  " FROM " + slTable +" join "+ lTable+
        " on "+ slTable+"."+DBSchema.SiteLabours.LABOUR_ID +" = "+
        lTable+"."+DBSchema.Labours.LABOUR_ID + " WHERE "+ DBSchema.SiteLabours.SITE_ID + " = ?"  ;

        String selectionArgs [] ={siteId.toString()};
        Cursor c = db.rawQuery(QUERY, selectionArgs);


//        Cursor c = db.query(
//                DBSchema.SiteLabours.TABLE_NAME,              // The table to query
//                null,                               // The columns to return
//                null,                                // The columns for the WHERE clause
//                null,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                null                                 // The sort order
//        );
//
        Toast.makeText(context, "Inserted : "+c.getCount(), Toast.LENGTH_SHORT).show();
        return c;
    }
}
