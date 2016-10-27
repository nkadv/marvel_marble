package com.mm.storage.local.db.sites.allocations.material;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mm.storage.local.db.DBHelper;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.DBStorage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nayerram on 07-10-2016.
 */
public class SiteMaterialStorage implements ISiteMaterialStorage {

    @Override
    public boolean AddMaterialExpense(Context context, Integer m_id, Double m_quanity, Integer siteId, String date) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBSchema.SiteMaterials.MATERIAL_ID,m_id);
        values.put(DBSchema.SiteMaterials.SITE_ID,siteId);
        values.put(DBSchema.SiteMaterials.DATE, date.toString());
        values.put(DBSchema.SiteMaterials.QUANTITY,m_quanity);

        db.insert(DBSchema.SiteMaterials.TABLE_NAME,null,values);

        return true;
    }

    @Override
    public boolean AddMaterialExpenses(Context context, ArrayList<Integer> mId_list,
                                       ArrayList<Double> mQuanity_list, Integer siteId, String date) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (int i=0;i<mId_list.size();i++){
            values.put(DBSchema.SiteMaterials.MATERIAL_ID,mId_list.get(i));
            values.put(DBSchema.SiteMaterials.SITE_ID,siteId);
            values.put(DBSchema.SiteMaterials.DATE, date.toString());
            values.put(DBSchema.SiteMaterials.QUANTITY,mQuanity_list.get(i));
            db.insert(DBSchema.SiteMaterials.TABLE_NAME,null,values);
        }

        db.close();
        return true;
    }

    @Override
    public boolean RemoveMaterialExpenses(Context context, ArrayList<Integer> m_list, Integer siteId, Date date) {
        return false;
    }

    @Override
    public Cursor getMaterialExpenses(Context context, Integer siteId) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String mTable = DBSchema.Materials.TABLE_NAME;
        String smTable = DBSchema.SiteMaterials.TABLE_NAME;

        //  TODO: SiteLabours change to SiteMaterals

        String QUERY = "SELECT " +   mTable+"."+DBSchema.Materials._ID+ ","+DBSchema.Materials.NAME+ ","+
                DBSchema.SiteMaterials.QUANTITY+","+DBSchema.SiteMaterials.AMOUNT + ","+ DBSchema.SiteMaterials.DATE+ ","+
                DBSchema.SiteMaterials.EMP_ID+ " FROM " + smTable +" join "+ mTable+
                " on "+ smTable+"."+DBSchema.SiteMaterials.MATERIAL_ID +" = "+
                mTable+"."+DBSchema.Materials._ID + " WHERE "+ DBSchema.SiteLabours.SITE_ID + " = ?"  ;

        String selectionArgs [] ={siteId.toString()};
        Cursor c = db.rawQuery(QUERY, selectionArgs);

        return c;
    }
}
