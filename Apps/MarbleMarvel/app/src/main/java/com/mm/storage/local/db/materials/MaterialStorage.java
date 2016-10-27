package com.mm.storage.local.db.materials;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mm.labours.Labour;
import com.mm.materials.Material;
import com.mm.storage.local.db.DBHelper;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.DBStorage;

/**
 * Created by nayerram on 05-10-2016.
 */
public class MaterialStorage implements IMaterialStorage {
    @Override
    public boolean persistMaterial(Context context, Material material) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBSchema.Materials.NAME,material.name);
        values.put(DBSchema.Materials.UNITS,material.units);
        values.put(DBSchema.Materials.COSTPERUNIT,material.costPerUnit);

        //  TODO:Return Value check
        db.insert(DBSchema.Materials.TABLE_NAME,null,values);

        db.close();

        return true;
    }

    @Override
    public Cursor getAllMaterials(Context context) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query(
                DBSchema.Materials.TABLE_NAME,              // The table to query
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
    public Labour getMaterial(Context context, Integer materialID) {
        return null;
    }

    @Override
    public Labour getMaterial(Context context, String materialName) {
        return null;
    }

    @Override
    public Cursor getMaterials(Context context, String searchString) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query(
                true,
                DBSchema.Materials.TABLE_NAME,
                null,
                DBSchema.Materials.NAME + " LIKE ?",
                new String[] {"%"+searchString+"%"},
                null,
                null,
                null,
                null
        );

        return c;
    }
}
